package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Info;
import pro.sky.telegrambot.repositories.InfoRepository;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class InfoShelterService {

    private InfoRepository infoRepository;

    public InfoShelterService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    /**
     * Метод направляет пользователю инфомацию о приюте из файла
     */
    public String sendInfoShelter(String message) {
        Info infoShelter = infoRepository.findInfoByName(message);
        return infoShelter.getDetails();
    }


    //  Бот может выдать расписание работы приюта и адрес, схему проезда.

    /**
     * Метод направляет пользователю расписание работы приюта, адрес, схему проезда из файла
     */
    public String sendWorkTimeAddressMap(String message) {
        Info workTime = infoRepository.findInfoByName(message);
        return workTime.getDetails();
    }

    //  Бот может выдать общие рекомендации о технике безопасности на территории приюта.

    /**
     * Метод направляет пользователю рекомендации о правилах приюта из файла
     */
    public String sendShelterRules(String message) {
        Info shelterRules = infoRepository.findInfoByName(message);
        return shelterRules.getDetails();
    }

    //  Бот может принять и записать контактные данные для связи.

    /**
     * Метод принимает и записывает контактные данные пользователя в базу данных
     */
    public void saveContactInfo() {

        return;
    }

    //  Если бот не может ответить на вопросы клиента, то можно позвать волонтера.

    /**
     * Метод вызывает волонтера в чат
     */
    public void callVolunteer() {

        return;
    }


}
