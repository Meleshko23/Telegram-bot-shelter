package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.repositories.PhotoPetRepository;

@ExtendWith(MockitoExtension.class)
public class PhotoPetServiceTest {

    @InjectMocks
    private PhotoPetService photoPetService;

    @Mock
    private PhotoPetRepository photoPetRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void uploadPhotoPetTest(){

    }

    @Test
    public void getExtensionTest(){

    }

    @Test
    public void findPhotoPetTest(){

    }

    @Test
    public void savePhotoReportTest(){

    }



}
