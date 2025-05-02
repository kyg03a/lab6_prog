package org.example.builders;

import org.example.models.Color;

/**
 * Класс для построения объектов типа MusicGenre.
 * Наследует функциональность от абстрактного класса Builder.
 * Позволяет пользователю выбрать жанр музыки из доступных вариантов.
 */
public class ColorBuilder extends Builder {

    /**
     * Создает объект типа MusicGenre, запрашивая у пользователя выбор жанра из доступных вариантов.
     *
     * @return объект типа MusicGenre, соответствующий введенному пользователем жанру
     */
    public Color create() {
        System.out.println("Цвет волос: ");
        System.out.println(Color.names()); // Выводим список доступных жанров

        while (true) {
            String input = scanner.nextLine().trim(); // Читаем ввод пользователя
            try {
                return Color.valueOf(input.toUpperCase()); // Пытаемся преобразовать ввод в значение перечисления
            } catch (IllegalArgumentException e) {
                System.err.println("Такого цвета нет. Попробуйте еще раз"); // Если жанр не найден, выводим сообщение об ошибке
            }
        }
    }
}
