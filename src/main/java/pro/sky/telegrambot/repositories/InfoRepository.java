package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Info;

public interface InfoRepository extends JpaRepository<Info,Long> {

    String findInfoByName(String name);
}
