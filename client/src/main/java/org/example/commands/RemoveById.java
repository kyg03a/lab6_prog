package org.example.commands;


import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для удаления элемента из коллекции по его идентификатору.
 */
public class RemoveById extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 150L;

    public RemoveById() {
        super("remove_by_id", "remove_by_id id : удалить элемент из коллекции по его id", true);

    }

}
