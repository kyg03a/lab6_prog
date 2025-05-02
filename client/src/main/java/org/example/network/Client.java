package org.example.network;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    private int listenPort;
    private String bindAddress;
    private int timeout;
    private SocketChannel socket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private int maxReconnectionAttempts;


    public Client(String bindAddress, int listenPort, int timeout, int maxReconnectionAttempts) {
        this.bindAddress = bindAddress;
        this.listenPort = listenPort;
        this.timeout = timeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
    }


    public void connect() {
        try {
            socket = SocketChannel.open();
            socket.connect(new InetSocketAddress(bindAddress,listenPort));
            writer = new ObjectOutputStream(socket.socket().getOutputStream());
            reader = new ObjectInputStream(socket.socket().getInputStream());
        }catch (IOException e) {
            System.err.println("Ошибка подключения к серверу");
            log.error("Ошибка подключения к {}:{} - {}", bindAddress, listenPort, e.getMessage());
        }
    }


    public void disconnect() {
        try {
            socket.close();
            reader.close();
            writer.close();
        }catch (IOException e) {
            System.err.println("Не подключено к серверу");
        }
    }


    public Response sendRequest(Request request) throws InterruptedException {
        for (int attempt = 0; attempt < maxReconnectionAttempts; attempt++) {
            try {
                if (!isConnected()) {
                    connect();
                }


                if (request == null || (request.getCommand() == null && !"exit".equals(request.getArgs()))) {
                    log.warn("Некорректный запрос: {}", request);
                    return new Response("Ошибка: некорректный запрос");
                }


                writer.writeObject(request);
                writer.flush();
                log.debug("Запрос отправлен: {}", request);


                Response response = (Response) reader.readObject();
                log.debug("Получен ответ: {}", response.getResult());

                return response;

            } catch (SocketTimeoutException e) {
                log.warn("Таймаут ожидания ответа (попытка {}/{})", attempt + 1, maxReconnectionAttempts);
            } catch (IOException e) {
                log.warn("Ошибка связи (попытка {}/{}): {}", attempt + 1, maxReconnectionAttempts, e.getMessage());
                disconnect();
            } catch (ClassNotFoundException e) {
                log.error("Ошибка десериализации: {}", e.getMessage());
                return new Response("Ошибка: неверный формат ответа");
            }


            if (attempt < maxReconnectionAttempts - 1) {
                log.info("Повторная попытка через {} сек...", timeout/1000);
                Thread.sleep(timeout);
            }
        }

        log.error("Не удалось выполнить запрос после {} попыток", maxReconnectionAttempts);
        return new Response("Ошибка: сервер недоступен");
    }

    private boolean isConnected() {
        return socket != null && socket.isConnected();
    }


}