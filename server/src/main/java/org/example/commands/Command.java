package org.example.commands;

import org.example.network.Request;
import org.example.network.Response;

import java.io.Serial;
import java.io.Serializable;
/**
 * Абстрактный класс, представляющий команду.
 * Каждая команда имеет имя, описание и метод для выполнения.
 */
public abstract class Command implements Serializable {


    @Serial
    private static final long serialVersionUID = 666L;
    private final String name; // Название команды
    private final String description; // Описание команды
    /**
     * Конструктор для создания команды.
     *
     * @param name название команды
     * @param description описание команды
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }



    public abstract Response execute(Request request);
}
