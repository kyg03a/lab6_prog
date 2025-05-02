package org.example.commands;

import org.example.exceptions.EmptyCollectionException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для удаления первого элемента из коллекции.
 */
public class RemoveGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 160L;
    /**
     * Менеджер коллекции, используемый для управления элементами.
     */
    private final CollectionManager collectionManager;


    /**
     * Конструктор команды.
     *
     * @param collectionManager менеджер коллекции, который будет использоваться для удаления первого элемента
     */
    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", "remove_greater : удалить из коллекции все элементы, id которых превышает заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            long comparisonId = request.getLongArgument();

            collectionManager.removeGreater(comparisonId);

            return new Response("Элементы успешно удалены!");

        } catch (EmptyCollectionException e) {
            return new Response("Ошибка: коллекция пуста!");
        } catch (IllegalArgumentException e) {
            return new Response("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            return new Response("Ошибка при выполнении команды: " + e.getMessage());
        }
    }
}