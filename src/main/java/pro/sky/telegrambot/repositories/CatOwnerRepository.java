package pro.sky.telegrambot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.CatOwner;


public interface CatOwnerRepository extends JpaRepository<CatOwner,Long>{
}
