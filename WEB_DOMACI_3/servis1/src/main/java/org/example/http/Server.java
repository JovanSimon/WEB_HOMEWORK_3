package org.example.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    public static final int PORT = 10101;
    public static List<String> listOfQuotes = new CopyOnWriteArrayList<>();
    public static String quoteOfTheDay;

    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int serverPort = 10102;

        try {
            Socket socket = new Socket(serverAddress, serverPort);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            out.println("GET /get-quote HTTP/1.1");

            String response;

            while(!(response = in.readLine()).isEmpty()){
                System.out.println(response);
            }

            response = in.readLine();

            splitQotd(response);

            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private static void splitQotd(String response) {
        String[] firstSplit = response.split(":");

        String newString = firstSplit[1].substring(1, firstSplit[1].length() - 2);
        quoteOfTheDay = newString;
    }
}
