package pro.sky.telegrambot.constant;

public enum Keyboard {

    START("/start"), ONE("ONE"), TWO("TWO"), THREE("THREE"), FORTH("FORTH");

    private String command;

    Keyboard(String command){
        this.command = command;
    }

    public String getCommand(){
        return command;
    }
}
