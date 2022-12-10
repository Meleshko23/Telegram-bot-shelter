package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Keyboard;
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
                    keyboardService.responseOnCommandStart(chatId);
                }
            }
            else if (update.callbackQuery() != null) {
                String callbackQuery = update.callbackQuery().data();
                Long chatId = update.callbackQuery().message().chat().id();
                String messageText = null;
                ////////////////////////////////
                // кнопки после команды старт

                if (callbackQuery.equals(Keyboard.CAT.getCommand())) {
                    keyboardService.responseOnCommandCat(chatId);
                }
                if (callbackQuery.equals(Keyboard.DOG.getCommand())) {
                    keyboardService.responseOnCommandDog(chatId);
                }
                ////////////////////////////////////
                // кнопки после команды DOG
                ///////////////////////////////////////////////
                if (callbackQuery.equals(Keyboard.ONE_DOG.getCommand())) {
                    keyboardService.responseOnCommandOneDog(chatId);
                }
                if (callbackQuery.equals(Keyboard.TWO_DOG.getCommand())) {
                    keyboardService.responseOnCommandOneDog(chatId);
                }
//                if (callbackQuery.equals(Keyboard.THREE_DOG.getCommand())) {
//                    keyboardService.responseOnCommandOneDog(chatId);
//                }
//                if (callbackQuery.equals(Keyboard.FOUR_DOG.getCommand())) {
//                    keyboardService.responseOnCommandOneDog(chatId);
//                }


                ////////////////////////////////////
                // кнопки после команды CAT
                if (callbackQuery.equals(Keyboard.ONE_CAT.getCommand())) {
                    keyboardService.responseOnCommandOneCat(chatId);
                }
                if (callbackQuery.equals(Keyboard.TWO_CAT.getCommand())) {
                    keyboardService.responseOnCommandOneCat(chatId);
                }
//                if (callbackQuery.equals(Keyboard.THREE_CAT.getCommand())) {
//                    keyboardService.responseOnCommandOneCat(chatId);
//                }
//                if (callbackQuery.equals(Keyboard.FOUR_CAT.getCommand())) {
//                    keyboardService.responseOnCommandOneCat(chatId);
//                }

                ////////////////////////////////////////////////////
                // кнопки после команды DOG инфо о приюте
                if (callbackQuery.equals(Keyboard.info_shelter_dog.getCommand())) {
                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.work_time_and_address_dog.getCommand())) {
                    messageText = infoShelterService.sendWorkTimeAddressMap(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.shelter_rules_dog.getCommand())) {
                    messageText = infoShelterService.sendShelterRules(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.security_contacts_dog.getCommand())) {
                    messageText = infoShelterService.sendSecurityContacts(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.safety_precautions_dog.getCommand())) {
                    messageText = infoShelterService.sendSafetyPrecautions(callbackQuery);
                }
                ////////////////////////////////////
                // кнопки после команды CAT инфо о приюте
                if (callbackQuery.equals(Keyboard.info_shelter_cat.getCommand())){
                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.work_time_and_address_cat.getCommand())){
                    messageText = infoShelterService.sendWorkTimeAddressMap(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.shelter_rules_cat.getCommand())){
                    messageText = infoShelterService.sendShelterRules(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.security_contacts_cat.getCommand())){
                    messageText = infoShelterService.sendSecurityContacts(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.safety_precautions_cat.getCommand())){
                    messageText = infoShelterService.sendSafetyPrecautions(callbackQuery);
                }
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
