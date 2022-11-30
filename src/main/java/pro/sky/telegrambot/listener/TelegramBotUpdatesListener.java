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
            if (update.message().text().equals("/start")) {
                Long chatId = update.message().chat().id();
                SendMessage sendMess = new SendMessage(chatId, "Привет!!! Хочешь взять собаку из приюта");

                sendMess.replyMarkup(preparekeyboard());

                SendResponse response = telegramBot.execute(sendMess);

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * метод создает инлайн клавиатуру
     * @return клавиатура подсообщением
     */
    private InlineKeyboardMarkup preparekeyboard() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("2");
        InlineKeyboardButton button3 = new InlineKeyboardButton("3");
        InlineKeyboardButton button4 = new InlineKeyboardButton("4");

        button1.callbackData("One");
        button2.callbackData("TWO");
        button3.callbackData("Three");
        button4.callbackData("Forth");

//                rowInline.add(button1);
//                rowInline.add(button2);
//                rowsInline.add(rowInline);
//
//                rowInline.add(button3);
//                rowInline.add(button4);
//                rowsInline.add(rowInline);

        markupInline.addRow(button1, button2);
        markupInline.addRow(button3, button4);
        return markupInline;
    }


}
