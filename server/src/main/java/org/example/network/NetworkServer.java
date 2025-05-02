package org.example.network;

import org.example.managers.FileManager;
import org.example.managers.RunManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;


public class NetworkServer {
    private static final Logger log = LoggerFactory.getLogger(NetworkServer.class);

    private final String bindAddress;
    private final int listenPort;
    private final FileManager storage;
    private final RunManager executor;
    private ServerSocketChannel serverChannel;
    private boolean running = true;


    public NetworkServer(String bindAddress, RunManager executor, int listenPort, FileManager storage) {
        this.bindAddress = bindAddress;
        this.listenPort = listenPort;
        this.storage = storage;
        this.executor = executor;
    }

    public void launchServer() {
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(bindAddress, listenPort));
            serverChannel.configureBlocking(false);
            log.info("Сервер запущен на {}:{}", bindAddress, listenPort);
        } catch (IOException e) {
            log.error("Не удалось запустить сервер на порту {}: {}", listenPort, e.toString());
        }
    }

    private void handleConnection(SocketChannel clientChannel) {
        Request userRequest;
        Response responseToUser;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientChannel.socket().getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientChannel.socket().getOutputStream())) {

            userRequest = (Request) clientReader.readObject();
            if (userRequest.getArgs() != null) {
                if (userRequest.getArgs().equals("exit")) {
                    try {
                        storage.saveData("server/src/main/java/org/example/pookie.xml");
                        Response response = new Response();
                        clientWriter.writeObject(response);
                        clientWriter.flush();

                    }catch (IOException | NullPointerException e) {
                        log.error("Путь неверный, сохранение не удалось");
                        Response response = new Response("Путь неверный, сохранение не удалось");
                        clientWriter.writeObject(response);
                        clientWriter.flush();
                    } finally {
                        closeClientSocket(clientChannel);
                    }
                } else if (userRequest.getCommand().getName().equals("execute_script")) {

                    responseToUser = executor.process(userRequest);
                    clientWriter.writeObject(responseToUser);
                    clientWriter.flush();
                }

            } else {
                log.info("Запрос с командой " + userRequest.getCommand().getName());
                responseToUser = executor.process(userRequest);
                clientWriter.writeObject(responseToUser);
                log.info("Отправлен ответ ");
                clientWriter.flush();
            }
        } catch (ClassNotFoundException | InvalidClassException | NotSerializableException exception) {
            exception.printStackTrace();
            log.error("Ошибка при взаимодействии с клиентом!");
        } catch (IOException exception) {
            exception.printStackTrace();
            log.error("Ошибка ввода вывода");
        }
    }



    public void initialize() {
        launchServer();
        log.info("Сервер готов к работе");

        try {
            while (running) {

                SocketChannel client = serverChannel.accept();
                if (client != null) {
                    handleConnection(client);
                }

                try {
                    Thread.sleep(100); // 100ms задержка
                } catch (InterruptedException e) {
                    log.warn("Прервано ожидание", e);
                }
            }
        } catch (IOException e) {
            log.error("Ошибка работы сервера: {}", e.toString());
        }
    }



    private void closeClientSocket(SocketChannel channel) {
        try {
            if (channel != null)
                channel.close();
        } catch (IOException e) {
            log.debug("Ошибка закрытия соединения: {}", e.toString());
        }
    }

}