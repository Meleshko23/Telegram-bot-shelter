package pro.sky.telegrambot.constant;

public enum Keyboard {

    START("/start"),
    CAT("CAT"),
    DOG("DOG"),
    info_shelter_cat("info_shelter_cat"),
    work_time_and_address_cat("work_time_and_address_cat"),
    security_contact_details_cat("security_contact_details_cat"),
    shelter_rules_cat("shelter_rules_cat"),
    leave_request_cat("leave_request_cat"),
    call_volunteer_cat("call_volunteer_cat"),

    info_shelter_dog("info_shelter_dog"),
    work_time_and_address_dog("work_time_and_address_dog"),
    security_contact_details_dog("security_contact_details_dog"),
    shelter_rules_dog("shelter_rules_dog"),
    leave_request_dog("leave_request_dog"),
    call_volunteer_dog("call_volunteer_dog"),

    ONE_DOG("ONE_DOG"),
    TWO_DOG("TWO_DOG"),
    THREE_DOG("THREE_DOG"),
    FOUR_DOG("FOUR_DOG"),

    ONE_CAT("ONE_CAT"),
    TWO_CAT("TWO_CAT"),
    THREE_CAT("THREE_CAT"),
    FOUR_CAT("FOUR_CAT"),

    DATING_RULES_DOG("dating_rules_DOG"),
    DOCUMENTS_LIST_DOG ("documents_list_DOG"),
    TRANSPORT_RECOMMENDATIONS_DOG ("transports_recommendations_DOG"),
    HOME_IMPROVEMENT_PUPPY_DOG ("home_improvement_puppy_DOG"),
    HOME_IMPROVEMENT_DOG ("home_improvement_DOG"),
    HOME_IMPROVEMENT_DISABLED_DOG ("home_improvement_disabled_DOG"),
    CYNOLOGIST_TIPS_DOG ("cynologist_tips_DOG"),
    CYNOLOGISTS_LIST_DOG ("cynologist_list_DOG"),
    BOUNCE_LIST_DOG ("bounce_list_DOG"),

    DATING_RULES_CAT("dating_rules_CAT"),
    DOCUMENTS_LIST_CAT ("documents_list_CAT"),
    TRANSPORT_RECOMMENDATIONS_CAT ("transports_recommendations_CAT"),
    HOME_IMPROVEMENT_PUPPY_CAT ("home_improvement_puppy_CAT"),
    HOME_IMPROVEMENT_CAT ("home_improvement_CAT"),
    HOME_IMPROVEMENT_DISABLED_CAT ("home_improvement_disabled_CAT"),
    BOUNCE_LIST_CAT ("bounce_list_CAT"),

    DAILY_REPORT_FORM_DOG("DAILY_REPORT_FORM_DOG"),
    SEND_REPORT_DOG("SEND_REPORT_DOG"),

    DAILY_REPORT_FORM_CAT("DAILY_REPORT_FORM_CAT"),
    SEND_REPORT_CAT("SEND_REPORT_CAT");


    private String command;

    Keyboard(String command){
        this.command = command;
    }

    public String getCommand(){
        return command;
    }
}
