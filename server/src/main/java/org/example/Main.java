package org.example;
import org.example.managers.FileManager;
import org.example.utility.ServerInitializer;

public class Main {
    public static void main(String[] args) {

        ServerInitializer serverInitializer = new ServerInitializer(args);
        serverInitializer.launchServer();
    }
}
