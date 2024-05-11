package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int PORT = 10102;
    public static final List<String> listOfQuotes = Arrays.asList("You must be the change you wish to see in the world. -Mahatma Gandhi",
            "The only thing we have to fear is fear itself. -Franklin D. Roosevelt",
            "Darkness cannot drive out darkness, only light can do that. Hate cannot drive out hate: only love can do that. -Martin Luther King Jr.",
            "Do one thing every day that scares you. -Eleanor Roosevelt");

    public static String qotd = "";

    public static void main(String[] args) {
        qotdIt();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void qotdIt() {
        Random random = new Random();
        int rnd = random.nextInt(listOfQuotes.size());

        qotd = listOfQuotes.get(rnd);
    }
}