package org.example.builders;

import org.example.exceptions.InvalidDataException;

import java.time.LocalDate;

/**
 * Класс для построения объектов типа Date, представляющих дату основания.
 * Наследует функциональность от абстрактного класса Builder.
 */
public class DateSetBuilder extends Builder {

    /**
     * Создает объект типа Date, запрашивая у пользователя значения для года, месяца и дня.
     *
     * @return объект типа Date, представляющий дату основания
     * @throws InvalidDataException если введенные данные некорректны
     */
    public LocalDate create() throws InvalidDataException {
        int year = buildInt("Год");
        int month = buildMonth("Месяц(1-12)");
        int day = buildDate("день",month,year);
        return LocalDate.of(year,month,day);
    }
}