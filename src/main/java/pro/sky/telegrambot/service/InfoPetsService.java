package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InfoPetsService {


    private final Logger logger = LoggerFactory.getLogger(InfoPetsService.class);

    //  Бот приветствует пользователя.

    /**
     * Метод направляет приветственное сообщение пользователю
     * и дает доступ к Меню.
     *
     * @param updates
     * @return
     */
//    public int process(List<Update> updates) {
//        updates.forEach(update -> {
//            logger.info("Processing update: {}", update);
//            Message mess = update.message();
//            Long chatId = update.message().chat().id();
//
//            if (mess.text().equals("/start")) {
//                SendMessage sendMess = new SendMessage(chatId,
//                        "Привет друг! " +
//                                "\n Я бот-помощник и помогу познакомится с приютом и питомцами." +
//                                " \n Выбери нужное меню");
//                SendResponse response = telegramBot.execute(sendMess);
//            }
//        });
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }

    //  Бот может рассказать о приюте.

    /**
     * Метод направляет пользователю инфомацию о приюте из файла
     */
    public void sendInfoShelter() {

        return;
    }


    //  Бот может выдать расписание работы приюта и адрес, схему проезда.

    /**
     * Метод направляет пользователю расписание работы приюта, адрес, схему проезда из файла
     */
    public void sendWorktimeAddressMap() {

        return;
    }

    //  Бот может выдать общие рекомендации о технике безопасности на территории приюта.

    /**
     * Метод направляет пользователю рекомендации о правилах приюта из файла
     */
    public void sendShelterRules() {

        return;
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
