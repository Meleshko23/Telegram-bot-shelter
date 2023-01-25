package pro.sky.telegrambot.controller;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.repositories.UserRepository;
import pro.sky.telegrambot.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TelegramBot telegramBot;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private UserService userService;

    @Test
    public void getAllOrdersPositiveTest() throws Exception{

        final Long chatId = 232L;
        final String shelter = "DOG";
        final String name = "Ruslan";
        final String phone = "userPhone";
        final String mail = "userMail";

        User user = new User(chatId, shelter, name, phone, mail);
        List<User> users = new ArrayList<>(List.of(user));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders/all_orders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].chatId").value(chatId))
                .andExpect(jsonPath("$[0].shelter").value(shelter))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].phone").value(phone))
                .andExpect(jsonPath("$[0].mail").value(mail));

    }

    @Test
    public void getAllOrdersTestWhenReturnEmptyList() throws Exception{

        Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders/all_orders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findUserByShelterPositiveTest() throws Exception{

        final Long chatId = 232L;
        final String shelter = "DOG";
        final String name = "Ruslan";
        final String phone = "userPhone";
        final String mail = "userMail";

        User user = new User(chatId, shelter, name, phone, mail);
        List<User> users = new ArrayList<>(List.of(user));

        Mockito.when(userRepository.findUserByShelter(shelter)).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders/shelter?shelter={shelter}", shelter)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].chatId").value(chatId))
                .andExpect(jsonPath("$[0].shelter").value(shelter))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].phone").value(phone))
                .andExpect(jsonPath("$[0].mail").value(mail));

    }

    @Test
    public void findUserByShelterTestWhenReturnEmptyList() throws Exception{

        final String shelter = "DOG";
        Mockito.when(userRepository.findUserByShelter(shelter)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders/shelter?shelter={shelter}", shelter)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
