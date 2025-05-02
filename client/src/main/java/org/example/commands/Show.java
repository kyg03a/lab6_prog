package org.example.commands;


import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для вывода всех элементов коллекции в строковом представлении.
 */
public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 180L;

    public Show() {
        super("show", "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении", false);
    }


}
