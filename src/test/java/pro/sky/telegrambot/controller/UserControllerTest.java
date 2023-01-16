package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambot.repositories.UserRepository;
import pro.sky.telegrambot.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllOrdersTest() throws Exception{

    }

    @Test
    public void findUserByShelterTest() throws Exception{

    }


}
