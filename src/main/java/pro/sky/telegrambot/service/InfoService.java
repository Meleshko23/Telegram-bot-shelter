package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Info;
import pro.sky.telegrambot.model.Pet;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repositories.InfoRepository;
import pro.sky.telegrambot.repositories.PetRepository;
import pro.sky.telegrambot.repositories.VolunteerRepository;

import java.util.Collection;


/**
 * Сервис который осдержит методы по добавлению питомцев, волонтеров и необходимой информации для
 * InfoPetsService и InfoShelterService
 * @see InfoPetsService
 * @see InfoShelterService
 */
@Service
public class InfoService {

    private InfoRepository infoRepository;
    private PetRepository petRepository;
    private VolunteerRepository volunteerRepository;

    public InfoService(InfoRepository infoRepository, PetRepository petRepository, VolunteerRepository volunteerRepository) {
        this.infoRepository = infoRepository;
        this.petRepository = petRepository;
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Метод добавляет питомца в базу данных.
     *
     * @param pet
     * @return Pet
     */
    public Pet addPet(Pet pet){
        return pet;
    }

    /**
     * Метод добавляет волонтера в базу данных.
     *
     * @param volunteer
     * @return Volunteer
     */
    public Volunteer addVolunteer(Volunteer volunteer){
        return volunteer;
    }

    /**
     * Метод обновляет необходимую информацию в базу данных.
     *
     * @param info где, info.getName() является ключом и при нахождении обновляет информацию
     * @return Info
     */
    public Info editInfo(Info info){
        if(infoRepository.findInfoByName(info.getName()) == null){
            //возвращает ошибку
        }
        return info;
    }

    /**
     * Метод выводит весь список питомцев
     *
     * @return Collection
     */
    public Collection<Pet> getAllPet(){
        return petRepository.findAll();
    }

    /**
     * Метод выводит весь список волонтеров.
     *
     * @return Collection
     */
    public Collection<Volunteer> getAllVolunteer(){
        return volunteerRepository.findAll();
    }

    /**
     * Метод показывает список доступной информации из базы данных
     *
     * @return Collection
     */
    public Collection<Info> getAllInfo(){
        return infoRepository.findAll();
    }
}
