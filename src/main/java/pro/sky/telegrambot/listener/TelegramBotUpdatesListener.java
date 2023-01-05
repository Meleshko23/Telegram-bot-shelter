package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.constant.Icon;
import pro.sky.telegrambot.constant.Keyboard;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.service.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static pro.sky.telegrambot.constant.Keyboard.START;
import static pro.sky.telegrambot.constant.KeyboardMenu.*;
import static pro.sky.telegrambot.constant.MessageForDailyReport.*;
import static pro.sky.telegrambot.constant.MessageForSaveContacts.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final KeyboardService keyboardService;
    private final InfoPetsService infoPetsService;
    private final KeepingPetService keepingPetService;
    private final PetOwnerService petOwnerService;

    private final UserService userService;
    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(KeyboardService keyboardService, InfoPetsService infoPetsService, KeepingPetService keepingPetService, PetOwnerService petOwnerService, UserService userService) {
        this.keyboardService = keyboardService;
        this.infoPetsService = infoPetsService;
        this.keepingPetService = keepingPetService;
        this.petOwnerService = petOwnerService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null) {

                // пользователь отправил сообщение
                if (update.message().text() != null) {
                    String cmd = update.message().text();
                    if (cmd.equals(START.getCommand())) {
                        Long chatId = update.message().chat().id();
                        String msgText = ("Привет друг! " + Icon.WAVE_Icon.get()) +
                                ("\nВыбери приют" + Icon.HAND_Icon.get());
                        InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                                textButtonsAfterCommandStart,
                                keyboardsAfterCommandStart);
                        keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                    }

                    // пользователь ответил на сообщение бота
                    else if (update.message().replyToMessage() != null &&
                            !update.message().replyToMessage().text().isEmpty()) {
                        Long chatId = update.message().chat().id();
                        String userRequest = update.message().text();
                        String replyMessage = update.message().replyToMessage().text();

                        // отправка контактных данных
                        if (replyMessage.equals(NAME)) {
                            userService.saveContactInfo(chatId, PHONE, userRequest);
                        }
                        if (replyMessage.equals(PHONE)) {
                            userService.saveContactInfo(chatId, MAIL, userRequest);
                        }
                        if (replyMessage.equals(MAIL)) {
                            userService.saveContactInfo(chatId, SAVE, userRequest);
                        }

                        // пользователь отправил в отчете о животном текст без фото
                        if (replyMessage.equals(SEND_REPORT) ||
                                replyMessage.equals(RE_SEND_REPORT)) {
                            keepingPetService.sendReport(chatId, RE_SEND_REPORT);
                        }

                    }
                    //пользователь отправил фото для отчета о животном
                } else if (update.message().photo() != null &&
                        update.message().replyToMessage() != null) {
                    String replyMessage = update.message().replyToMessage().text();
                    // отправка отчета о животном
                    if (replyMessage.equals(SEND_REPORT) ||
                            replyMessage.equals(RE_SEND_REPORT)) {

                        Long chatId = update.message().chat().id();
                        PhotoSize[] photoSizes = update.message().photo();
                        String capture = update.message().caption();
                        if (capture == null || photoSizes == null) {
                            keepingPetService.sendReport(chatId, RE_SEND_REPORT);
                        } else if (petOwnerService.findCatOwner(chatId) == null && petOwnerService.findDogOwner(chatId) == null) {
                            keepingPetService.sendReportWithoutReply(chatId, USER_IS_NOT_OWNER);
                        } else {
                            try {
                                keepingPetService.sendReport(chatId, capture, photoSizes);
                                keepingPetService.sendMessage(chatId, "Отчет сохранен, ждем отчета завтра");
                            } catch (IOException e) {
                                throw new RuntimeException("Проблема с сохранением фото");
                            }
                        }
                    }
                }

            } else if (update.callbackQuery() != null) {
                String callbackQuery = update.callbackQuery().data();
                Long chatId = update.callbackQuery().message().chat().id();
                String messageText = null;

                ////////////////////////////////
                // кнопки после команды старт
                if (callbackQuery.equals(Keyboard.CAT.getCommand())) {
                    User user = new User();
                    String msgText = ("Меню приюта кошек " + Icon.CAT_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandCat,
                            keyboardsAfterCommandCat
                    );
                    User prevUser = userService.findUserByChatId(chatId);
                    if (prevUser == null) {
                        user.setChatId(chatId);
                    } else {
                        user = prevUser;
                    }
                    user.setShelter(callbackQuery);
                    userService.saveUser(user);
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.DOG.getCommand())) {
                    User user = new User();
                    String msgText = ("Меню приюта собак " + Icon.DOG_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandDog,
                            keyboardsAfterCommandDog
                    );
                    User prevUser = userService.findUserByChatId(chatId);
                    if (prevUser == null) {
                        user.setChatId(chatId);
                    } else {
                        user = prevUser;
                    }

                    user.setShelter(callbackQuery);
                    userService.saveUser(user);
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                ////////////////////////////////////
                // кнопки после команды DOG
                ///////////////////////////////////////////////
                if (callbackQuery.equals(Keyboard.ONE_DOG.getCommand())) {
                    String msgText = ("Информация о приюте собак " + Icon.DOG_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandInfoShelter,
                            keyboardsAfterCommandInfoShelter
                    );
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.TWO_DOG.getCommand())) {
                    String msgText = ("Как взять собаку из приюта " + Icon.DOG_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandInfoPetsDog,
                            keyboardsAfterCommandInfoPetsDog
                    );
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.THREE_DOG.getCommand())) {
                    String msgText = ("Отправка отчета " + Icon.DOG_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandReportDog,
                            keyboardsAfterCommandReportDog);
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
//                if (callbackQuery.equals(Keyboard.FOUR_DOG.getCommand())) {
//                    keyboardService.responseOnCommandCallVolunteerDog(chatId);
//                }

                ////////////////////////////////////
                // кнопки после команды CAT
                if (callbackQuery.equals(Keyboard.ONE_CAT.getCommand())) {
                    String msgText = ("Информация о приюте кошек " + Icon.CAT_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandInfoShelter,
                            keyboardsAfterCommandInfoShelter
                    );
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.TWO_CAT.getCommand())) {
                    String msgText = ("Как взять кошку из приюта " + Icon.CAT_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandInfoPetsCat,
                            keyboardsAfterCommandInfoPetsCat
                    );
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
                if (callbackQuery.equals(Keyboard.THREE_CAT.getCommand())) {
                    String msgText = ("Отправка отчета " + Icon.CAT_Icon.get());
                    InlineKeyboardMarkup inlineKeyboard = keyboardService.prepareKeyboard(
                            textButtonsAfterCommandReportCat,
                            keyboardsAfterCommandReportCat
                    );
                    keyboardService.responseOnCommand(chatId, msgText, inlineKeyboard);
                }
//                if (callbackQuery.equals(Keyboard.FOUR_CAT.getCommand())) {
//                    keyboardService.responseOnCommandCallVolunteerCat(chatId);
//                }
                ////////////////////////////////////
                // кнопки инфо о приюте
                if (callbackQuery.equals(Keyboard.info_shelter_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.work_time_and_address_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.shelter_rules_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.security_contacts_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.safety_precautions_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.leave_request_.getCommand())) {
                    userService.saveContactInfo(chatId, NAME, null);
                }
                ///////////////
                // кнопки Как взять животное из приюта
                if (callbackQuery.equals(Keyboard.DATING_RULES_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.DOCUMENTS_LIST_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.TRANSPORT_RECOMMENDATIONS_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.HOME_IMPROVEMENT_PUPPY_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.HOME_IMPROVEMENT_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.HOME_IMPROVEMENT_DISABLED_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.CYNOLOGIST_TIPS_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.CYNOLOGISTS_LIST_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.BOUNCE_LIST_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery+userService.findShelterByChatId(chatId));
                }
                if (callbackQuery.equals(Keyboard.call_volunteer_.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery);
                }
                ////////////////////////////////
                // кнопки после команды DOG Прислать отчет о питомце
                if (callbackQuery.equals(Keyboard.DAILY_REPORT_FORM_DOG.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.SEND_REPORT_DOG.getCommand())) {
                    keepingPetService.sendReport(chatId, SEND_REPORT);
                }

                ////////////////////////////////
                // кнопки после команды CAT Прислать отчет о питомце
                if (callbackQuery.equals(Keyboard.DAILY_REPORT_FORM_CAT.getCommand())) {
                    messageText = infoPetsService.getInfoByRequest(callbackQuery);
                }
                if (callbackQuery.equals(Keyboard.SEND_REPORT_CAT.getCommand())) {
                    keepingPetService.sendReport(chatId, SEND_REPORT);
                }

                if (messageText != null) {
                    SendMessage message = new SendMessage(chatId, messageText);
                    telegramBot.execute(message);
                }
                // Такой команды не существует
            } else{
                Long chatId = update.message().chat().id();
                String msgText = "Такой команды не существует. \n\n Чтобы вернуться в главное меню, введи /start";
                keyboardService.responseOnCommand(chatId, msgText);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
