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

//    public void responseOnCommandStart(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Привет!!! Выбери приют");
//        sendMess.replyMarkup(preparekeyboardStart());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandCat(long chatId) {
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardCat());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandOneDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardInfoShelterDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandOneCat(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardInfoShelterCat());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandTwoDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardInfoPetsDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandTwoCat(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardInfoPetsCat());
//        telegramBot.execute(sendMess);
//    }
//
//    public void responseOnCommandThreeDog(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardReportDog());
//        telegramBot.execute(sendMess);
//    }
//    public void responseOnCommandThreeCat(long chatId) {
//
//        SendMessage sendMess = new SendMessage(chatId, "Нажми на кнопку ниже. Выбери то,что тебя интересует");
//        sendMess.replyMarkup(preparekeyboardReportCat());
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
    public InlineKeyboardMarkup preparekeyboardStart() {
        String[] textButtonsAfterCommand = {
                "Приют для кошек",
                "Приют для собак"
        };
        Keyboard[] keyboards = {
                Keyboard.CAT,
                Keyboard.DOG
        };
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды CAT
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardCat() {
        String[] textButtonsAfterCommand = {
                "О приюте",
                "Как взять питомца из приюта",
                "Прислать отчет о питомце",
                "Позвать волонтёра"};
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
    public InlineKeyboardMarkup preparekeyboardDog() {
        String[] textButtonsAfterCommand = {
                "О приюте",
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
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды CAT -> информация о приюте
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardInfoShelterCat() {
        String[] textButtonsAfterCommand = {
                "Общая информация",
                "График работы",
                "Оформить пропуск для автомобиля",
                "Правила приюта",
                "Оставить заявку",
                "Связаться с волонтёром"};
        Keyboard[] keyboards = {
                Keyboard.info_shelter_cat,
                Keyboard.work_time_and_address_cat,
                Keyboard.security_contact_details_cat,
                Keyboard.shelter_rules_cat,
                Keyboard.leave_request_cat,
                Keyboard.call_volunteer_cat
        };
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура под сообщением после отправки команды DOG -> информация о приюте
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardInfoShelterDog() {
        String[] textButtonsAfterCommand = {
                "Общая информация",
                "График работы",
                "Оформить пропуск для автомобиля",
                "Правила приюта",
                "Оставить заявку",
                "Связаться с волонтёром"};

        Keyboard[] keyboards = {
                Keyboard.info_shelter_dog,
                Keyboard.work_time_and_address_dog,
                Keyboard.security_contact_details_dog,
                Keyboard.shelter_rules_dog,
                Keyboard.leave_request_dog,
                Keyboard.call_volunteer_dog
        };
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }


    /**
     * Клавиатура после отправки команды DOG -> как взять питомца из приюта
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardInfoPetsDog() {
        String[] textButtonsAfterCommand = {
                "Правила знакомства с собакой",
                "Список необходимых документов",
                "Список рекомендаций по транспортировке животного",
                "Список рекомендаций по обустройству дома для щенка",
                "Список рекомендаций по обустройству дома для взрослого животного",
                "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение)",
                "Советы кинолога по первичному общению с собакой",
                "Рекомендации по проверенным кинологам для дальнейшего обращения",
                "Список причин, почему могут отказать и не дать забрать собаку из приюта",
                "Отправить контакнтые данные для связи",
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
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура после отправки команды Cat -> как взять питомца из приюта
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardInfoPetsCat() {
        String[] textButtonsAfterCommand = {
                "Правила знакомства с животным (котом, котенком)",
                "Список необходимых документов",
                "Список рекомендаций по транспортировке животного",
                "Список рекомендаций по обустройству дома для котенка",
                "Список рекомендаций по обустройству дома для взрослого животного",
                "Список рекомендаций по обустройству дома для животного с ограниченными возможностями (зрение, передвижение)",
                "Список причин, почему могут отказать и не дать забрать кошку из приюта",
                "Отправить контакнтые данные для связи",
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
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура после отправки команды Cat -> сделать отчет о питомце
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardReportCat() {
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
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

    /**
     * Клавиатура после отправки команды DOG -> сделать отчет о питомце
     * @return
     */
    public InlineKeyboardMarkup preparekeyboardReportDog() {
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
        return preparekeyboard(textButtonsAfterCommand, keyboards);
    }

}
