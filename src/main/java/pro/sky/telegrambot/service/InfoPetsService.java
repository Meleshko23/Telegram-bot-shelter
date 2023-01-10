package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repositories.InfoRepository;

/**
 * Класс отвечает за консультации с новым пользователем через методы приветствия,
 * предоставления полной информации о том, как предстоит подготовиться человеку ко встрече с новым членом семьи,
 * записать контактные данные пользователя, позвать волонтера.
 */
@Service
public class InfoPetsService {

    private final InfoRepository infoRepository;

    public InfoPetsService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    /**
     * Метод выдает информацию из БД по ключу, переданному через параметр
     * @param message ключ для доступа к данным в таблице
     * @return Информация по указанному ключу
     */
    public String getInfoByRequest(String message) {
        return infoRepository.findInfoByName(message).getDetails();
    }


}
