package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByChatId(Long chatId);
}
