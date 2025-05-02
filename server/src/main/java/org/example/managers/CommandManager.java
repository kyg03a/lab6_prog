package org.example.managers;

import org.example.commands.*;
import org.example.network.Request;
import org.example.network.Response;

import java.util.HashMap;
import java.util.LinkedList;

public class CommandManager {

    private HashMap<String, Command> commands = new HashMap<>();
    private final LinkedList<String> commandHistory = new LinkedList<>();
    private static final int MAX_HISTORY_SIZE = 9;

    public void addCommand(Command command) {
        this.commands.put(command.getName(), command);
    }
    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void addToHistory(String commandName) {
        commandHistory.addLast(commandName);
        if (commandHistory.size() > MAX_HISTORY_SIZE) {
            commandHistory.removeFirst();
        }
    }


    public LinkedList<String> getHistory() {
        return new LinkedList<>(commandHistory);
    }

    public void init(CommandManager commandManager, CollectionManager collectionManager) {
        this.addCommand(new Add(collectionManager));
        commandManager.addCommand(new Clear(collectionManager));
        commandManager.addCommand(new ExecuteScript(commandManager));
        commandManager.addCommand(new CountLess(collectionManager));
        commandManager.addCommand(new Help(collectionManager,commandManager));
        commandManager.addCommand(new Info(collectionManager));
        commandManager.addCommand(new AddIfMin(collectionManager));
        commandManager.addCommand(new FilterGreater(collectionManager));
        commandManager.addCommand(new FilterLess(collectionManager));
        commandManager.addCommand(new RemoveById(collectionManager));
        commandManager.addCommand(new History(commandManager));
        commandManager.addCommand(new Show(collectionManager));
        commandManager.addCommand(new RemoveGreater(collectionManager));
        commandManager.addCommand(new Update(collectionManager));

    }

    public Response execute(Request request) {
        Command command = this.commands.get(request.getCommand().getName());
        if (command != null) {
            this.addToHistory(command.getName());
            return command.execute(request);
        } else {
            return new Response("Команды нет");
        }
    }
}