package org.example.models;

import org.example.utility.Validatable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.io.Serializable;
import java.io.Serial;


/**
 * Класс, представляющий музыкальную группу.
 * Реализует интерфейсы {@link Validatable} и {@link Comparable<MusicBand>}.
 */
public class MusicBand implements Validatable, Comparable<MusicBand>, Serializable {
    @Serial
    private static final long serialVersionUID = 302L;

    private static long nextId = 1;
    private static final Set<Long> usedIds = new HashSet<>();

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private Integer albumsCount; //Поле не может быть null, Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private MusicGenre genre; //Поле не может быть null
    private Person frontMan;

    public static long idcounter = 1;

    public MusicBand(String name, Coordinates coordinates, LocalDate creationDate,
                     Integer numberOfParticipants, Integer albumsCount, String description,
                     MusicGenre genre, Person frontMan) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = RandomCreationDate();
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.description = description;
        this.genre = genre;
        this.frontMan = frontMan;
        this.id = findNextAvailableId();;
    }

    public MusicBand(String name, Coordinates coordinates, Integer numberOfParticipants,
                     Integer albumsCount, String description, MusicGenre genre, Person frontMan) {
        this(name, coordinates, LocalDate.now(), numberOfParticipants, albumsCount, description, genre, frontMan);
        this.id = idcounter;
        if (validate()) {
            idcounter++;
        }
    }

    private long findNextAvailableId() {
        if (nextId <= Long.MAX_VALUE) {
            while (usedIds.contains(nextId)) {
                nextId++;
            }
            usedIds.add(nextId);
            return nextId++;
        } else {
            for (long i = 1; i <= Long.MAX_VALUE; i++) {
                if (!usedIds.contains(i)) {
                    usedIds.add(i);
                    return i;
                }
            }
            throw new IllegalStateException("Все возможные id использованы.");
        }
    }

    /**
     * Генерирует случайную дату создания в пределах последних 10 лет.
     *
     * @return случайная дата создания
     */
    private LocalDate RandomCreationDate() {
        Random random = new Random();
        long startEpochDay = LocalDate.now().minusYears(10).toEpochDay(); // 10 лет назад
        long endEpochDay = LocalDate.now().toEpochDay(); // Сегодня
        long randomEpochDay = startEpochDay + random.nextLong(endEpochDay - startEpochDay);
        return LocalDate.ofEpochDay(randomEpochDay);
    }

    // геттер для кол-ва альбомов
    public Integer getAlbumsCount() {
        return this.albumsCount;
    }

    // геттер для genre
    public MusicGenre getGenre() {
        return this.genre;
    }


    // геттер для description
    public String getDescription() {
        return this.description;
    }

    public long getId() {
        return this.id;
    }
    public Person getFrontMan() {
        return this.frontMan;
    }

    /**
     * Устанавливает идентификатор музыкальной группы.
     *
     * @param id идентификатор группы
     */
    public void setId(long id) {
        usedIds.remove(this.id);
        this.id = id;
        usedIds.add(this.id);

    }



    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "MusicBand{id: " + id + ", " +
                "name: " + name + ", " +
                "creationDate: " + creationDate.format(formatter) + ", " +
                "coordinates: " + coordinates + ", " +
                "numberOfParticipants: " + (numberOfParticipants == null ? "null" : numberOfParticipants) + ", " +
                "albumsCount: " + (albumsCount == null ? "null" : albumsCount) + ", " +
                "description: " + (description == null ? "null" : description) + ", " +
                "musicGenre: " + (genre == null ? "null" : genre) + ", " +
                "frontMan " + frontMan + "}";
    }

    @Override
    public boolean validate() {
        if (id < 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (genre == null) return false;
        if (frontMan == null) return false;
        if (numberOfParticipants != null && numberOfParticipants <= 0) return false;
        if (albumsCount == null || albumsCount <= 0) return false;
        if (description == null || description.isEmpty()) return false;
        return true;
    }

    public String getName() {
        return this.name;
    }
    @Override
    public int compareTo(MusicBand o) {
        return (int) (this.id - o.id);
    }



    /**
     * Возвращает количество участников музыкальной группы.
     *
     * @return количество участников
     */
    public int getNumberOfParticipants() {
        return this.numberOfParticipants;
    }



}
