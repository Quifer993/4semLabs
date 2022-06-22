package ru.nsu.fit.oop.zolotorevskii.lab5;

import ru.nsu.fit.oop.zolotorevskii.lab5.Model.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }
}
