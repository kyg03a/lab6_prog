package org.example.commands;


import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для добавления нового элемента в коллекцию.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class Add extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1257L;

    public Add() {
        super("add", "add {element} : добавить новый элемент в коллекцию", false);
    }

}
