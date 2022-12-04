package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Keyboard;
import pro.sky.telegrambot.constant.MessageAboutShelter;
import pro.sky.telegrambot.service.InfoShelterService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final InfoShelterService infoShelterService;

    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(InfoShelterService infoShelterService) {
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
                    responseOnCommandStart(chatId);
                }
            }
            else if (update.callbackQuery() != null) {
                String callbackQuery = update.callbackQuery().data();
                Long chatId = update.callbackQuery().message().chat().id();
                String messageText = null;
                if (callbackQuery.equals(Keyboard.ONE.getCommand())) {
                    responseOnCommandInfoShelter(chatId);
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

    private void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет!!! Хочешь взять собаку из приюта");
        sendMess.replyMarkup(preparekeyboardStart());
        SendResponse response = telegramBot.execute(sendMess);
    }
    private void responseOnCommandInfoShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoShelter());
        SendResponse response = telegramBot.execute(sendMess);
    }
    /**
     * метод создает инлайн клавиатуру после отправки команды Start
     * @return клавиатура подсообщением
     */
    private InlineKeyboardMarkup preparekeyboardStart() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Информация о питомцах");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Взаимодействие с питомцем ");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");

        button1.callbackData(Keyboard.ONE.getCommand());
        button2.callbackData(Keyboard.TWO.getCommand());
        button3.callbackData(Keyboard.THREE.getCommand());
        button4.callbackData(Keyboard.FORTH.getCommand());

        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        return markupInline;
    }
    /**
     * метод создает инлайн клавиатуру после отправки команды для получения информации о приюте
     * @return клавиатура под сообщением
     */
    private InlineKeyboardMarkup preparekeyboardInfoShelter() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton button1 = new InlineKeyboardButton("Общая информация");
        InlineKeyboardButton button2 = new InlineKeyboardButton("График работы");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Правила приюта");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Оставить заявку");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Связаться с волонтёром");

        button1.callbackData(MessageAboutShelter.info_shelter.getCommand());
        button2.callbackData(MessageAboutShelter.work_time_and_address.getCommand());
        button3.callbackData(MessageAboutShelter.shelter_rules.getCommand());
        button4.callbackData("qqqq");
        button5.callbackData("wwww");

        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        markupInline.addRow(button5);
        return markupInline;
    }


}
