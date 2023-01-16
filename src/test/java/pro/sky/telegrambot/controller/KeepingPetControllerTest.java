package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambot.repositories.KeepingPetRepository;
import pro.sky.telegrambot.service.KeepingPetService;

@WebMvcTest(controllers = KeepingPetController.class)
public class KeepingPetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeepingPetRepository keepingPetRepository;

    @SpyBean
    private KeepingPetService keepingPetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllKeepingPetTest() throws Exception{

    }

    @Test
    public void getAllKeepingPetByOwnerIdTest() throws Exception{

    }

    @Test
    public void sendWarningByVolunteerTest() throws Exception{

    }

}
