package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Keyboard;

@Service
public class KeyboardService {

    @Autowired
    private TelegramBot telegramBot;

    public void responseOnCommandStart(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Привет!!! Выбери приют");
        sendMess.replyMarkup(preparekeyboardStart());
        telegramBot.execute(sendMess);
    }

    public void responseOnCommandCat(long chatId) {
        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardCat());
        telegramBot.execute(sendMess);
    }

    public void responseOnCommandDog(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardDog());
        telegramBot.execute(sendMess);
    }
    public void responseOnCommandOneDog(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoShelterDog());
        telegramBot.execute(sendMess);
    }
    public void responseOnCommandOneCat(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoShelterCat());
        telegramBot.execute(sendMess);
    }



    public void responseOnCommandInfoShelterDog(long chatId) {

        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
        sendMess.replyMarkup(preparekeyboardInfoShelterDog());
        telegramBot.execute(sendMess);
    }



//    public void responseOnCommandCallVolunteer(long chatId, int messageId) {
//        ForceReply forceReply = new ForceReply();
//        //forceReply.selective(true);
//
//        SendMessage sendMess = new SendMessage(chatId, "Введите ваше имя:");
//        sendMess.rep
//        telegramBot.execute(sendMess);
//
//    }


    /**
     * Метод создает InlineKeyboardMarkup клавиатуру (в каждой строке одна кнопка)
     * @param buttonsText текст на кнопках
     * @param callbackDataes идентификаторы кнопок
     * @return инлайн клавитура для сообщения
     */
    private InlineKeyboardMarkup preparekeyboard(String[] buttonsText, Keyboard[] callbackDataes) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[buttonsText.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new InlineKeyboardButton(buttonsText[i]);
            buttons[i].callbackData(callbackDataes[i].getCommand());
            markupInline.addRow(buttons[i]);
        }

        return markupInline;
    }

    /**
     * Клавиатура под сообщением после отправки команды старт
     * @return
     */
    private InlineKeyboardMarkup preparekeyboardStart() {
        String[] textButtonsAfterCommand = {"Приют для кошек", "Приют для собак"};
        Keyboard[] keyboards = {Keyboard.CAT, Keyboard.DOG};

        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды CAT информация о приюте
     * @return
     */
    private InlineKeyboardMarkup preparekeyboardInfoShelterCat() {
        String[] textButtonsAfterCommand = {
                "Общая информация",
                "График работы",
                "Правила приюта",
                "Контакты охраны",
                "Техника безопасности",
                "Оставить контактные данные",
                "Позвать волонтера"};
        Keyboard[] messageAboutShelterCat = {
                Keyboard.info_shelter_cat,
                Keyboard.work_time_and_address_cat,
                Keyboard.shelter_rules_cat,
                Keyboard.security_contacts_cat,
                Keyboard.safety_precautions_cat,
                Keyboard.leave_request_cat,
                Keyboard.call_volunteer_cat
        };
        return preparekeyboard(textButtonsAfterCommand, messageAboutShelterCat);
    }


    /**
     * Клавиатура под сообщением после отправки команды DOG информация о приюте
     * @return
     */
    private InlineKeyboardMarkup preparekeyboardInfoShelterDog() {
        String[] textButtonsAfterCommand = {
                "Общая информация",
                "График работы",
                "Правила приюта",
                "Контакты охраны",
                "Техника безопасности",
                "Оставить контактные данные",
                "Позвать волонтера"};
        Keyboard[] keyboards = {
                Keyboard.info_shelter_dog,
                Keyboard.work_time_and_address_dog,
                Keyboard.shelter_rules_dog,
                Keyboard.security_contacts_dog,
                Keyboard.safety_precautions_dog,
                Keyboard.leave_request_dog,
                Keyboard.call_volunteer_dog
        };
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды CAT
     * @return
     */
    private InlineKeyboardMarkup preparekeyboardCat() {
        String[] textButtonsAfterCommand = {
                "О приюте",
                "О питомцах",
                "Взаимодействие с питомцем",
                "Связаться с волонтёром"};
        Keyboard[] keyboards = {
                Keyboard.ONE_CAT,
                Keyboard.TWO_CAT,
                Keyboard.THREE_CAT,
                Keyboard.FOUR_CAT
        };
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }
    /**
     * Клавиатура под сообщением после отправки команды DOG
     * @return
     */
    private InlineKeyboardMarkup preparekeyboardDog() {
        String[] textButtonsAfterCommand = {
                "О приюте",
                "О питомцах",
                "Взаимодействие с питомцем",
                "Связаться с волонтёром"
        };
        Keyboard[] keyboards = {
                Keyboard.ONE_DOG,
                Keyboard.TWO_DOG,
                Keyboard.THREE_DOG,
                Keyboard.FOUR_DOG
        };
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }
}
