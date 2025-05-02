package org.example;



import org.example.exceptions.InvalidDataException;

import org.example.managers.CollectionManager;
import org.example.managers.FileManager;
import org.example.network.RequestManager;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws InvalidDataException, InterruptedException {
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(collectionManager);
        RequestManager program = new RequestManager(fileManager, collectionManager);
        program.execute();



    }
}