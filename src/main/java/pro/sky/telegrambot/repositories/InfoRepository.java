package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Info;
import pro.sky.telegrambot.model.Volunteer;

public interface InfoRepository extends JpaRepository<Info,Long> {

    Info findInfoByName(String name);
}
