package org.example.managers;

import org.example.exceptions.AlreadyEmptyException;
import org.example.exceptions.EmptyCollectionException;
import org.example.exceptions.InvalidDataException;
import org.example.exceptions.NoElementException;
import org.example.models.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Класс для управления коллекцией музыкальных групп.x
 * Реализует основные операции для работы с коллекцией, такие как добавление, удаление, поиск и сортировка.
 */
public class CollectionManager {
    private Stack<MusicBand> bands = new Stack<MusicBand>(); // Коллекция музыкальных групп
    private LocalDate date; // Дата создания коллекции
    private Integer minParticipants = null;  // Переменная для хранения самой ранней даты создания


    public CollectionManager() {
        this.date = LocalDate.parse(LocalDate.now().toString());
    }

    public Stack<MusicBand> getBands() {
        return this.bands;
    }


    /**
     * Устанавливает дату создания коллекции.
     *
     * @param date дата создания коллекции
     */
    public void setLocaleDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Возвращает дату создания коллекции.
     *
     * @return дата создания коллекции
     */
    public LocalDate getLocaleDate() {
        return this.date;
    }

    /**
     * Проверяет, существует ли в коллекции группа с таким же идентификатором.
     *
     * @param musicBand музыкальная группа для проверки
     * @return true, если идентификатор уникален, иначе false
     */
    public boolean checkId(MusicBand musicBand) {
        for (MusicBand band : bands) {
            if (musicBand.getId() == band.getId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяет, есть ли в коллекции группы с одинаковыми идентификаторами.
     *
     * @return true, если все идентификаторы уникальны, иначе false
     */
    public boolean checkSameId() {
        Set<Long> ids = new HashSet<>();
        for (MusicBand band : bands) {
            if (!ids.add(band.getId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавляет коллекцию музыкальных групп в текущую коллекцию.
     *
     * @param collection коллекция музыкальных групп для добавления
     * @throws InvalidDataException если данные группы невалидны
     */
    public void addElem(Collection<MusicBand> collection) throws InvalidDataException {
        if (collection == null) return;
        for (MusicBand band : collection) {
            add(band);
        }
    }

    /**
     * Добавляет музыкальную группу в коллекцию.
     *
     * @param band музыкальная группа для добавления
     * @throws InvalidDataException если данные группы невалидны
     */
    public void add(MusicBand band) throws InvalidDataException {
        if (!band.validate()) {
            throw new InvalidDataException();
        }
        while (!checkId(band)) {
            MusicBand.idcounter++;
            band.setId(MusicBand.idcounter);
        }
        bands.add(band);
        updateMinParticipants(band.getNumberOfParticipants());
    }

    /**
     * Обновляет переменную с минимальным количеством участников.
     *
     * @param numberOfParticipants количество участников нового элемента
     */
    private void updateMinParticipants(Integer numberOfParticipants) {
        if (minParticipants == null || numberOfParticipants < minParticipants) {
            minParticipants = numberOfParticipants;
        }
    }

    /**
     * Возвращает минимальное количество участников в коллекции.
     *
     * @return минимальное количество участников
     */
    public Integer getMinParticipants() {
        return minParticipants;
    }


    /**
     * Возвращает музыкальную группу по идентификатору.
     *
     * @param id идентификатор группы
     * @return музыкальная группа или null, если группа не найдена
     */
    public MusicBand getById(long id) {
        for (MusicBand band : bands) {
            if (band.getId() == id) return band;
        }
        return null;
    }

    /**
     * Удаляет музыкальную группу по идентификатору.
     *
     * @param id идентификатор группы
     * @throws NoElementException если группа с таким идентификатором не найдена
     */
    public void removeById(long id) throws NoElementException {
        MusicBand band = getById(id);
        if (band == null) {
            throw new NoElementException();
        }
        bands.remove(band);
    }



    /**
     * Очищает коллекцию.
     *
     * @throws AlreadyEmptyException если коллекция уже пуста
     */
    public void clear() throws AlreadyEmptyException {
        if (bands.isEmpty()) {
            throw new AlreadyEmptyException();
        }
        bands.clear();
    }

    /**
     * Возвращает дату создания коллекции.
     *
     * @return дата создания коллекции
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Возвращает тип коллекции.
     *
     * @return тип коллекции
     */
    public String getTypeOfCollection() {
        return "Stack";
    }

    /**
     * Возвращает размер коллекции.
     *
     * @return размер коллекции
     */
    public int size() {
        return bands.size();
    }

    /**
     * Возвращает информацию о коллекции.
     *
     * @return строка с информацией о типе, дате создания и размере коллекции
     */
    public String info() {
        return "Тип: " + getTypeOfCollection() + "\n" +
                "Дата Создания: " + getDate() + "\n" +
                "Размер: " + size();
    }



    /**
     * Обновляет музыкальную группу по идентификатору.
     *
     * @param id   идентификатор группы
     * @param band новая музыкальная группа
     * @throws NoElementException  если группа с таким идентификатором не найдена
     * @throws InvalidDataException если данные группы невалидны
     */
    public void updateId(long id, MusicBand band) throws NoElementException, InvalidDataException {
        MusicBand oldElem = getById(id);
        if (oldElem == null) {
            throw new NoElementException();
        }
        if (!band.validate()) {
            throw new InvalidDataException();
        }
        bands.remove(oldElem);
        band.setId(id);
        bands.add(band);
    }




    /**
     * Выводит все музыкальные группы в коллекции.
     *
     * @throws EmptyCollectionException если коллекция пуста
     */
    public String show() throws EmptyCollectionException {
        if (bands.isEmpty()) {
            throw new EmptyCollectionException();
        }

        return bands.stream()
                .map(MusicBand::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданное значение id.
     *
     * @param comparisonId значение id для сравнения
     * @throws EmptyCollectionException если коллекция пуста
     */
    public void removeGreater(long comparisonId) throws EmptyCollectionException {
        if (bands.isEmpty()) {
            throw new EmptyCollectionException();
        }


        Stack<MusicBand> tempStack = new Stack<>();


        while (!bands.isEmpty()) {
            MusicBand current = bands.pop();
            if (current.getId() <= comparisonId) {
                tempStack.push(current);
            }
        }

        while (!tempStack.isEmpty()) {
            bands.push(tempStack.pop());
        }
    }



    /**
     * Возвращает количество элементов с albumsCount меньше заданного значения.
     *
     * @param albumsCountThreshold значение для сравнения
     * @return количество элементов
     */
    public int countLessThanAlbumsCount(int albumsCountThreshold) {
        int count = 0;
        for (MusicBand band : bands) {
            if (band.getAlbumsCount() < albumsCountThreshold) {
                count++;
            }
        }
        return count;
    }


}
