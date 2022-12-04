package pro.sky.telegrambot.constant;

public enum MessageAboutShelter {

    info_shelter("info_shelter"), work_time_and_address("work_time_and_address"), shelter_rules("shelter_rules");

    private String command;

    MessageAboutShelter(String command){
        this.command = command;
    }

    public String getCommand(){
        return command;
    }
}
