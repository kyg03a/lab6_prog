package org.example.commands;


import java.io.Serial;
import java.io.Serializable;


public class CountLess extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 50L;

    public CountLess() {
        super("count_less_than_albums_count", "count_less_than_albums_count albumsCount : вывести количество элементов, значение поля albumsCount которых меньше заданного", false);

    }


}
