package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;
import pro.sky.telegrambot.model.KeepingPet;
import pro.sky.telegrambot.service.KeepingPetService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface KeepingPetRepository extends JpaRepository<KeepingPet,Long> {
    KeepingPet findKeepingPetById(long id);

    Collection<KeepingPet> findKeepingPetByDateTimeBetween(LocalDateTime begin, LocalDateTime end);

    Collection<KeepingPet> findKeepingPetByDogOwner(DogOwner dogOwner);
    Collection<KeepingPet> findKeepingPetByCatOwner(CatOwner catOwner);
    @Query(value = "select * from keeping_pet k inner join (select cat_owner_id, max(date_time) as MaxDate from keeping_pet group by cat_owner_id) tm on k.cat_owner_id = tm.cat_owner_id and k.date_time = tm.MaxDate", nativeQuery = true)
    List<KeepingPet> findLastKeepingPetCatOwner();

    @Query(value = "select * from keeping_pet k inner join (select dog_owner_id, max(date_time) as MaxDate from keeping_pet group by dog_owner_id) tm on k.dog_owner_id = tm.dog_owner_id and k.date_time = tm.MaxDate", nativeQuery = true)
    List<KeepingPet> findLastKeepingPetDogOwner();
}
