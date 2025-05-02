package org.example.commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для удаления первого элемента из коллекции.
 */
public class RemoveGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 160L;

    public RemoveGreater() {
        super("remove_greater", "remove_greater : удалить из коллекции все элементы, id которых превышает заданный", true);

    }

}