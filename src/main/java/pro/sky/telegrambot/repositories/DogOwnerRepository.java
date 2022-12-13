package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.DogOwner;

public interface DogOwnerRepository extends JpaRepository<DogOwner,Long> {
}
