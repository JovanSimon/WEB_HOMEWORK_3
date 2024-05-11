package org.example;

import org.example.controller.RequestHandler;
import org.example.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.StringTokenizer;

public class ServerThread implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket sock) {
        this.client = sock;

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String requestLine = in.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            Request request = new Request(HttpMethod.valueOf(method), path, Main.qotd);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            out.println(response.getResponseString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
