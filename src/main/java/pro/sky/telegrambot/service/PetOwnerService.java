package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.StatusTrialPeriod;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.repositories.CatOwnerRepository;
import pro.sky.telegrambot.repositories.DogOwnerRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetOwnerService {

    private final CatOwnerRepository catOwnerRepository;
    private final DogOwnerRepository dogOwnerRepository;

    public PetOwnerService(CatOwnerRepository petOwnerRepository, DogOwnerRepository dogOwnerRepository) {
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
     * @param chatId идентификатор чата владельца собаки
     * @return владелец собаки
     */
    public DogOwner findDogOwner(long chatId) {
        return dogOwnerRepository.findDogOwnerByChatId(chatId);
    }

    /**
     * Метод ищет хозяина кошки по его chatId
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
                .filter(owner -> owner.getStatusTrial().equals(StatusTrialPeriod.CURRENT) ||
                        owner.getStatusTrial().equals(StatusTrialPeriod.EXTENDED_14_DAYS) ||
                        owner.getStatusTrial().equals(StatusTrialPeriod.EXTENDED_30_DAYS))
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
                .filter(owner -> owner.getStatusTrial().equals(StatusTrialPeriod.CURRENT) ||
                        owner.getStatusTrial().equals(StatusTrialPeriod.EXTENDED_14_DAYS) ||
                        owner.getStatusTrial().equals(StatusTrialPeriod.EXTENDED_30_DAYS))
                .collect(Collectors.toList());
    }

//    public Object findOwnerByChatID(Long chatID) {
//
//
//    }

}
