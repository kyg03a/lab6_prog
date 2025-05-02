package org.example.commands;


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

    public ExecuteScript() {
        super("execute_script", "execute_script file_name : " +
                "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, " +
                "в котором их вводит пользователь в интерактивном режиме.", true);

    }


}