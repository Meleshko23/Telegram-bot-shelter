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
                    keyboardService.responseOnCommandStart(chatId);
                }
            }
            else if (update.callbackQuery() != null) {
                String callbackQuery = update.callbackQuery().data();
                Long chatId = update.callbackQuery().message().chat().id();
                String messageText = null;
                if (callbackQuery.equals(Keyboard.ONE.getCommand())) {
                    keyboardService.responseOnCommandInfoShelter(chatId);
                }
                if (callbackQuery.equals(MessageAboutShelter.info_shelter.getCommand())){
                    messageText = infoShelterService.sendInfoShelter(callbackQuery);
                }
                if (callbackQuery.equals(MessageAboutShelter.work_time_and_address.getCommand())){
                    messageText = infoShelterService.sendWorkTimeAddressMap(callbackQuery);
                }
                if (callbackQuery.equals(MessageAboutShelter.shelter_rules.getCommand())){
                    messageText = infoShelterService.sendShelterRules(callbackQuery);
                }
                if (messageText != null){
                    SendMessage message = new SendMessage(chatId, messageText);
                    telegramBot.execute(message);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
