package org.example.builders;
import org.example.models.Country;

/**
 * Класс для построения объектов типа MusicGenre.
 * Наследует функциональность от абстрактного класса Builder.
 * Позволяет пользователю выбрать жанр музыки из доступных вариантов.
 */
public class CountryBuilder extends Builder {

    /**
     * Создает объект типа MusicGenre, запрашивая у пользователя выбор жанра из доступных вариантов.
     *
     * @return объект типа MusicGenre, соответствующий введенному пользователем жанру
     */
    public Country create() {
        System.out.println("Страна (происхождение исполнителя): ");
        System.out.println(Country.names());

        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Country.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Такой страны нет. Попробуйте еще раз");
            }
        }
    }
}
