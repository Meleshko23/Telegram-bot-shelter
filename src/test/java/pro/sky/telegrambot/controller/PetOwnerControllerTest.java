package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pengrad.telegrambot.TelegramBot;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.constant.StatusTrialPeriod;
import pro.sky.telegrambot.constant.TypeAnimal;
import pro.sky.telegrambot.model.CatOwner;
import pro.sky.telegrambot.model.DogOwner;

import pro.sky.telegrambot.model.Pet;
import pro.sky.telegrambot.model.PhotoPet;
import pro.sky.telegrambot.repositories.CatOwnerRepository;
import pro.sky.telegrambot.repositories.DogOwnerRepository;
import pro.sky.telegrambot.service.PetOwnerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PetOwnerController.class)
public class PetOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatOwnerRepository catOwnerRepository;
    @MockBean
    private DogOwnerRepository dogOwnerRepository;
    @MockBean
    private TelegramBot telegramBot;

    @SpyBean
    private PetOwnerService petOwnerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addCatOwnerTest() throws Exception{

        final long photoPetId = 10L;
        final String filePath = "filePath";
        final long fileSize = 213L;
        final String mediaType = "application-json";

        PhotoPet photoPet = new PhotoPet();
        photoPet.setId(photoPetId);
        photoPet.setFilePath(filePath);
        photoPet.setFileSize(fileSize);
        photoPet.setMediaType(mediaType);

        JSONObject photoPetObject = new JSONObject();
        photoPetObject.put("id", photoPetId);
        photoPetObject.put("filePath", filePath);
        photoPetObject.put("fileSize", fileSize);
        photoPetObject.put("mediaType", mediaType);

        final Long petId = 1L;
        final String petName = "name";
        final int petAge = 10;
        final TypeAnimal petType = TypeAnimal.CAT;
        final String petBreed = "breed";
        final boolean petHealth = false;
        Pet pet = new Pet(petName, petAge, petType, petBreed, petHealth);
        pet.setId(petId);
        pet.setPhotoPets(List.of(photoPet));

        JSONObject petObject = new JSONObject();
        petObject.put("id", petId);
        petObject.put("name", petName);
        petObject.put("age", petAge);
        petObject.put("type", petType);
        petObject.put("breed", petBreed);
        petObject.put("photoPets", photoPetObject);
        petObject.put("health_restrictions", petHealth);

        final String ownerName = "name";
        final String ownerMail = "mail";
        final String ownerPhone = "phone";
        final long ownerChatId = 2345667L;
        final long ownerId = 2222222L;
        final LocalDate startTrialPeriod = LocalDate.now();
        final LocalDate endTrialPeriod = startTrialPeriod.plusDays(30);
        final StatusTrialPeriod statusTrial = StatusTrialPeriod.CURRENT;

        CatOwner catOwner = new CatOwner(ownerName, ownerMail, ownerPhone, ownerChatId, ownerId);
        catOwner.setStartTrialPeriod(startTrialPeriod);
        catOwner.setEndTrialPeriod(endTrialPeriod);
        catOwner.setStatusTrial(statusTrial);
        catOwner.setPet(pet);

        JSONObject ownerObject = new JSONObject();
        ownerObject.put("id", ownerId);
        ownerObject.put("chatId", ownerChatId);
        ownerObject.put("name", ownerName);
        ownerObject.put("mail", ownerMail);
        ownerObject.put("phone", ownerPhone);
        ownerObject.put("startTrialPeriod", startTrialPeriod);
        ownerObject.put("endTrialPeriod", endTrialPeriod);
        ownerObject.put("statusTrial", statusTrial);
        ownerObject.put("pet", petObject);


//        DogOwner dogOwner = new DogOwner(ownerName, ownerMail, ownerPhone, ownerChatId, ownerId);

        when(catOwnerRepository.save(catOwner)).thenReturn(catOwner);
//        when(dogOwnerRepository.save(any(DogOwner.class))).thenReturn(dogOwner);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pet_owner/cat_owner")
                        .content(ownerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(ownerName))
                .andExpect(jsonPath("$.mail").value(ownerMail))
                .andExpect(jsonPath("$.phone").value(ownerPhone))
                .andExpect(jsonPath("$.chatId").value(ownerChatId))
                .andExpect(jsonPath("$.id").value(ownerId));
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
