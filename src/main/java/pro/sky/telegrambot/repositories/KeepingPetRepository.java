package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.KeepingPet;


public interface KeepingPetRepository extends JpaRepository<KeepingPet,Long> {
}
