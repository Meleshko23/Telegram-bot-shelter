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
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.constant.Icon;
import pro.sky.telegrambot.service.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro.sky.telegrambot.constant.KeyboardMenu.keyboardsAfterCommandStart;
import static pro.sky.telegrambot.constant.KeyboardMenu.textButtonsAfterCommandStart;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdatesListenerTest {

    @Mock
    private TelegramBot telegramBot;
    @Mock
    private KeyboardService keyboardService;
    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;
    @Mock
    private List<Update> updates;
    @Mock
    private Update update;
    @Mock
    private Message message;
    @Mock
    private Chat chat;
    @Mock
    private InlineKeyboardMarkup inlineKeyboard;
    @Mock
    private InfoPetsService infoPetsService;
    @Mock
    private KeepingPetService keepingPetService;
    @Mock
    private PetOwnerService petOwnerService;
    @Mock
    private UserService userService;


//    @Test
//    public void handleStartTest() throws URISyntaxException, IOException{
//        String json = Files.readString(Paths.get(TelegramBotUpdatesListenerTest.class.getResource("start_update.json").toURI()));
//        Update update = getUpdate(json, "/start");
//        telegramBotUpdatesListener.process(Collections.singletonList(update));
//
//        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
//        verify(telegramBot).execute(argumentCaptor.capture());
//        SendMessage actual = argumentCaptor.getValue();
//
//        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(1L);
//        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(("Привет друг! " + Icon.WAVE_Icon.get()) +
//                ("\nВыбери приют" + Icon.HAND_Icon.get()));
////        Assertions.assertThat(actual.getParameters().get("inline_keyboard")).isEqualTo(keyboardService.prepareKeyboard(
////                textButtonsAfterCommandStart,
////                keyboardsAfterCommandStart));
//    }
//
//    private Update getUpdate(String json, String replaced){
//        return BotUtils.fromJson(json.replace("%command%", replaced), Update.class);
//    }

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
