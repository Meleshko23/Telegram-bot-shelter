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

import java.util.ArrayList;
import java.util.List;
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
    private static String SHELTER = "shelter";
    private User user = new User();
    private User user1 = new User();
    private User user2 = new User();
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    public void init() {
        user1.setName("name");user1.setPhone("phone");user1.setMail("mail");user1.setChatId(1213L);user1.setShelter(SHELTER);
        user2.setName("name");user2.setPhone("phone");user2.setMail("mail");user2.setChatId(1213L);user2.setShelter("shelter1");
        user.setName(NAME1);
        user.setPhone(PHONE1);
        user.setMail(MAIL1);
        user.setChatId(CHAT_ID);
        user.setShelter(SHELTER);
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
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

    @Test
    public void getAllOrdersTest(){
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Assertions.assertThat(userList).isEqualTo(userService.getAllOrders());
    }

    @Test
    public void findUserByShelterTest(){
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        Mockito.when(userRepository.findUserByShelter(user.getShelter())).thenReturn(users);
        Assertions.assertThat(users).isEqualTo(userService.findUserByShelter(user.getShelter()));
    }

    @Test
    public void findUserByChatIdTest(){
        Mockito.when(userRepository.findUserByChatId(user.getChatId())).thenReturn(user);
        Assertions.assertThat(user).isEqualTo(userService.findUserByChatId(user.getChatId()));
    }
}