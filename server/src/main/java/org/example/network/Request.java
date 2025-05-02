package org.example.network;

import org.example.commands.Command;
import org.example.models.MusicBand;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 1414L;
    public MusicBand musicBand;
    Command command;
    Object args;


    public Request(Command command) {
        this.command = command;

    }

    public Request(Command command, Object args) {
        this.command = command;
        this.args = args;
    }
    public Request(Command command,MusicBand band, Object args) {
        this.command = command;
        this.musicBand = band;
        this.args = args;
    }

    public Request(MusicBand musicBand) {
        this.musicBand = musicBand;
    }

    public Request(Command command, MusicBand band) {
        this.command = command;
        this.musicBand = band;
    }
    public Request (String string) {
        this.args = string;
    }


    public Command getCommand() {
        return this.command;
    }

    public MusicBand getMusicBand() {
        return this.musicBand;
    }

    public Object getArgs() {
        return args;
    }
    public int getIntArgument() {

        if (args == null) {
            throw new IllegalArgumentException("Аргумент не указан (null)");
        }

        try {
            if (args instanceof Number) {
                return ((Number) args).intValue();
            } else if (args instanceof String) {
                String trimmedArg = ((String) args).trim();
                return Integer.parseInt(trimmedArg);
            } else {
                throw new IllegalArgumentException(
                        "Неподдерживаемый тип аргумента: " + args.getClass().getName()
                );
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Аргумент должен быть целым числом. Получено: '" + args + "'"
            );
        }
    }
    public long getLongArgument() {
        if (args == null) {
            throw new IllegalArgumentException("Аргумент не указан (null)");
        }

        try {
            if (args instanceof Number) {
                return ((Number) args).longValue();
            } else if (args instanceof String) {
                String trimmedArg = ((String) args).trim();
                return Long.parseLong(trimmedArg);
            } else {
                throw new IllegalArgumentException(
                        "Неподдерживаемый тип аргумента: " + args.getClass().getName()
                );
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Аргумент должен быть числом (long). Получено: '" + args + "'"
            );
        }
    }


}