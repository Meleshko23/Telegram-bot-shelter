package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import pro.sky.telegrambot.repositories.CatOwnerRepository;
import pro.sky.telegrambot.repositories.DogOwnerRepository;
import pro.sky.telegrambot.service.PetOwnerService;

@WebMvcTest(controllers = PetOwnerController.class)
public class PetOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatOwnerRepository catOwnerRepository;
    private DogOwnerRepository dogOwnerRepository;

    @SpyBean
    private PetOwnerService petOwnerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addCatOwnerTest() throws Exception{

    }

    @Test
    public void addDogOwnerTest() throws Exception{

    }

    @Test
    public void getAllCatOwnerTest() throws Exception{

    }

    @Test
    public void getAllDogOwnerTest() throws Exception{

    }

    @Test
    public void getDogOwnersEndTrialPeriodTest() throws Exception{

    }

    @Test
    public void getCatOwnersEndTrialPeriodTest() throws Exception{

    }

    @Test
    public void changeStatusTrialPeriodCatTest() throws Exception{

    }

    @Test
    public void changeStatusTrialPeriodDogTest() throws Exception{

    }

}
