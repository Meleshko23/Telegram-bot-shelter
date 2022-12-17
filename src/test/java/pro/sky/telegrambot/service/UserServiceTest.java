package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pro.sky.telegrambot.constant.MessageForSaveContacts;
import pro.sky.telegrambot.exception.UserNotFoundException;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repositories.UserRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
//    @Mock
//    private TelegramBot telegramBot;


    @InjectMocks
    private UserService userService;

    private static String NAME1 = "name1";
    private static String PHONE1 = "111";
    private static String MAIL1 = "1@ya.ru";
    private static long CHAT_ID = 123456789;
    private static long CHAT_ID_NULL = 987654321;
    private User user = new User();

    @BeforeEach
    public void init() {

        user.setName(NAME1);
        user.setPhone(PHONE1);
        user.setMail(MAIL1);
        user.setChatId(CHAT_ID);


    }


    @Test
    void saveUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertThat(user).isEqualTo(userService.saveUser(user));
    }

//    @Test
//    void saveContactInfo() {
//
//        Mockito.when(telegramBot.execute(any(SendMessage.class))).thenReturn()
//        userService.saveContactInfo(CHAT_ID, MessageForSaveContacts.NAME, null);
//        Assertions.assertThat(userService.getName()).isNull();
//        Assertions.assertThat(userService.getPhone()).isNull();
//        Assertions.assertThat(userService.getMail()).isNull();
//    }

    @Test
    void findUser() {
        Mockito.when(userRepository.findById(CHAT_ID)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(CHAT_ID_NULL)).thenReturn(Optional.empty());
        Assertions.assertThat(user).isEqualTo(userService.findUser(CHAT_ID));
        Assertions.assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> userService.findUser(CHAT_ID_NULL));
    }
}