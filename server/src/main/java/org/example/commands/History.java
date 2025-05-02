package org.example.commands;

import org.example.managers.CommandManager;
import org.example.network.Response;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.Serial;
import java.io.Serializable;
import org.example.network.Request;
import org.example.network.Response;
/**
 * Команда для вывода последних 9 выполненных команд.
 */
public class History extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 130L;
    private final CommandManager commandManager;
    /**
     * Конструктор команды History.
     *
     * @param commandManager менеджер команд, который содержит историю выполненных команд
     */
    public History(CommandManager commandManager) {
        super("history", "history : вывести последние 9 выполненных команд");
        this.commandManager = commandManager;
    }


    @Override
    public Response execute(Request request) {
        LinkedList<String> history = commandManager.getHistory();

        // Создаем StringBuilder для формирования результата
        StringBuilder result = new StringBuilder();
        result.append("Последние выполненные команды:\n");

        // Добавляем все команды из истории
        for (String command : history) {
            result.append(command).append("\n");
        }

        // Возвращаем ответ с историей команд
        return new Response(result.toString());
    }
}
