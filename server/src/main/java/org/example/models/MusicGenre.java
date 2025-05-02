package org.example.models;
import java.io.Serializable;
public enum MusicGenre implements Serializable {

    PSYCHEDELIC_ROCK,
    RAP,
    PSYCHEDELIC_CLOUD_RAP,
    SOUL,
    MATH_ROCK;

    /**
     * Возвращает строку, содержащую все названия музыкальных жанров, разделенные запятыми.
     *
     * @return строка с названиями жанров
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (MusicGenre genre : values()) {
            nameList.append(genre.name()).append(", ");
        }

        return nameList.substring(0, nameList.length() - 2);
    }
}
