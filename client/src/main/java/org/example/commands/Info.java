package org.example.commands;



import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для вывода информации по всем командам
 * Реализует абстрактный метод execute() из класса Command.
 */
public class Info extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 140L;

    public Info() {
        super("info", "info : вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)", false);
    }


}
