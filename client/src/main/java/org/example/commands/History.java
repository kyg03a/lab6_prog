package org.example.commands;


import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для вывода последних 9 выполненных команд.
 */
public class History extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 130L;

    public History() {
        super("history", "history : вывести последние 9 выполненных команд", false);
    }


}
