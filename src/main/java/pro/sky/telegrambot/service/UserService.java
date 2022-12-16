package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.MessageForSaveContacts;
import pro.sky.telegrambot.exception.UserNotFoundException;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repositories.UserRepository;

import static pro.sky.telegrambot.constant.MessageForSaveContacts.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private TelegramBot telegramBot;

    private static String name = null;
    private static String phone = null;
    private static String mail = null;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    /**
     * Метод принимает и записывает контактные данные пользователя в базу данных
     */
    public void saveContactInfo(long chatId, String messageText, String userRequest) {
        if (messageText.equals(NAME)) {
            name = null;
            phone = null;
            mail = null;
            sendMessageReply(chatId, messageText);
        }else if (messageText.equals(PHONE)) {
            name = userRequest;
            sendMessageReply(chatId, messageText);
        } else if (messageText.equals(MAIL)) {
            phone = userRequest;
            sendMessageReply(chatId, messageText);
        } else if (messageText.equals(SAVE)) {
            mail = userRequest;

            User user = new User();

            user.setChatId(chatId);
            user.setName(name);
            user.setPhone(phone);
            user.setMail(mail);
            saveUser(user);
            sendMessage(chatId, messageText);
        }

    }

    private void sendMessageReply(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        sendMess.replyMarkup(new ForceReply());
        telegramBot.execute(sendMess);
    }
    private void sendMessage(long chatId, String messageText) {
        SendMessage sendMess = new SendMessage(chatId, messageText);
        telegramBot.execute(sendMess);
    }


    public User findUser(long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException());
    }
}
