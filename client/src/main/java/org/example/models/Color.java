package org.example.models;
import java.io.Serializable;

public enum Color implements Serializable {
    BLACK,
    WHITE,
    BROWN;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (Color color : values()) {
            nameList.append(color.name()).append(", ");
        }

        return nameList.substring(0, nameList.length() - 2);
    }
}