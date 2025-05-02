package org.example.models;
import java.io.Serializable;
public enum Country implements Serializable {
    CHINA,
    INDIA,
    VATICAN,
    NORTH_KOREA;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (Country nationality : values()) {
            nameList.append(nationality.name()).append(", ");
        }

        return nameList.substring(0, nameList.length() - 2);
    }
}
