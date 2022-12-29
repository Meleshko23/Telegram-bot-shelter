package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.model.KeepingPet;
import pro.sky.telegrambot.model.PhotoPet;
import pro.sky.telegrambot.repositories.KeepingPetRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Сервис, описывающий методы по ведению питомца хозяевами
 */
@Service
public class KeepingPetService {

//    private String textReport;
//    private Integer fileId;
//    private String photoesDir = "/photoPets";

    private final PetOwnerService petOwnerService;
    private final PhotoPetService photoPetService;
//    private final UserService userService;
    private final KeepingPetRepository keepingPetRepository;

    @Autowired
    private TelegramBot telegramBot;
    private final String coversDir = ("C://Users//lenovo//Desktop");

    public KeepingPetService(PetOwnerService petOwnerService, PhotoPetService photoPetService, KeepingPetRepository keepingPetRepository) {
        this.petOwnerService = petOwnerService;
        this.photoPetService = photoPetService;
        this.keepingPetRepository = keepingPetRepository;
    }

    /**
     * Метод отправляет ежедневный отчет усыновителя, включающиий  фото питомца, рацион, самочувствие, поведение. Отчет сохраняется в БД в таблице KeepingPet
     *
     * @param chatId     идентификатор чата, не может быть null
     * @param photoSizes объект, хранящий информацию с фотографией питомца. не null
     * @param caption    сообщение, отправленное вместе с фото
     * @return KeepingPet (объект инкапсулирующий отчет пользователя)
     */
    public KeepingPet sendReport(Long chatId, String caption, PhotoSize[] photoSizes) throws IOException {
        KeepingPet keepingPet = getNewReport(chatId, photoSizes, caption);
//        если user не владелец животного - бросить ошибку
//        if (chatId != null) {
//            throw new NoAnimalAdoptedException();
//        }

        return keepingPetRepository.save(keepingPet);
    }

    /**
     * Метод создает объекты типа PhotoPet и KeepingPet
     * Отправляет эти объекты в базу данных
     * Сохраняет фотографию питомца на сервер в папку
     * @param chatId Идентификатор чата
     * @param photoSizes массив объектов класса PhotoSize (фотографии, отправленные пользователем)
     * @param caption Текстовое описание к фотографии
     * @return KeepingPet сохраненный в БД отчет
     * @throws IOException
     */
    private KeepingPet getNewReport(Long chatId, PhotoSize[] photoSizes, String caption) throws IOException {
        PhotoSize photo = photoSizes[1];
        String fileId = photo.fileId();

        GetFile fileRequest = new GetFile(fileId);
        GetFileResponse fileResponse = telegramBot.execute(fileRequest);
        File file = fileResponse.file();
        byte[] fileData = telegramBot.getFileContent(file);

        Path filePath = Path.of(coversDir, fileId + "." +getExtension(file.filePath()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        PhotoPet photoPet = savePhotoPetToDB(chatId, fileRequest, file, filePath);

        KeepingPet keepingPet = saveReportToDB(chatId, caption, photoPet);

        uploadPhotoToServer(fileData, filePath);

        return keepingPet;
    }

    /**
     * Метод сохраняет объект типа PhotoPet в базу данных
     *
     * @param chatId Идентификатор чата
     * @param fileRequest объект класса GetFile
     * @param file объект класса File
     * @param filePath путь к файлу
     * @return сохраненный в БД объект класса PhotoPet
     */
    private PhotoPet savePhotoPetToDB(Long chatId, GetFile fileRequest, File file, Path filePath) {

        PhotoPet photoPet = new PhotoPet();
        photoPet.setMediaType(fileRequest.getContentType());
        photoPet.setFileSize(file.fileSize());
        photoPet.setFilePath(filePath.toString());

        CatOwner catOwner = petOwnerService.findCatOwner(chatId);
        DogOwner dogOwner = petOwnerService.findDogOwner(chatId);
        if (catOwner != null) {
            photoPet.setPet(catOwner.getPet());
        }
        if (dogOwner != null) {
            photoPet.setPet(dogOwner.getPet());
        }
        photoPetService.savePhotoReport(photoPet);

        return photoPet;
    }

    /**
     * Метод сохраняет отчет владельца о питомце в БД
     *
     * @param chatId Идентификатор чата
     * @param caption текстовое описание отчета
     * @param photoPet объект класса PhotoPet
     * @return Сохраненный в БД отчет
     */
    private KeepingPet saveReportToDB(Long chatId, String caption, PhotoPet photoPet) {

        KeepingPet keepingPet = new KeepingPet();
        keepingPet.setChatId(chatId);
        keepingPet.setDateTime(LocalDateTime.now());
        keepingPet.setInfoPet(caption);
        keepingPet.setPhotoPet(photoPet);

        CatOwner catOwner = petOwnerService.findCatOwner(chatId);
        DogOwner dogOwner = petOwnerService.findDogOwner(chatId);
        if (catOwner != null) {
            keepingPet.setCatOwner(catOwner);
        } else if (dogOwner != null) {
            keepingPet.setDogOwner(dogOwner);
        }
        return keepingPet;
    }

    /**
     * Метод загружает фотографию питомца на сервер в папку
     * @param fileData массив байтов, хранящий фотографию
     * @param filePath путь для сохранения фотографии
     * @Throw RuntimeException ошибка при сохранении фото
     *
     */
    private void uploadPhotoToServer(byte[] fileData, Path filePath) {
        try (InputStream is = new ByteArrayInputStream(fileData);
             OutputStream os=Files.newOutputStream(filePath,CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * первая стадия метода отправки отчета. Этот метод отпрвляет пользователю сообщение с просьбой
     * отправить отчет: текст и фото
     *
     * @param chatId      идентификатор чата
     * @param messageText сообщение для пользователя
     */
    public void sendReport(long chatId, String messageText) {
        sendMessageReply(chatId, messageText);
    }

    /**
     * Метод вызывается при отправке отчета пользователем, который
     * не является владельцем питомца
     * @param chatId идентификатор чата
     * @param messageText сообщение пользователю
     */
    public void sendReportWithoutReply(long chatId, String messageText) {
        sendMessage(chatId, messageText);
    }

    /**
     * Метод получает расширение файла из его полного пути
     * @param fileName имя файла
     * @return расширение файла
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private void sendMessageReply(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        sendMess.replyMarkup(new ForceReply());
        telegramBot.execute(sendMess);
    }

    public void sendMessage(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMess);
    }

//    public void increaseProbationPeriod(Long ownerId, int count) {
//        CatOwner catOwner = petOwnerService.findCatOwnerById(ownerId);
//        DogOwner dogOwner = petOwnerService.findDogOwnerById(ownerId);
//        if (catOwner != null) {
//            LocalDate currentDate = catOwner.getEndTrialPeriod();
//            Calendar c = Calendar.getInstance();
//            c.setTime(currentDate);
//
//
//        }
//
//
//    }

    /**
     * метод для волонтера, для отправки усыновителю предупреждения о том,
     * что отчет заполняется плохо
     */
    public void sendWarningByVolunteer() {

    }

    /**
     * метод определяет усыновителей , у которых по времени пройден испытательный срок
     * метод запускается по графику каждый день
     * Если усыновтели найдены, то отправить информацию волонтеру для проверки
     *
     * @param allPetOwners - все усыновители
     * @return список усыновителей
     */
    public List<DogOwner> chechProbationaryPeriod(List<DogOwner> allPetOwners) {
        return null;
    }

    /**
     * метод направляет информацию волонтеру об усыновителях,
     * у которых закончился испытательный срок
     */
    public void notifyVolunteerAboutOwnerEndedProbablyPeriod() {

    }

    /**
     * метод поздравляет усыновителя с успешным окончанием испытательного срока
     */
    public void congratulateOwner() {

    }

    /**
     * метод сообщает усыновителю что ему назначены
     * дополнительные дни исптытального срока в определенном количестве дней
     */
    public void reportAddProbationaryPeriod() {

    }

    /**
     * метод сообщает усыновителю что испытательный срок не пройдет
     * дает инструкции по дальнейшим шагам
     */
    public void reportProbationaryPeriodNotPassed() {

    }

    /**
     * Метод зовет волонтера в чат
     */
    public void callVolunteer() {

    }

    /**
     * метод возвращает количество прошедших часов с момента отправки последнего отчета
     *
     * @return количество часов
     */
    private int timeSinceLastReport () {
        return 0;
    }

    /**
     * метод запускается каждый час и проверяет, нет ли усыновителей,
     * которые не отправили отчет в установленный срок
     * если прошло 24 часа
     * запустить метод отправки стандартного сообщения волонтером
     * с напоминанием о необходимости отправки отчета
     * <p>
     * Если прошло более 48 часов,
     * запустить метод, который отправляет запрос волонтеру для связи с усыновителем
     */
    public void checkTime() {

    }

    /**
     * Метод выводит список всех отчетов по определенным датам.
     *
     * @return Collection
     */
    public Collection<KeepingPet> getAllKeepingPet(LocalDateTime dateTime){
        return keepingPetRepository.findKeepingPetByDateTime(dateTime);
    }

    /**
     * Метод возвращает список отчетов по айди владельца
     * @param ownerId идентификатор (id) владельца питомца
     * @throws IllegalArgumentException если по айди не найден владелец питомца
     * @return список отчетов
     */
    public Collection<KeepingPet> getAllKeepingPetByOwnerId(Long ownerId) {
        CatOwner catOwner = petOwnerService.findCatOwnerById(ownerId);
        DogOwner dogOwner = petOwnerService.findDogOwnerById(ownerId);
        if (catOwner != null) {
            return keepingPetRepository.findKeepingPetByCatOwner(catOwner);
        } else if (dogOwner != null) {
            return keepingPetRepository.findKeepingPetByDogOwner(dogOwner);
        } else {
            throw new IllegalArgumentException("Владельца с таким айди не существует");
        }
    }
}
