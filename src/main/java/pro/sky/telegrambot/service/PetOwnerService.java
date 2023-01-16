package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.StatusTrialPeriod;
import pro.sky.telegrambot.exception.OwnerNotFoundException;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.repositories.CatOwnerRepository;
import pro.sky.telegrambot.repositories.DogOwnerRepository;
import pro.sky.telegrambot.repositories.VolunteerRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static pro.sky.telegrambot.constant.StatusTrialPeriod.*;

@Service
public class PetOwnerService {

    @Autowired
    private TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;

    private final CatOwnerRepository catOwnerRepository;
    private final DogOwnerRepository dogOwnerRepository;

    private final String statusCURRENT = "Статус нельзя поменять на CURRENT!";
    private final String statusNOT_PASSED = "Испытательный период не пройден";
    private final String statusSUCCESS_PASSED = "Испытательный период пройден успешно";
    private final String statusEXTENDED_14_DAYS = "Испытательный период продлен на 14 дней";
    private final String statusEXTENDED_30_DAYS = "Испытательный период продлен на 30 дней";
    private final String unCorrectedStatus = "Некорректный новый статус";

    public PetOwnerService(VolunteerRepository volunteerRepository, CatOwnerRepository petOwnerRepository, DogOwnerRepository dogOwnerRepository) {
        this.volunteerRepository = volunteerRepository;
        this.catOwnerRepository = petOwnerRepository;
        this.dogOwnerRepository = dogOwnerRepository;
    }

    /**
     * Метод позволяет волонтеру добавить владельца кота в бд.
     *
     * @param catOwner
     * @return CatOwner
     */
    public CatOwner addCatOwner(CatOwner catOwner) {
        return catOwnerRepository.save(catOwner);
    }

    /**
     * Метод позволяет волонтеру добавить владельца собак в бд.
     *
     * @param dogOwner
     * @return DogOwner
     */
    public DogOwner addDogOwner(DogOwner dogOwner) {
        return dogOwnerRepository.save(dogOwner);
    }

    /**
     * Метод ищет хозяина собаки по его chatId
     *
     * @param chatId идентификатор чата владельца собаки
     * @return владелец собаки
     */
    public DogOwner findDogOwner(long chatId) {
        return dogOwnerRepository.findDogOwnerByChatId(chatId);
    }

    /**
     * Метод ищет хозяина кошки по его chatId
     *
     * @param chatId идентификатор чата владельца кошки
     * @return владелец кошки
     */
    public CatOwner findCatOwner(long chatId) {
        return catOwnerRepository.findCatOwnerByChatId(chatId);
    }

    /**
     * Возвращает список всех владельцев кошек.
     *
     * @return Collection
     */
    public Collection<CatOwner> allCatOwner() {
        return catOwnerRepository.findAll();
    }

    /**
     * Возвращает список всех владельцев собак.
     *
     * @return Collection
     */
    public Collection<DogOwner> allDogOwner() {
        return dogOwnerRepository.findAll();
    }

    public CatOwner findCatOwner(Long chatID) {
        return catOwnerRepository.findCatOwnerByChatId(chatID);
    }

    public DogOwner findDogOwner(Long chatID) {
        return dogOwnerRepository.findDogOwnerByChatId(chatID);
    }

    public CatOwner findCatOwnerById(Long id) {
        return catOwnerRepository.findCatOwnerById(id);
    }

    public DogOwner findDogOwnerById(Long id) {
        return dogOwnerRepository.findDogOwnerById(id);
    }

    /**
     * Метод возвращает список владельцев собак, у которых завершился испытательный период.
     *
     * @return список владельцев собак
     */
    public List<DogOwner> getDogOwnersEndTrialPeriod() {
        return allDogOwner().stream()
                .filter(owner -> owner.getEndTrialPeriod().isBefore(LocalDate.now()))
                .filter(owner -> owner.getStatusTrial().equals(CURRENT) ||
                        owner.getStatusTrial().equals(EXTENDED_14_DAYS) ||
                        owner.getStatusTrial().equals(EXTENDED_30_DAYS))
                .collect(Collectors.toList());
    }

    /**
     * Метод возвращает список владельцев кошек, у которых завершился испытательный период.
     *
     * @return список владельцев кошек
     */
    public List<CatOwner> getCatOwnersEndTrialPeriod() {
        return allCatOwner().stream()
                .filter(owner -> owner.getEndTrialPeriod().isBefore(LocalDate.now()))
                .filter(owner -> owner.getStatusTrial().equals(CURRENT) ||
                        owner.getStatusTrial().equals(EXTENDED_14_DAYS) ||
                        owner.getStatusTrial().equals(EXTENDED_30_DAYS))
                .collect(Collectors.toList());
    }

    /**
     * метод изменяет статус испытательного периода владельца собаки на другой статус.
     * При необходимости изменяет дату окончания испытательного периода
     * Измененный объект сохраняет в БД
     *
     * @param ownerId идентификтор владельца питомца
     * @param stp     новый статус испытательного периода
     * @return Владелец питомца с новым статусом и датой окончания испытательног периода
     * @throws IllegalArgumentException если неверный параметр (например, CURRENT)
     * @throws OwnerNotFoundException   если владелец питомца не найден по его идентификатору
     */
    public DogOwner changeStatusTrialPeriodDog(long ownerId, StatusTrialPeriod stp) {
        DogOwner dogOwner = dogOwnerRepository.findDogOwnerById(ownerId);
        if (dogOwner == null) {
            throw new OwnerNotFoundException();
        } else {
            long chatId = dogOwner.getChatId();
            LocalDate curentDate = null;

            switch (stp) {
                case CURRENT:
                    throw new IllegalArgumentException(statusCURRENT);
                case SUCCESS_PASSED:
                    dogOwner.setStatusTrial(SUCCESS_PASSED);
                    sendMessage(chatId, statusSUCCESS_PASSED);
                    break;
                case NOT_PASSED:
                    dogOwner.setStatusTrial(NOT_PASSED);
                    sendMessage(chatId, statusNOT_PASSED);
                    break;
                case EXTENDED_14_DAYS:
                    curentDate = dogOwner.getEndTrialPeriod();
                    dogOwner.setEndTrialPeriod(curentDate.plusDays(14));
                    dogOwner.setStatusTrial(EXTENDED_14_DAYS);
                    sendMessage(chatId, statusEXTENDED_14_DAYS);
                    break;
                case EXTENDED_30_DAYS:
                    curentDate = dogOwner.getEndTrialPeriod();
                    dogOwner.setEndTrialPeriod(curentDate.plusDays(30));
                    dogOwner.setStatusTrial(EXTENDED_30_DAYS);
                    sendMessage(chatId, statusEXTENDED_30_DAYS);
                    break;
                default:
                    throw new IllegalArgumentException(unCorrectedStatus);
            }
            dogOwnerRepository.save(dogOwner);
        }
        return dogOwner;
    }

    /**
     * метод изменяет статус испытательного периода владельца кошки на другой статус.
     * При необходимости изменяет дату окончания испытательного периода
     * Измененный объект сохраняет в БД
     *
     * @param ownerId идентификтор владельца питомца
     * @param stp     новый статус испытательного периода
     * @return Владелец питомца с новым статусом и датой окончания испытательног периода
     * @throws IllegalArgumentException если неверный параметр (например, CURRENT)
     * @throws OwnerNotFoundException   если владелец питомца не найден по его идентификатору
     */
    public CatOwner changeStatusTrialPeriodCat(long ownerId, StatusTrialPeriod stp) {
        CatOwner catOwner = catOwnerRepository.findCatOwnerById(ownerId);
        if (catOwner == null) {
            throw new OwnerNotFoundException();
        } else {
            long chatId = catOwner.getChatId();
            LocalDate curentDate = null;

            switch (stp) {
                case CURRENT:
                    throw new IllegalArgumentException(statusCURRENT);
                case SUCCESS_PASSED:
                    catOwner.setStatusTrial(SUCCESS_PASSED);
                    sendMessage(chatId, statusSUCCESS_PASSED);
                    break;
                case NOT_PASSED:
                    catOwner.setStatusTrial(NOT_PASSED);
                    sendMessage(chatId, statusNOT_PASSED);
                    break;
                case EXTENDED_14_DAYS:
                    curentDate = catOwner.getEndTrialPeriod();
                    catOwner.setEndTrialPeriod(curentDate.plusDays(14));
                    catOwner.setStatusTrial(EXTENDED_14_DAYS);
                    sendMessage(chatId, statusEXTENDED_14_DAYS);
                    break;
                case EXTENDED_30_DAYS:
                    curentDate = catOwner.getEndTrialPeriod();
                    catOwner.setEndTrialPeriod(curentDate.plusDays(30));
                    catOwner.setStatusTrial(EXTENDED_30_DAYS);
                    sendMessage(chatId, statusEXTENDED_30_DAYS);
                    break;
                default:
                    throw new IllegalArgumentException(unCorrectedStatus);
            }
            catOwnerRepository.save(catOwner);
        }
        return catOwner;
    }

    /**
     * Метод отправляет сообщение пользователю в чате в телеграме
     *
     * @param chatId      идентификатор чата
     * @param messageText сообщение пользователю
     */
    private void sendMessage(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMess);
    }

}
