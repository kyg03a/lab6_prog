package org.example.utility;


import org.example.managers.*;
import org.example.network.NetworkServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ServerInitializer {
    private static final Logger log = LoggerFactory.getLogger(ServerInitializer.class);
    private final String[] args;

    public ServerInitializer(String[] args) {
        this.args = args;
    }

    public void launchServer() {

        try {

            CollectionManager collectionManager = new CollectionManager();
            FileManager fileManager = new FileManager(collectionManager);


            if (args.length > 0 && !args[0].trim().isEmpty()) {
                try {
                    fileManager.readFromCollection(args[0]);
                    log.info("Коллекция успешно загружена из файла: {}", args[0]);
                } catch (IOException e) {
                    log.error("Ошибка загрузки коллекции из файла: {}", e.getMessage());
                }
            } else {
                log.warn("Файл для загрузки коллекции не указан. Будет использована пустая коллекция.");
            }


            CommandManager commandManager = new CommandManager();
            RunManager runManager = new RunManager(commandManager);
            commandManager.init(commandManager, collectionManager);

            NetworkServer server = new NetworkServer("localhost", runManager,1830,  fileManager);
            server.initialize();

        } catch (Exception e) {
            log.error("Критическая ошибка запуска сервера: ", e);
            System.exit(1);
        }
    }
}