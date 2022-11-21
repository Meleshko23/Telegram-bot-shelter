package pro.sky.telegrambot.model;

import java.util.Objects;

public class PetOwner {

    private String name;
    private String mail;
    private String phone;
    private Long chatId;
    private Long id;

    public PetOwner(String name, String mail, String phone, Long chatId, Long id) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.chatId = chatId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", chatId=" + chatId +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetOwner petOwner = (PetOwner) o;
        return Objects.equals(name, petOwner.name) && Objects.equals(mail, petOwner.mail) && Objects.equals(phone, petOwner.phone) && Objects.equals(chatId, petOwner.chatId) && Objects.equals(id, petOwner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mail, phone, chatId, id);
    }
}
