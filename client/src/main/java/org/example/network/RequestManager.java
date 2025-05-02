package org.example.network;

import org.example.builders.MusicBandBuilder;
import org.example.commands.*;
import org.example.exceptions.InvalidDataException;
import org.example.managers.CollectionManager;
import org.example.managers.FileManager;
import org.example.models.MusicBand;
import org.example.managers.CommandManager;

import java.util.Scanner;


public class RequestManager {
    private final FileManager fileManager;
    private CollectionManager collectionManager;

    public RequestManager(FileManager fileManager, CollectionManager CollectionManager) {
        this.fileManager = fileManager;
        this.collectionManager = CollectionManager;
    }

    public void execute() throws InvalidDataException, InterruptedException {
        CommandManager commands = new CommandManager();

        commands.putCommand(new Add());
        commands.putCommand(new AddIfMin());
        commands.putCommand(new Clear());
        commands.putCommand(new CountLess());
        commands.putCommand(new ExecuteScript());
        commands.putCommand(new FilterGreater());
        commands.putCommand(new FilterLess());
        commands.putCommand(new Help());
        commands.putCommand(new History());
        commands.putCommand(new Info());
        commands.putCommand(new RemoveById());
        commands.putCommand(new RemoveGreater());
        commands.putCommand(new Show());
        commands.putCommand(new Update());

        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("localhost",1830,5000,3);



        System.out.println("Введите help для получения списка команд: ");
        while (true) {
            String cmd = (scanner.nextLine()+" ").trim();
            input = cmd.split(" ");
            if (input[0].equals("exit")) {
                System.out.println(client.sendRequest(new Request("exit")).getResult());
                System.out.println("Коллекция сохранена. Завершение работы");
                Thread.sleep(200);
                System.exit(0);
            }


            if (commands.getCommands().get(input[0]) == null) {
                System.err.println("Такой команды нет! Попытайтесь снова или обратитесь в help");
                continue;
            }

            Command command = commands.getCommands().get(input[0]);
            if (!command.isHasArgs()) {
                if (input.length != 1) {
                    System.err.println("Здесь не должно быть аргументов");
                    continue;
                }

                if (input[0].equals("add") || input[0].equals("add_if_min")) {
                    MusicBand band = new MusicBandBuilder().create();
                    System.out.println(client.sendRequest(new Request(command, band)).getResult());
                } else {
                    try {
                        Request request = new Request(command);
                        System.out.println(client.sendRequest(request).getResult());
                    } catch (NullPointerException e) {
                        System.out.println("Клиент не смог подключиться к серверу");
                        System.exit(505);
                    }
                }
                continue;
            }

            if (input.length != 2) {
                System.err.println("Введите только один аргумент");
                continue;
            }
            if (!(input[0].equals("execute_script"))) {
                try {
                    switch (input[0]) {
                        case "update":
                            long id = Long.parseLong(input[1]);
                            MusicBand band = new MusicBandBuilder().create();
                            System.out.println(client.sendRequest(new Request(command,band,id)).getResult());
                            continue;

                        case "remove_greater":
                            long greaterId = Long.parseLong(input[1]);
                            MusicBand iBand = new MusicBandBuilder().create();
                            System.out.println(client.sendRequest(
                                    new Request(command, iBand, greaterId)).getResult());
                            break;

                        case "remove_by_id":
                            long removeId = Long.parseLong(input[1]);
                            System.out.println(client.sendRequest(
                                    new Request(command, removeId)).getResult());
                            break;

                        case "count_less_than_albums_count":
                            int albumsCount = Integer.parseInt(input[1]);
                            System.out.println(client.sendRequest(
                                    new Request(command, albumsCount)).getResult());
                            break;

                        case "filter_greater_than_description":
                            int description = Integer.parseInt(input[1]);
                            System.out.println(client.sendRequest(
                                    new Request(command, description)).getResult());
                            break;

                        case "filter_less_than_genre":
                            System.out.println(client.sendRequest(
                                    new Request(command, input[1])).getResult());
                            continue;

                        default:
                            System.err.println("Неизвестная команда с аргументами");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: аргумент должен быть числом");
                } catch (Exception e) {
                    System.err.println("Ошибка выполнения команды: " + e.getMessage());
                }


            } else {
                Request request = new Request(command, input[1].toUpperCase());
                System.out.println(client.sendRequest(request).getResult());
            }
        }

    }
}