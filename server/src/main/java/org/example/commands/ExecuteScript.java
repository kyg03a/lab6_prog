package org.example.commands;

import org.example.builders.MusicBandBuilder;
import org.example.managers.CommandManager;
import org.example.managers.ScriptExecuteManager;
import org.example.models.MusicBand;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.FileMode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
/**
 * Команда для выполнения скрипта из указанного файла.
 * Реализует абстрактный метод execute() из класса Command.
 * Скрипт содержит команды в том же формате, что и ввод пользователя в интерактивном режиме.
 */
public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 80L;
    private final CommandManager commandManager;

    /**
     * Конструктор команды ExecuteScript.
     *
     * @param commandManager менеджер команд, который будет выполнять команды из скрипта
     */
    public ExecuteScript(CommandManager commandManager) {
        super("execute_script", "execute_script file_name : " +
                "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, " +
                "в котором их вводит пользователь в интерактивном режиме.");
        this.commandManager = commandManager;
    }


    @Override
    public Response execute(Request request) {
        String path = ((String) request.getArgs()).trim();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileMode.setFileMode(true);
            ScriptExecuteManager.pushFile(path);

            String line;
            while ((line = ScriptExecuteManager.readfile()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // Пропускаем пустые строки

                String[] cmd = (line + " ").split(" ", 2);
                cmd[1] = cmd[1].trim();

                // Обработка вложенных скриптов
                if (cmd[0].equals("execute_script")) {
                    if (ScriptExecuteManager.IsRepeat(cmd[1])) {
                        stringBuilder.append("Обнаружена рекурсия").append("\n\n");
                        continue;
                    }
                }

                if (commandManager.getCommands().get(cmd[0]) == null) {
                    continue; // Пропускаем неизвестные команды
                }

                Response response;
                if (cmd[0].equals("remove_by_id") || cmd[0].equals("remove_greater")
                        || cmd[0].equals("count_less_than_albums_count")
                        || cmd[0].equals("filter_greater_than_description")
                        || cmd[0].equals("filter_less_than_genre")) {

                    response = commandManager.execute(
                            new Request(commandManager.getCommands().get(cmd[0]),
                                    Integer.parseInt(cmd[1])));

                } else if (cmd[0].equals("add") || cmd[0].equals("add_if_min")) {
                    MusicBand band = new MusicBandBuilder().create();
                    response = commandManager.execute(
                            new Request(commandManager.getCommands().get(cmd[0]), band));

                } else if (cmd[0].equals("update")) {
                    MusicBand band = new MusicBandBuilder().create();
                    response = commandManager.execute(
                            new Request(commandManager.getCommands().get(cmd[0]),band, Integer.parseInt(cmd[1])));

                } else {
                    response = commandManager.execute(
                            new Request(commandManager.getCommands().get(cmd[0]), cmd[1]));
                }

                stringBuilder.append(response.getResult()).append("\n\n");

                if (cmd[0].equals("execute_script")) {
                    ScriptExecuteManager.popfile();
                }
            }

            return new Response(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            return new Response("Файл не найден");
        } catch (IOException e) {
            return new Response("Ошибка чтения");
        } catch (NumberFormatException e) {
            return new Response("Ошибка: неверный числовой аргумент");
        } finally {
            FileMode.setFileMode(false);
            try {
                if (ScriptExecuteManager.hasOpenFiles()) {
                    ScriptExecuteManager.popfile();
                }
            } catch (IOException e) {
                // Логируем, но не прерываем выполнение
            }
        }
    }

}