package org.example.commands;

import org.example.builders.MusicBandBuilder;
import org.example.managers.CollectionManager;
import org.example.exceptions.InvalidDataException;
import org.example.models.*;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для добавления нового элемента в коллекцию с условием
 * Реализует абстрактный метод execute() из класса Command.
 */
public class AddIfMin extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1338L;
    private final CollectionManager collectionManager; // Менеджер коллекции, в которую добавляется элемент

    /**
     * Конструктор команды Adв_if_min
     *
     * @param collectionManager менеджер коллекции, в которую будет добавлен новый элемент
     */
    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min", "add_if_min {element} : добавить новый элемент в коллекцию, если число участников группы меньше, чем наименьшее кол-во участников");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {

            MusicBand newBand = request.getMusicBand();


            Integer minParticipants = collectionManager.getMinParticipants();

            if (minParticipants == null || newBand.getNumberOfParticipants() < minParticipants) {
                collectionManager.add(newBand);
                return new Response("Объект успешно добавлен!");
            } else {
                return new Response("Объект не добавлен: количество участников не меньше минимального в коллекции.");
            }
        } catch (InvalidDataException e) {
            return new Response("Ошибка: некорректные данные объекта MusicBand."); // Ошибка валидации
        }
    }
}

