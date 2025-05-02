package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.models.MusicBand;
import org.example.network.Request;
import org.example.network.Response;


import java.util.Stack;
import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для фильтрации элементов коллекции по длине имени жанра.
 */
public class FilterLess extends Command implements Serializable{
    @Serial
    private static final long serialVersionUID = 110L;
    private final CollectionManager collectionManager; // Менеджер коллекции

    /**
     * Конструктор команды FilterLess.
     *
     * @param collectionManager менеджер коллекции, в которой будет выполняться фильтрация
     */
    public FilterLess(CollectionManager collectionManager) {
        super("filter_less_than_genre", "filter_less_than_genre length : вывести элементы, длина имени жанра которых меньше заданного числа");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            // Получаем пороговую длину из запроса
            int genreLengthThreshold = request.getIntArgument();

            // Получаем коллекцию элементов
            Stack<MusicBand> bands = collectionManager.getBands();

            // Формируем результат
            StringBuilder result = new StringBuilder();
            result.append("Элементы с длиной имени жанра меньше ")
                    .append(genreLengthThreshold)
                    .append(":\n");

            boolean found = false;
            for (MusicBand band : bands) {
                if (band.getGenre() != null &&
                        band.getGenre().name().length() < genreLengthThreshold) {
                    result.append(band).append("\n");
                    found = true;
                }
            }

            if (!found) {
                result.append("Нет элементов, удовлетворяющих условию.");
            }

            return new Response(result.toString());

        } catch (IllegalArgumentException e) {
            return new Response("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            return new Response("");
        }
    }
}