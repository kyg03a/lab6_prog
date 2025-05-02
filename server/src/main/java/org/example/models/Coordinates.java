package org.example.models;
import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 8888L;
    /**
     * Координата x. Поле не может быть null.
     */
    private Double x;

    /**
     * Координата y. Максимальное значение поля: 968.
     */
    private int y;

    /**
     * Конструктор для создания объекта Coordinates.
     *
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Double x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает строковое представление координат в формате "x;y".
     *
     * @return строковое представление координат
     */
    @Override
    public String toString() {
        return x + ";" + y;
    }

    /**
     * Возвращает значение координаты x.
     *
     * @return значение координаты x
     */
    public double getX() {
        return x;
    }

    /**
     * Возвращает значение координаты y.
     *
     * @return значение координаты y
     */
    public long getY() {
        return y;
    }
}
