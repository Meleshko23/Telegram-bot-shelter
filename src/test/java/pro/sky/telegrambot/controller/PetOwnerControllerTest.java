package pro.sky.telegrambot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    final String ownerName = "name";
    final String ownerMail = "mail";
    final String ownerPhone = "phone";
    final long ownerChatId = 2345667L;
    final long ownerId = 2222222L;
    final LocalDate startTrialPeriod = LocalDate.now();
    final LocalDate endTrialPeriod = startTrialPeriod.plusDays(30);
    final StatusTrialPeriod statusTrial = StatusTrialPeriod.CURRENT;

    CatOwner catOwner = new CatOwner();
    DogOwner dogOwner = new DogOwner();
    @BeforeEach
    private void init() {
        final long photoPetId = 10L;
        final String filePath = "filePath";
        final long fileSize = 213L;
        final String mediaType = "application-json";

        final Long petId = 1L;
        final String petName = "name";
        final int petAge = 10;
        final TypeAnimal petType = TypeAnimal.CAT;
        final String petBreed = "breed";
        final boolean petHealth = false;

        PhotoPet photoPet = new PhotoPet();
        photoPet.setId(photoPetId);
        photoPet.setFilePath(filePath);
        photoPet.setFileSize(fileSize);
        photoPet.setMediaType(mediaType);

        Pet pet = new Pet(petName, petAge, petType, petBreed, petHealth);
        pet.setId(petId);
        pet.setPhotoPets(List.of(photoPet));

        catOwner = new CatOwner(ownerName, ownerMail, ownerPhone, ownerChatId, ownerId);
        catOwner.setStartTrialPeriod(startTrialPeriod);
        catOwner.setEndTrialPeriod(endTrialPeriod);
        catOwner.setStatusTrial(statusTrial);
        catOwner.setPet(pet);

        dogOwner = new DogOwner(ownerName, ownerMail, ownerPhone, ownerChatId, ownerId);
        dogOwner.setStartTrialPeriod(startTrialPeriod);
        dogOwner.setEndTrialPeriod(endTrialPeriod);
        dogOwner.setStatusTrial(statusTrial);
        dogOwner.setPet(pet);
    }


    @Test
    public void addCatOwnerTest() throws Exception{

        String jsonOwner = objectMapper.writeValueAsString(catOwner);

        when(catOwnerRepository.save(catOwner)).thenReturn(catOwner);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pet_owner/cat_owner")
                        .content(jsonOwner)
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

        String jsonOwner = objectMapper.writeValueAsString(dogOwner);

        when(dogOwnerRepository.save(dogOwner)).thenReturn(dogOwner);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pet_owner/dog_owner")
                        .content(jsonOwner)
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
    public void getAllCatOwnerTest() throws Exception{

        when(catOwnerRepository.findAll()).thenReturn(List.of(catOwner));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/all_cat_owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(ownerName))
                .andExpect(jsonPath("$[0].mail").value(ownerMail))
                .andExpect(jsonPath("$[0].phone").value(ownerPhone))
                .andExpect(jsonPath("$[0].chatId").value(ownerChatId))
                .andExpect(jsonPath("$[0].id").value(ownerId));
    }

    @Test
    public void getAllDogOwnerTest() throws Exception{

        when(dogOwnerRepository.findAll()).thenReturn(List.of(dogOwner));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/all_dog_owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(ownerName))
                .andExpect(jsonPath("$[0].mail").value(ownerMail))
                .andExpect(jsonPath("$[0].phone").value(ownerPhone))
                .andExpect(jsonPath("$[0].chatId").value(ownerChatId))
                .andExpect(jsonPath("$[0].id").value(ownerId));
    }

    @Test
    public void getDogOwnersEndTrialPeriodWhenEmptyListTest() throws Exception{

        when(dogOwnerRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/dog/end_trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getDogOwnersEndTrialPeriodWhenStatusPassed() throws Exception{

        dogOwner.setEndTrialPeriod(LocalDate.now().minusDays(1));
        dogOwner.setStatusTrial(StatusTrialPeriod.SUCCESS_PASSED);
        List<DogOwner> dogOwnerList = new ArrayList<>(List.of(dogOwner));
        when(dogOwnerRepository.findAll()).thenReturn(dogOwnerList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/dog/end_trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getDogOwnersEndTrialPeriodPositiveTest() throws Exception{

        dogOwner.setEndTrialPeriod(LocalDate.now().minusDays(1));
        dogOwner.setStatusTrial(StatusTrialPeriod.CURRENT);
        DogOwner dogOwner2 = dogOwner;
        dogOwner.setEndTrialPeriod(LocalDate.now().minusDays(2));
        dogOwner.setStatusTrial(StatusTrialPeriod.EXTENDED_14_DAYS);
        List<DogOwner> dogOwnerList = new ArrayList<>(List.of(dogOwner, dogOwner2));
        when(dogOwnerRepository.findAll()).thenReturn(dogOwnerList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/dog/end_trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(ownerName))
                .andExpect(jsonPath("$[0].mail").value(ownerMail))
                .andExpect(jsonPath("$[0].phone").value(ownerPhone))
                .andExpect(jsonPath("$[0].chatId").value(ownerChatId))
                .andExpect(jsonPath("$[0].id").value(ownerId));
    }

    @Test
    public void getCatOwnersEndTrialPeriodWhenEmptyListTest() throws Exception{

        when(catOwnerRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/cat/end_trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getCatOwnersEndTrialPeriodWhenStatusPassed() throws Exception{

        catOwner.setEndTrialPeriod(LocalDate.now().minusDays(1));
        catOwner.setStatusTrial(StatusTrialPeriod.SUCCESS_PASSED);
        List<CatOwner> catOwnerList = new ArrayList<>(List.of(catOwner));
        when(catOwnerRepository.findAll()).thenReturn(catOwnerList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/cat/end_trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void getCatOwnersEndTrialPeriodPositiveTest() throws Exception{

        catOwner.setEndTrialPeriod(LocalDate.now().minusDays(1));
        catOwner.setStatusTrial(StatusTrialPeriod.CURRENT);
        CatOwner catOwner2 = catOwner;
        catOwner2.setEndTrialPeriod(LocalDate.now().minusDays(2));
        catOwner2.setStatusTrial(StatusTrialPeriod.EXTENDED_14_DAYS);
        List<CatOwner> catOwnerList = new ArrayList<>(List.of(catOwner, catOwner2));
        when(catOwnerRepository.findAll()).thenReturn(catOwnerList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet_owner/cat/end_trial_period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(ownerName))
                .andExpect(jsonPath("$[0].mail").value(ownerMail))
                .andExpect(jsonPath("$[0].phone").value(ownerPhone))
                .andExpect(jsonPath("$[0].chatId").value(ownerChatId))
                .andExpect(jsonPath("$[0].id").value(ownerId));
    }

    @Test
    public void changeStatusTrialPeriodCatWhenOwnerNotFoundTest() throws Exception{

        when(catOwnerRepository.findCatOwnerById(ownerId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/pet_owner/cat/{ownerId}?new_STP={new_STP}", ownerId, StatusTrialPeriod.CURRENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void changeStatusTrialPeriodCatWhenIllegalArgument() throws Exception{

        when(catOwnerRepository.findCatOwnerById(ownerId)).thenReturn(null);
        String illegalSTP = "illegal Argument";

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/pet_owner/cat/{ownerId}?new_STP={new_STP}", ownerId, illegalSTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void changeStatusTrialPeriodCatPositiveTest() throws Exception{

        when(catOwnerRepository.findCatOwnerById(ownerId)).thenReturn(catOwner);
        String newSTP = "SUCCESS_PASSED";

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/pet_owner/cat/{ownerId}?new_STP={new_STP}", ownerId, newSTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusTrial").value(newSTP));
    }

    @Test
    public void changeStatusTrialPeriodDogPositiveTest() throws Exception{
        when(dogOwnerRepository.findDogOwnerById(ownerId)).thenReturn(dogOwner);
        String newSTP = "SUCCESS_PASSED";

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/pet_owner/dog/{ownerId}?new_STP={new_STP}", ownerId, newSTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusTrial").value(newSTP));

    }

}
