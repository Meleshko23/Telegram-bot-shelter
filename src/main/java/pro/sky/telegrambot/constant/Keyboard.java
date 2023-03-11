package pro.sky.telegrambot.constant;

public enum Keyboard {

    START("/start"),
    CAT("cat"),
    DOG("dog"),
    ONE_DOG("ONE_DOG"),
    TWO_DOG("TWO_DOG"),
    THREE_DOG("THREE_DOG"),
    FOUR_DOG("FOUR_DOG"),

    ONE_CAT("ONE_CAT"),
    TWO_CAT("TWO_CAT"),
    THREE_CAT("THREE_CAT"),
    FOUR_CAT("FOUR_CAT"),

//    info_shelter_dog("info_shelter_dog"),
//    work_time_and_address_dog("work_time_and_address_dog"),
//    shelter_rules_dog("shelter_rules_dog"),
//    security_contacts_dog("security_contacts_dog"),
//    safety_precautions_dog("safety_precautions_dog"),
//    leave_request_dog("leave_request_dog"),
//    call_volunteer_dog("call_volunteer_dog"),


    info_shelter_("info_shelter_"),
    work_time_and_address_("work_time_and_address_"),
    shelter_rules_("shelter_rules_"),
    security_contacts_("security_contacts_"),
    safety_precautions_("safety_precautions_"),
    leave_request_("leave_request_"),
    call_volunteer_("call_volunteer_"),

//    DATING_RULES_("dating_rules_"),
//    DOCUMENTS_LIST_ ("documents_list_"),
//    TRANSPORT_RECOMMENDATIONS_ ("transports_recommendations_"),
//    HOME_IMPROVEMENT_PUPPY_ ("home_improvement_puppy_"),
//    HOME_IMPROVEMENT_ ("home_improvement_"),
//    HOME_IMPROVEMENT_DISABLED_ ("home_improvement_disabled_"),
    CYNOLOGIST_TIPS_ ("cynologist_tips_"),
    CYNOLOGISTS_LIST_ ("cynologist_list_"),
//    BOUNCE_LIST_ ("bounce_list_"),

    DATING_RULES_("dating_rules_"),
    DOCUMENTS_LIST_ ("documents_list_"),
    TRANSPORT_RECOMMENDATIONS_ ("transports_recommendations_"),
    HOME_IMPROVEMENT_PUPPY_ ("home_improvement_puppy_"),
    HOME_IMPROVEMENT_ ("home_improvement_"),
    HOME_IMPROVEMENT_DISABLED_ ("home_improvement_disabled_"),
    BOUNCE_LIST_ ("bounce_list_"),

    DAILY_REPORT_FORM_DOG("DAILY_REPORT_FORM_DOG"),
    SEND_REPORT_DOG("SEND_REPORT_DOG"),

    DAILY_REPORT_FORM_CAT("DAILY_REPORT_FORM_CAT"),
    SEND_REPORT_CAT("SEND_REPORT_CAT");

    private final String command;

    Keyboard(String command){
        this.command = command;
    }

    public String getCommand(){
        return command;
    }
}
