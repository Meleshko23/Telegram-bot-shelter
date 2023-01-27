package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.constant.Icon;
import pro.sky.telegrambot.constant.Keyboard;
import pro.sky.telegrambot.service.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro.sky.telegrambot.constant.KeyboardMenu.*;
import static pro.sky.telegrambot.constant.KeyboardMenu.keyboardsAfterCommandCat;
import static pro.sky.telegrambot.constant.MessageForSaveContacts.NAME;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {

    @Mock
    private TelegramBot telegramBot;
    @Mock
    private KeyboardService keyboardService;
    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;
//    @Mock
//    private List<Update> updates;
//    @Mock
//    private Update update;
//    @Mock
//    private Message message;
//    @Mock
//    private Chat chat;
//    @Mock
//    private InlineKeyboardMarkup inlineKeyboard;
    @Mock
    private InfoPetsService infoPetsService;
    @Mock
    private KeepingPetService keepingPetService;
    @Mock
    private PetOwnerService petOwnerService;
    @Mock
    private UserService userService;


    @Test
    public void handleStartTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class.getResource("start_update.json").toURI()));
        Update update = getUpdate(json, "/start");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(keyboardService).prepareKeyboard(textButtonsAfterCommandStart, keyboardsAfterCommandStart);
    }

    @Test
    public void CallbackQueryCatTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "cat");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(keyboardService).prepareKeyboard(textButtonsAfterCommandCat,
                keyboardsAfterCommandCat);

    }
    @Test
    public void CallbackQueryDogTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "dog");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(keyboardService).prepareKeyboard(textButtonsAfterCommandDog,
                keyboardsAfterCommandDog);
    }
    @Test
    public void CallbackQueryOneDogTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "ONE_DOG");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(keyboardService).prepareKeyboard(textButtonsAfterCommandInfoShelter,
                keyboardsAfterCommandInfoShelter);
    }
    @Test
    public void CallbackQueryOneCatTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "ONE_CAT");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(keyboardService).prepareKeyboard(textButtonsAfterCommandInfoShelter,
                keyboardsAfterCommandInfoShelter);
    }

    @Test
    public void callbackInfoShelterTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "info_shelter_");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

//        String callbackQuery = update.callbackQuery().data();
//        Long chatId = update.callbackQuery().message().chat().id();

        verify(infoPetsService).getInfoByRequest(anyString());
    }

    @Test
    public void callbackWorktimeTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "work_time_and_address_");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(infoPetsService).getInfoByRequest(anyString());
    }
    @Test
    public void callbackShelterRulesTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "shelter_rules_");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(infoPetsService).getInfoByRequest(anyString());
    }

    @Test
    public void callbackSucurityContactsTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "security_contacts_");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(infoPetsService).getInfoByRequest(anyString());
    }

    @Test
    public void callbackSafityPrecautionsTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "safety_precautions_");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        verify(infoPetsService).getInfoByRequest(anyString());
    }

    @Test
    public void callbackLeaveRequestTest() throws URISyntaxException, IOException{
        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class
                .getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, "leave_request_");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        Long chatId = update.callbackQuery().message().chat().id();
        verify(userService).saveContactInfo(chatId, NAME, null);
    }

    private Update getUpdate(String json, String replaced){
        return BotUtils.fromJson(json.replace("%command%", replaced), Update.class);
    }
    private Update getUpdate(String json){
        return BotUtils.fromJson(json, Update.class);
    }

//    @BeforeEach
//    public void setUp() {
//        telegramBotUpdatesListener = new TelegramBotUpdatesListener(keyboardService, infoPetsService, keepingPetService, petOwnerService, userService, telegramBot);
//    }
//
//    @Test
//    public void testProcess() {
//        when(update.message()).thenReturn(message);
//        when(message.text()).thenReturn("/start");
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(123L);
//        when(keyboardService.prepareKeyboard(textButtonsAfterCommandStart,
//                keyboardsAfterCommandStart)).thenReturn(inlineKeyboard);
//
//        telegramBotUpdatesListener.process(updates);
//
//        verify(keyboardService).responseOnCommand(123L, ("Привет друг! " + Icon.WAVE_Icon.get()) +
//                ("\nВыбери приют" + Icon.HAND_Icon.get()), inlineKeyboard);
//    }
}
