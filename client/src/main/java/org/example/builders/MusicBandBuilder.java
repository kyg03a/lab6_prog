package org.example.builders;

import org.example.exceptions.InvalidDataException;
import org.example.models.MusicBand;

/**
 * Класс для построения объектов типа MusicBand.
 * Наследует функциональность от абстрактного класса Builder.
 * Использует другие билдеры для создания сложных объектов, таких как Coordinates, EstabilishmentDate и Label.
 */
public class MusicBandBuilder extends Builder {

    /**
     * Создает объект типа MusicBand, запрашивая у пользователя значения для всех необходимых полей.
     *
     * @return объект типа MusicBand
     * @throws InvalidDataException если введенные данные некорректны
     */
    public MusicBand create() throws InvalidDataException {
        return new MusicBand(
                buildString("название группы:"),
                new CoordinatesBuilder().create(),
                buildInt("кол-во участников:"),
                buildInt("кол-во альбомов:"),
                buildString("описание:"),
                new MusicGenreBuilder().create(),
                new PersonBuilder().create()
        );
    }
}
