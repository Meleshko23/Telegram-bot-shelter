package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Keyboard;
import pro.sky.telegrambot.constant.MessageAboutShelter;

@Service
public class KeyboardService {

    @Autowired
    private TelegramBot telegramBot;

    public void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет!!! Хочешь взять собаку из приюта");
        sendMess.replyMarkup(preparekeyboardStart());
        telegramBot.execute(sendMess);
    }
    public void responseOnCommandInfoShelter(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoShelter());
        telegramBot.execute(sendMess);
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
