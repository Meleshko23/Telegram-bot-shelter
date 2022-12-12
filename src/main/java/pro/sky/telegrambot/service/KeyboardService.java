package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Icon;
import pro.sky.telegrambot.constant.Keyboard;

@Service
public class KeyboardService {

    @Autowired
    private TelegramBot telegramBot;

//    public void responseOnCommandStart(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Привет!!! Выбери приют");
//        sendMess.replyMarkup(prepareKeyboardStart());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandCat(long chatId) {
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardCat());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandOneDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardInfoShelterDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandOneCat(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardInfoShelterCat());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandTwoDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardInfoPetsDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandTwoCat(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardInfoPetsCat());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandThreeDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardReportDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandThreeCat(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(prepareKeyboardReportCat());
//        telegramBot.execute(sendMess);
//    }

    /**
     * Метод отправляет пользователю сообщение с клавиатурой под сообщением
     * @param chatId идентификатор чата для отправки сообщения
     * @param messageText текст сообщения
     * @param inlineKeyboardMarkup клавиатура по сообщением
     */
    public void responseOnCommand(long chatId,
                                  String messageText,
                                  InlineKeyboardMarkup inlineKeyboardMarkup ) {

        SendMessage sendMess = new SendMessage(chatId, messageText);
        sendMess.replyMarkup(inlineKeyboardMarkup);
        telegramBot.execute(sendMess);
    }

    /**
     * Метод для вызова волонтера собачьего приюта
     * @param chatId
     */
    public void responseOnCommandCallVolunteerDog(long chatId) {
        //
    }

    /**
     * Метод для вызова волонтера кошачьего приюта
     * @param chatId
     */
    public void responseOnCommandCallVolunteerCat(long chatId) {
//        ForceReply forceReply = new ForceReply();
//        //forceReply.selective(true);
//
//        SendMessage sendMess = new SendMessage(chatId, "Введите ваше имя:");
//        sendMess.rep
//        telegramBot.execute(sendMess);
    }


    /**
     * Метод создает InlineKeyboardMarkup клавиатуру (в каждой строке одна кнопка)
     * @param buttonsText текст на кнопках
     * @param callbackDataes идентификаторы кнопок
     * @return инлайн клавитура для сообщения
     */
    private InlineKeyboardMarkup prepareKeyboard(String[] buttonsText, Keyboard[] callbackDataes) {
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
    public InlineKeyboardMarkup prepareKeyboardStart() {
        String[] textButtonsAfterCommand = {
                ("Приют для кошек " + Icon.CAT_Icon.get()),
                ("Приют для собак " + Icon.DOG_Icon.get())
        };
        Keyboard[] keyboards = {
                Keyboard.CAT,
                Keyboard.DOG
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды CAT
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardCat() {
        String[] textButtonsAfterCommand = {
                "О приюте кошек",
                "Как взять питомца из приюта",
                "Прислать отчет о питомце",
                "Позвать волонтёра"};
        Keyboard[] keyboards = {
                Keyboard.ONE_CAT,
                Keyboard.TWO_CAT,
                Keyboard.THREE_CAT,
                Keyboard.FOUR_CAT
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }
    /**
     * Клавиатура под сообщением после отправки команды DOG
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardDog() {
        String[] textButtonsAfterCommand = {
                "О приюте собак",
                "Как взять питомца из приюта",
                "Прислать отчет о питомце",
                "Позвать волонтёра"
        };
        Keyboard[] keyboards = {
                Keyboard.ONE_DOG,
                Keyboard.TWO_DOG,
                Keyboard.THREE_DOG,
                Keyboard.FOUR_DOG
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды CAT -> информация о приюте
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardInfoShelterCat() {
        String[] textButtonsAfterCommand = {
                "Общая информация",
                "График работы",
                "Правила приюта",
                "Контакты охраны",
                "Техника безопасности",
                "Оставить контактные данные",
                "Позвать волонтера"};
        Keyboard[] keyboards = {
                Keyboard.info_shelter_cat,
                Keyboard.work_time_and_address_cat,
                Keyboard.shelter_rules_cat,
                Keyboard.security_contacts_cat,
                Keyboard.safety_precautions_cat,
                Keyboard.leave_request_cat,
                Keyboard.call_volunteer_cat
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды DOG -> информация о приюте
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardInfoShelterDog() {
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
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }


    /**
     * Клавиатура после отправки команды DOG -> как взять питомца из приюта
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardInfoPetsDog() {
        String[] textButtonsAfterCommand = {
                "Правила знакомства с питомцем",
                "Необходимые документы",
                "Транспортировка питомца",
                "Обустройство дома для щенка",
                "Обустройство дома для взрослого питомца",
                "Обустройство дома для питомца с особенностями",
                "Советы кинолога по первичному общению с собакой",
                "Проверенные кинологи для дальнейшего обращения",
                "Почему могут отказать и не дать забрать питомца",
                "Отправить контактные данные для связи",
                "Позвать волонтера"

        };
        Keyboard[] keyboards = {
                Keyboard.DATING_RULES_DOG,
                Keyboard.DOCUMENTS_LIST_DOG,
                Keyboard.TRANSPORT_RECOMMENDATIONS_DOG,
                Keyboard.HOME_IMPROVEMENT_PUPPY_DOG,
                Keyboard.HOME_IMPROVEMENT_DOG,
                Keyboard.HOME_IMPROVEMENT_DISABLED_DOG,
                Keyboard.CYNOLOGIST_TIPS_DOG,
                Keyboard.CYNOLOGISTS_LIST_DOG,
                Keyboard.BOUNCE_LIST_DOG,
                Keyboard.leave_request_dog,
                Keyboard.call_volunteer_dog
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура после отправки команды Cat -> как взять питомца из приюта
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardInfoPetsCat() {
        String[] textButtonsAfterCommand = {
                "Правила знакомства с питомцем",
                "Необходимые документы",
                "Транспортировка питомца",
                "Обустройство дома для котенка",
                "Обустройство дома для взрослого питомца",
                "Обустройство дома для питомца с особенностями",
                "Почему могут отказать и не дать забрать питомца",
                "Отправить контактные данные для связи",
                "Позвать волонтера"

        };
        Keyboard[] keyboards = {
                Keyboard.DATING_RULES_CAT,
                Keyboard.DOCUMENTS_LIST_CAT,
                Keyboard.TRANSPORT_RECOMMENDATIONS_CAT,
                Keyboard.HOME_IMPROVEMENT_PUPPY_CAT,
                Keyboard.HOME_IMPROVEMENT_CAT,
                Keyboard.HOME_IMPROVEMENT_DISABLED_CAT,
                Keyboard.BOUNCE_LIST_CAT,
                Keyboard.leave_request_cat,
                Keyboard.call_volunteer_cat
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура после отправки команды Cat -> сделать отчет о питомце
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardReportCat() {
        String[] textButtonsAfterCommand = {
                "Форма ежедневного отчета",
                "Отправить отчет о питомце",
                "Позвать волонтера"

        };
        Keyboard[] keyboards = {
                Keyboard.DAILY_REPORT_FORM_CAT,
                Keyboard.SEND_REPORT_CAT,
                Keyboard.call_volunteer_cat
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура после отправки команды DOG -> сделать отчет о питомце
     * @return
     */
    public InlineKeyboardMarkup prepareKeyboardReportDog() {
        String[] textButtonsAfterCommand = {
                "Форма ежедневного отчета",
                "Отправить отчет о питомце",
                "Позвать волонтера"

        };
        Keyboard[] keyboards = {
                Keyboard.DAILY_REPORT_FORM_DOG,
                Keyboard.SEND_REPORT_DOG,
                Keyboard.call_volunteer_dog
        };
        return prepareKeyboard(textButtonsAfterCommand, keyboards);
    }
}
