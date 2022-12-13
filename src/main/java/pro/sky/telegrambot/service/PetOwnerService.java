package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.repositories.CatOwnerRepository;
import pro.sky.telegrambot.repositories.DogOwnerRepository;

import java.util.Collection;

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
    public CatOwner addCatOwner(CatOwner catOwner){
        return catOwnerRepository.save(catOwner);
    }

    /**
     * Метод позволяет волонтеру добавить владельца собак в бд.
     *
     * @param dogOwner
     * @return DogOwner
     */
    public DogOwner addDogOwner(DogOwner dogOwner){
        return dogOwnerRepository.save(dogOwner);
    }

    /**
     * Возвращает список всех владельцев кошек.
     *
     * @return Collection
     */
    public Collection<CatOwner> allCatOwner(){
        return catOwnerRepository.findAll();
    }

    /**
     * Возвращает список всех владельцев собак.
     *
     * @return Collection
     */
    public Collection<DogOwner> allDogOwner(){
        return dogOwnerRepository.findAll();
    }
}
