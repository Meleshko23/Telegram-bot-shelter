package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.model.KeepingPet;
import pro.sky.telegrambot.repositories.KeepingPetRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Сервис, описывающий методы по ведению питомца хозяевами
 */
@Service
public class KeepingPetService {

    private String textReport;
    private Integer fileId;
    private String photoesDir = "/photoPets";

    private final PetOwnerService petOwnerService;
    private final KeepingPetRepository keepingPetRepository;

    @Autowired
    private TelegramBot telegramBot;

    public KeepingPetService(PetOwnerService petOwnerService, KeepingPetRepository keepingPetRepository) {
        this.petOwnerService = petOwnerService;
        this.keepingPetRepository = keepingPetRepository;
    }

    /**
     * метод отправляет усыновителям форму ежедневного <b>отчета</b>
     *
     * @param
     * @return void
     */
    public void getFormDailyReport() {

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

//        условие по шелтеру???.....

        return keepingPetRepository.save(keepingPet);
    }

    private KeepingPet getNewReport(Long chatId, PhotoSize[] photoSizes, String caption) {
        KeepingPet keepingPet = new KeepingPet();
        keepingPet.setChatId(chatId);
        keepingPet.setDate(LocalDate.now());
        keepingPet.setInfoPet(caption);
//        keepingPet.setPhotoPet(photoSizes);

        return keepingPet;
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


    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    private void sendMessageReply(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        sendMess.replyMarkup(new ForceReply());
        telegramBot.execute(sendMess);
    }

    private void sendMessage(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMess);
    }

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
    private int timeSinceLastReport() {
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

}
