package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Keyboard;
import pro.sky.telegrambot.constant.MessageAboutShelter;
import pro.sky.telegrambot.service.InfoShelterService;
import pro.sky.telegrambot.service.KeyboardService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final KeyboardService keyboardService;
    private final InfoShelterService infoShelterService;
    @Autowired
    private TelegramBot telegramBot;
    public TelegramBotUpdatesListener(KeyboardService keyboardService, InfoShelterService infoShelterService) {
        this.keyboardService = keyboardService;
        this.infoShelterService = infoShelterService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message() != null) {
                String messageText = update.message().text();
                if (update.message().text().equals(Keyboard.START.getCommand())) {
                    Long chatId = update.message().chat().id();
                    String msgText = "Привет!!! Выбери приют";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardStart();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
            }
            else if (update.callbackQuery() != null) {
                String callbackQuery = update.callbackQuery().data();
                Long chatId = update.callbackQuery().message().chat().id();
                String messageText = null;
                ////////////////////////////////
                // кнопки после команды старт

                if (callbackQuery.equals(Keyboard.CAT.getCommand())) {
                    String msgText = "Привет!!! Выбери интересующий пункт меню";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardCat();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.DOG.getCommand())) {
                    String msgText = "Привет!!! Выбери интересующий пункт меню";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardDog();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                ////////////////////////////////////
                // кнопки после команды DOG
                ///////////////////////////////////////////////
                if (callbackQuery.equals(Keyboard.ONE_DOG.getCommand())) {
                    String msgText = "Что тебе интересует о приюте собак";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardInfoShelterDog();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.TWO_DOG.getCommand())) {
                    String msgText = "Информация о том, как взять питомца из приюта";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardInfoPetsDog();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.THREE_DOG.getCommand())) {
                    String msgText = "Информация по отправке отчета";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardReportDog();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.FOUR_DOG.getCommand())) {
                    keyboardService.responseOnCommandCallVolunteerDog(chatId);
                }

                ////////////////////////////////////
                // кнопки после команды CAT
                if (callbackQuery.equals(Keyboard.ONE_CAT.getCommand())) {
                    String msgText = "Что тебе интересует о приюте кошек";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardInfoShelterCat();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.TWO_CAT.getCommand())) {
                    String msgText = "Информация о том, как взять питомца из приюта";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardInfoPetsCat();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.THREE_CAT.getCommand())) {
                    String msgText = "Информация по отправке отчета";
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.preparekeyboardReportCat();
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.FOUR_CAT.getCommand())) {
                    keyboardService.responseOnCommandCallVolunteerCat(chatId);
                }

                ////////////////////////////////////////////////////
                // кнопки после команды DOG инфо о приюте
//                if (callbackQuery.equals(Keyboard.info_shelter_dog.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.work_time_and_address_dog.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.shelter_rules_dog.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.leave_request_dog.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.call_volunteer_dog.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
                ////////////////////////////////////
                // кнопки после команды CAT инфо о приюте
//                if (callbackQuery.equals(Keyboard.info_shelter_cat.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.work_time_and_address_cat.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.shelter_rules_cat.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.leave_request_cat.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
//                if (callbackQuery.equals(Keyboard.call_volunteer_cat.getCommand())){
//                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
//                }
                ////////////////////////////////

                if (messageText != null){
                    SendMessage message = new SendMessage(chatId, messageText);
                    telegramBot.execute(message);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
