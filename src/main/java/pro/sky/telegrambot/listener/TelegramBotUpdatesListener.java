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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            if (update.message() != null) {
                if (update.message().text().equals("/start")) {
                    Long chatId = update.message().chat().id();
                    responseOnCommandStart(chatId);
                }
            }

            else if (update.callbackQuery() != null) {
                if (update.callbackQuery().data().equals("ONE")) {
                    Long chatId = update.callbackQuery().message().chat().id();
                    responseOnCommandInfoShelter(chatId);
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

        button1.callbackData("ONE");
        button2.callbackData("TWO");
        button3.callbackData("THREE");
        button4.callbackData("FORTH");

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

        InlineKeyboardButton button1 = new InlineKeyboardButton("Контакты приюта");
        InlineKeyboardButton button2 = new InlineKeyboardButton("График работы");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Что-то еще");
        InlineKeyboardButton button4 = new InlineKeyboardButton("И еще что-то");

        button1.callbackData("CONTACTS");
        button2.callbackData("GRAPHIC");
        button3.callbackData("qqqq");
        button4.callbackData("wwww");

        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        return markupInline;
    }


}
