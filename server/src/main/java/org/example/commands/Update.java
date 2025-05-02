package org.example.commands;

import org.example.builders.MusicBandBuilder;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.NoElementException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для обновления значения элемента коллекции по заданному идентификатору.
 */
public class Update extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 190L;
    /**
     * Менеджер коллекции, используемый для управления элементами.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор команды.
     *
     * @param collectionManager менеджер коллекции, который будет использоваться для обновления элемента
     */
    public Update(CollectionManager collectionManager) {
        super("update", "update id : обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            long id = (long) request.getArgs();
            collectionManager.updateId(id, request.getMusicBand());
            return new Response("Объект успешно изменен!");
        } catch (InvalidDataException e) {
            return new Response("Введены неверные данные!");
        } catch (NoElementException e) {
            return new Response("Объекта с таким id нет!");
        } catch (NumberFormatException e) {
            return new Response("Введите значение типа long");
        }
    }
}
