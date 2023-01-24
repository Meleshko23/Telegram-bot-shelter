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
import pro.sky.telegrambot.model.*;
import pro.sky.telegrambot.repositories.*;
import pro.sky.telegrambot.service.KeepingPetService;
import pro.sky.telegrambot.service.PetOwnerService;
import pro.sky.telegrambot.service.PetsService;
import pro.sky.telegrambot.service.PhotoPetService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KeepingPetController.class)
public class KeepingPetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TelegramBot telegramBot;

    @SpyBean
    private KeepingPetService keepingPetService;
    @MockBean
    private KeepingPetRepository keepingPetRepository;

    @SpyBean
    private PetOwnerService petOwnerService;
    @MockBean
    private CatOwnerRepository catOwnerRepository;
    @MockBean
    private DogOwnerRepository dogOwnerRepository;

    @SpyBean
    private PhotoPetService photoPetService;
    @MockBean
    private PhotoPetRepository photoPetRepository;

    @SpyBean
    private PetsService petsService;
    @MockBean
    private PetRepository petRepository;

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
    public void getAllKeepingWhenIllegalDateTest() throws Exception {

        final String date = "Illegal Date Format";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/keeping_pet/{date}", date)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllKeepingPetWhenEmptyCollectionTest() throws Exception {

        final String date = "2023-01-23";
        final LocalDate currentDate = LocalDate.parse(date);
        final LocalDateTime startDateTime = currentDate.atStartOfDay();
        final LocalDateTime endDateTime = currentDate.atStartOfDay().plusDays(1).minusSeconds(1);

        when(keepingPetRepository.findKeepingPetByDateTimeBetween(startDateTime, endDateTime)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/keeping_pet/{date}", date)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllKeepingPetPositiveTest() throws Exception {
        final long id = 100L;
        final long chatId = 817781679;
        final String infoPet = "testInfo";
        final PhotoPet photoPet = new PhotoPet();
        final String date = "2023-01-23";
        final boolean quality = true;
        final LocalDate currentDate = LocalDate.parse(date);
        final LocalDateTime startDateTime = currentDate.atStartOfDay();
        final LocalDateTime endDateTime = currentDate.plusDays(1).atStartOfDay();

        KeepingPet keepingPet = new KeepingPet(id, chatId, infoPet, photoPet, startDateTime);
        keepingPet.setQuality(quality);
        Collection<KeepingPet> keepingPets = new ArrayList<>(List.of(keepingPet));

        when(keepingPetRepository.findKeepingPetByDateTimeBetween(startDateTime, endDateTime)).thenReturn(keepingPets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/keeping_pet/{date}", date)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllKeepingPetByOwnerIdTest() throws Exception {
        final long id = 100L;
        final long chatId = 817781679;
        final String infoPet = "testInfo";
        final PhotoPet photoPet = new PhotoPet();
        final String date = "2023-01-23";
        final boolean quality = true;
        final LocalDate currentDate = LocalDate.parse(date);
        final LocalDateTime startDateTime = currentDate.atStartOfDay();

        KeepingPet keepingPet = new KeepingPet(id, chatId, infoPet, photoPet, startDateTime);
        keepingPet.setQuality(quality);
        Collection<KeepingPet> keepingPets = new ArrayList<>(List.of(keepingPet));

        when(keepingPetRepository.findKeepingPetByCatOwner(catOwner)).thenReturn(keepingPets);
        when(keepingPetRepository.findKeepingPetByDogOwner(dogOwner)).thenReturn(keepingPets);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/keeping_pet/owner/{id}", ownerId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void sendWarningByVolunteerTest() throws Exception {

    }

}
