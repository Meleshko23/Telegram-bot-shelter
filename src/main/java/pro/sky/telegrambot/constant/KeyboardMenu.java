package pro.sky.telegrambot.constant;

public class KeyboardMenu {

    public static final String[] textButtonsAfterCommandStart = {
            ("Приют для кошек " + Icon.CAT_Icon.get()),
            ("Приют для собак " + Icon.DOG_Icon.get())
    };
    public static final Keyboard[] keyboardsAfterCommandStart = {
            Keyboard.CAT,
            Keyboard.DOG
    };
    public static final String[] textButtonsAfterCommandCat = {
            "О приюте кошек",
            "Как взять питомца из приюта",
            "Прислать отчет о питомце",
            "Позвать волонтёра"
    };
    public static final Keyboard[] keyboardsAfterCommandCat = {
            Keyboard.ONE_CAT,
            Keyboard.TWO_CAT,
            Keyboard.THREE_CAT,
            Keyboard.call_volunteer_
    };
    public static final String[] textButtonsAfterCommandDog = {
            "О приюте собак",
            "Как взять питомца из приюта",
            "Прислать отчет о питомце",
            "Позвать волонтёра"
    };
    public static final Keyboard[] keyboardsAfterCommandDog = {
            Keyboard.ONE_DOG,
            Keyboard.TWO_DOG,
            Keyboard.THREE_DOG,
            Keyboard.call_volunteer_
    };
    public static final String[] textButtonsAfterCommandInfoShelter = {
            "Общая информация",
            "График работы",
            "Правила приюта",
            "Контакты охраны",
            "Техника безопасности",
            "Оставить контактные данные",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandInfoShelter = {
            Keyboard.info_shelter_,
            Keyboard.work_time_and_address_,
            Keyboard.shelter_rules_,
            Keyboard.security_contacts_,
            Keyboard.safety_precautions_,
            Keyboard.leave_request_,
            Keyboard.call_volunteer_
    };
//    public static final String[] textButtonsAfterCommandInfoShelterDog = {
//            "Общая информация",
//            "График работы",
//            "Правила приюта",
//            "Контакты охраны",
//            "Техника безопасности",
//            "Оставить контактные данные",
//            "Позвать волонтера"
//    };
//    public static final Keyboard[] keyboardsAfterCommandInfoShelterDog = {
//            Keyboard.info_shelter_,
//            Keyboard.work_time_and_address_,
//            Keyboard.shelter_rules_,
//            Keyboard.security_contacts_,
//            Keyboard.safety_precautions_,
//            Keyboard.leave_request_,
//            Keyboard.call_volunteer_
//    };
    public static final String[] textButtonsAfterCommandInfoPetsDog = {
            "Знакомство с питомцем",
            "Необходимые документы",
            "Перевозка питомца",
            "Готовим дом для щенка",
            "Готовим дом взрослому питомцу",
            "Готовим дом питомцу с особенностями",
            "Советы кинолога по общению с собакой",
            "Проверенные кинологи для обращения",
            "Причины отказа в выборе питомца",
            "Оставить контактные данные",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandInfoPetsDog = {
            Keyboard.DATING_RULES_,
            Keyboard.DOCUMENTS_LIST_,
            Keyboard.TRANSPORT_RECOMMENDATIONS_,
            Keyboard.HOME_IMPROVEMENT_PUPPY_,
            Keyboard.HOME_IMPROVEMENT_,
            Keyboard.HOME_IMPROVEMENT_DISABLED_,
            Keyboard.CYNOLOGIST_TIPS_,
            Keyboard.CYNOLOGISTS_LIST_,
            Keyboard.BOUNCE_LIST_,
            Keyboard.leave_request_,
            Keyboard.call_volunteer_
    };
    public static final String[] textButtonsAfterCommandInfoPetsCat = {
            "Знакомство с питомцем",
            "Необходимые документы",
            "Перевозка питомца",
            "Готовим дом для котенка",
            "Готовим дом взрослому питомцу",
            "Готовим дом питомцу с особенностями",
            "Причины отказа в выборе питомца",
            "Оставить контактные данные",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandInfoPetsCat = {
            Keyboard.DATING_RULES_,
            Keyboard.DOCUMENTS_LIST_,
            Keyboard.TRANSPORT_RECOMMENDATIONS_,
            Keyboard.HOME_IMPROVEMENT_PUPPY_,
            Keyboard.HOME_IMPROVEMENT_,
            Keyboard.HOME_IMPROVEMENT_DISABLED_,
            Keyboard.BOUNCE_LIST_,
            Keyboard.leave_request_,
            Keyboard.call_volunteer_
    };
    public static final String[] textButtonsAfterCommandReportCat = {
            "Форма ежедневного отчета",
            "Отправить отчет о питомце",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandReportCat = {
            Keyboard.DAILY_REPORT_FORM_CAT,
            Keyboard.SEND_REPORT_CAT,
            Keyboard.call_volunteer_
    };
    public static final String[] textButtonsAfterCommandReportDog = {
            "Форма ежедневного отчета",
            "Отправить отчет о питомце",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandReportDog = {
            Keyboard.DAILY_REPORT_FORM_DOG,
            Keyboard.SEND_REPORT_DOG,
            Keyboard.call_volunteer_
    };

}
