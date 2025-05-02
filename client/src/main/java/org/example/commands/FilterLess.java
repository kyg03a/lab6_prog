package org.example.commands;


import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для фильтрации элементов коллекции по длине имени жанра.
 */
public class FilterLess extends Command implements Serializable{
    @Serial
    private static final long serialVersionUID = 110L;

    public FilterLess() {
        super("filter_less_than_genre", "filter_less_than_genre length : вывести элементы, длина имени жанра которых меньше заданного числа", true);
    }


}