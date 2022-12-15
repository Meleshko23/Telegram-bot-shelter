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
            Keyboard.call_volunteer_cat
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
            Keyboard.call_volunteer_dog
    };
    public static final String[] textButtonsAfterCommandInfoShelterCat = {
            "Общая информация",
            "График работы",
            "Правила приюта",
            "Контакты охраны",
            "Техника безопасности",
            "Оставить контактные данные",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandInfoShelterCat = {
            Keyboard.info_shelter_cat,
            Keyboard.work_time_and_address_cat,
            Keyboard.shelter_rules_cat,
            Keyboard.security_contacts_cat,
            Keyboard.safety_precautions_cat,
            Keyboard.leave_request_cat,
            Keyboard.call_volunteer_cat
    };
    public static final String[] textButtonsAfterCommandInfoShelterDog = {
            "Общая информация",
            "График работы",
            "Правила приюта",
            "Контакты охраны",
            "Техника безопасности",
            "Оставить контактные данные",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandInfoShelterDog = {
            Keyboard.info_shelter_dog,
            Keyboard.work_time_and_address_dog,
            Keyboard.shelter_rules_dog,
            Keyboard.security_contacts_dog,
            Keyboard.safety_precautions_dog,
            Keyboard.leave_request_dog,
            Keyboard.call_volunteer_dog
    };
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
    public static final String[] textButtonsAfterCommandReportCat = {
            "Форма ежедневного отчета",
            "Отправить отчет о питомце",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandReportCat = {
            Keyboard.DAILY_REPORT_FORM_CAT,
            Keyboard.SEND_REPORT_CAT,
            Keyboard.call_volunteer_cat
    };
    public static final String[] textButtonsAfterCommandReportDog = {
            "Форма ежедневного отчета",
            "Отправить отчет о питомце",
            "Позвать волонтера"
    };
    public static final Keyboard[] keyboardsAfterCommandReportDog = {
            Keyboard.DAILY_REPORT_FORM_DOG,
            Keyboard.SEND_REPORT_DOG,
            Keyboard.call_volunteer_dog
    };

}
