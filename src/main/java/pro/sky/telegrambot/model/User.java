package pro.sky.telegrambot.model;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Locale;
import java.util.Objects;

@Entity(name = "users")
public class User{
    @Id
    private Long chatId;
    private String shelter;
    private String name;
    private String phone;
    private String mail;


    public User() {
    }

    public User(Long chatId, String shelter, String name, String phone, String mail) {
        this.chatId = chatId;
        this.shelter = shelter;
        this.name = normalizationName(name);
        this.phone = phone;
        this.mail = mail;
    }

    public User(Long chatId, String shelter) {
        this.chatId = chatId;
        this.shelter = shelter;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getShelter() {
        return shelter;
    }

    public void setShelter(String shelter) {
        this.shelter = shelter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = normalizationName(name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    private String normalizationName(String name){
        return StringUtils.capitalize(name.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(chatId, user.chatId);
    }

    @Override
    public int hashCode() {
        return chatId != null ? chatId.hashCode() : 0;
    }

}
