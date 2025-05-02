package org.example.builders;
import org.example.exceptions.InvalidDataException;
import org.example.models.Person;

/**
 * Класс для построения объектов типа MusicGenre.
 * Наследует функциональность от абстрактного класса Builder.
 * Позволяет пользователю выбрать жанр музыки из доступных вариантов.
 */
public class PersonBuilder extends Builder {
    /**
     * Создает объект типа Person, запрашивая у пользователя значения для полей name, bands и sales.
     *
     * @return объект типа Person
     * @throws InvalidDataException если введенные данные некорректны
     */
    public Person create() throws InvalidDataException {
        return new Person(
                buildString("имя главного в группе:"),
                buildFloat("его рост:"),
                buildFloat("его вес:"),
                new ColorBuilder().create(),
                new CountryBuilder().create());
    }
}