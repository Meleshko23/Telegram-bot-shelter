package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambot.repositories.PhotoPetRepository;
import pro.sky.telegrambot.service.PhotoPetService;

@WebMvcTest(controllers = PhotoPetController.class)
public class PhotoPetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhotoPetRepository photoPetRepository;

    @SpyBean
    private PhotoPetService photoPetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void uploadAvatarTest() throws Exception{

    }


}
