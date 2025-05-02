package org.example.commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для обновления значения элемента коллекции по заданному идентификатору.
 */
public class Update extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 190L;

    public Update() {
        super("update", "update id : обновить значение элемента коллекции, id которого равен заданному", true);

    }


}
