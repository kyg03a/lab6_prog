package org.example.commands;



import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для добавления нового элемента в коллекцию.
 * Реализует абстрактный метод execute() из класса Command.
 */
public class FilterGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 100L;

    public FilterGreater() {
        super("filter_greater_than_description", "filter_greater_than_description length : вывести элементы, значение поля description которых больше заданной длины", true);

    }


}

