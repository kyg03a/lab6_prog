package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

public class CountLess extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 50L;
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды CountLessThanAlbumsCount.
     *
     * @param collectionManager менеджер коллекции, который будет использоваться для подсчета элементов
     */
    public CountLess(CollectionManager collectionManager) {
        super("count_less_than_albums_count", "count_less_than_albums_count albumsCount : вывести количество элементов, значение поля albumsCount которых меньше заданного");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(Request request) {
        try {
            System.out.println("DEBUG: Выполнение команды count_less_than_albums_count");
            int albumsCountThreshold = request.getIntArgument();
            int count = collectionManager.countLessThanAlbumsCount(albumsCountThreshold);
            return new Response(
                    "Количество элементов с albumsCount меньше " + albumsCountThreshold + ": " + count
            );
        } catch (NumberFormatException e) {
            return new Response("Ошибка: некорректное значение albumsCount.");
        } catch (IllegalArgumentException e) {
            return new Response("");
        }
    }
}
