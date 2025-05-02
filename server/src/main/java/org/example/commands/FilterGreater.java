package org.example.commands;


import org.example.managers.CollectionManager;
import org.example.models.MusicBand;
import org.example.network.Request;
import org.example.network.Response;

import java.util.Stack;
import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для добавления нового элемента в коллекцию.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class FilterGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 100L;
    private final CollectionManager collectionManager; // Менеджер коллекции, в которую добавляется элемент


    /**
     * Конструктор команды filter_greater_than_description
     *
     * @param collectionManager менеджер коллекции, в которую будет добавлен новый элемент
     */
    public FilterGreater(CollectionManager collectionManager) {
        super("filter_greater_than_description", "filter_greater_than_description length : вывести элементы, значение поля description которых больше заданной длины");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            int descriptionLengthThreshold = (int) request.getArgs();

            Stack<MusicBand> bands = collectionManager.getBands();

            StringBuilder result = new StringBuilder();
            result.append("Элементы с длиной description больше ").append(descriptionLengthThreshold).append(":\n");

            for (MusicBand band : bands) {
                if (band.getDescription().length() > descriptionLengthThreshold) {
                    result.append(band).append("\n");
                }
            }

            if (result.toString().endsWith(":\n")) {
                result.append("Нет элементов, удовлетворяющих условию.");
            }

            return new Response(result.toString());
        } catch (NumberFormatException e) {
            return new Response("Ошибка: некорректный формат числа");
        } catch (IllegalArgumentException e) {
            return new Response("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            return new Response("");
        }
    }
}

