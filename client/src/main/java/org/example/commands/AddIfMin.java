package org.example.commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для добавления нового элемента в коллекцию с условием
 * Реализует абстрактный метод execute() из класса Command.
 */
public class AddIfMin extends Command implements Serializable{
    @Serial
    private static final long serialVersionUID = 1338L;

    public AddIfMin() {
        super("add_if_min", "add_if_min {element} : добавить новый элемент в коллекцию, если число участников группы меньше, чем наименьшее кол-во участников", false);

    }


}

