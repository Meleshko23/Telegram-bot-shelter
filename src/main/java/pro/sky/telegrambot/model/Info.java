package pro.sky.telegrambot.model;

import java.util.Objects;

public class Info {

    //при создании таблицы сделать primary key
    private String name;
    private String details;

    public Info(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return name.equals(info.name) && details.equals(info.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, details);
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
