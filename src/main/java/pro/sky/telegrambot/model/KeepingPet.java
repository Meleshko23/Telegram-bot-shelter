package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class KeepingPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String infoPet;
    private LocalDateTime dateTime;
    private Boolean quality;

    @OneToOne
    private PhotoPet photoPet;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dog_owner_id")
    private DogOwner dogOwner;

    @ManyToOne
    @JoinColumn(name = "cat_owner_id")
    private CatOwner catOwner;

    public KeepingPet(Long id, Long chatId, String infoPet, PhotoPet photoPet, LocalDateTime dateTime) {
        this.id = id;
        this.chatId = chatId;
        this.infoPet = infoPet;
        this.photoPet = photoPet;
        this.dateTime = dateTime;

    }

    public KeepingPet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getInfoPet() {
        return infoPet;
    }

    public void setInfoPet(String infoPet) {
        this.infoPet = infoPet;
    }

    public boolean isQuality() {
        return quality;
    }

    public void setQuality(boolean quality) {
        this.quality = quality;
    }


    public DogOwner getDogOwner() {
        return dogOwner;
    }

    public CatOwner getCatOwner() {
        return catOwner;
    }

    public PhotoPet getPhotoPet() {
        return photoPet;
    }

    public void setPhotoPet(PhotoPet photoPet) {
        this.photoPet = photoPet;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDogOwner(DogOwner dogOwner) {
        this.dogOwner = dogOwner;
    }

    public void setCatOwner(CatOwner catOwner) {
        this.catOwner = catOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeepingPet that = (KeepingPet) o;
        return id.equals(that.id) && chatId.equals(that.chatId) && infoPet.equals(that.infoPet) && photoPet.equals(that.photoPet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, infoPet, photoPet);
    }

    @Override
    public String toString() {
        return "KeepingPet{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", infoPet='" + infoPet + '\'' +
                ", photoPet=" + photoPet +
                '}';
    }
}
