package pro.sky.telegrambot.service;

import pro.sky.telegrambot.repositories.InfoRepository;

public class InfoShelterService {

    private InfoRepository infoRepository;

    public InfoShelterService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    /**
     * Метод направляет пользователю инфомацию о приюте из файла
     */
    public String sendInfoShelter(String message) {
        return infoRepository.findInfoByName(message);
    }


    //  Бот может выдать расписание работы приюта и адрес, схему проезда.

    /**
     * Метод направляет пользователю расписание работы приюта, адрес, схему проезда из файла
     */
    public String sendWorkTimeAddressMap(String message) {
        return infoRepository.findInfoByName(message);
    }

    //  Бот может выдать общие рекомендации о технике безопасности на территории приюта.

    /**
     * Метод направляет пользователю рекомендации о правилах приюта из файла
     */
    public String sendShelterRules(String message) {
        return infoRepository.findInfoByName(message);
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
