package org.example.models;
import java.io.Serial;
import java.util.Objects;
import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {

    @Serial
    private static final long serialVersionUID = 7777L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Float height; //Поле не может быть null, Значение поля должно быть больше 0
    private Float weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    public Person(String name, Float height, Float weight, Color hairColor, Country nationality) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "name: " + name + ", " +
                "height: " + height + ", " +
                "weight: " + weight + ", " +
                "hairColor: " + hairColor + ", " +
                "nationality: " + nationality;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return height == person.height && weight == person.weight &&
                hairColor == person.hairColor && nationality == person.nationality &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight, hairColor, nationality);
    }

}
