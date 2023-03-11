package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.repositories.CatOwnerRepository;
import pro.sky.telegrambot.repositories.DogOwnerRepository;

@ExtendWith(MockitoExtension.class)
public class PetOwnerServiceTest {

    @InjectMocks
    private PetOwnerService petOwnerService;

    @Mock
    private CatOwnerRepository catOwnerRepository;
    private DogOwnerRepository dogOwnerRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void addCatOwnerTest(){

    }

    @Test
    public void addDogOwnerTest(){

    }

    @Test
    public void allCatOwnerTest(){

    }

    @Test
    public void allDogOwnerTest(){

    }

    @Test
    public void findCatOwnerTest(){

    }

    @Test
    public void findDogOwnerTest(){

    }

    @Test
    public void findCatOwnerByIdTest(){

    }
    @Test
    public void findDogOwnerByIdTest(){

    }

    @Test
    public void getDogOwnersEndTrialPeriodTest(){

    }

    @Test
    public void getCatOwnersEndTrialPeriodTest(){

    }

    @Test
    public void changeStatusTrialPeriodDogTest(){

    }

    @Test
    public void changeStatusTrialPeriodCatTest(){

    }

    @Test
    public void sendMessageTest(){

    }

}
