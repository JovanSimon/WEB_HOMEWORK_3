package org.example.http;

import org.example.http.controller.RequestHandler;
import org.example.http.response.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
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

            int contentLength = 0;

            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();

                if(requestLine.contains("Content-Length"))
                    contentLength = Integer.parseInt(requestLine.split(":")[1].trim());

            } while (!requestLine.trim().equals(""));

            String postBody = "";

            if (method.equals(HttpMethod.POST.toString())) {
                char[] buffer = new char[contentLength];
                in.read(buffer);
                postBody = new String(buffer);
                System.out.println(postBody);
            }

            String finalQuote = "";

            if (!postBody.isEmpty())
                finalQuote = quoteIt(postBody);

            Request request = new Request(HttpMethod.valueOf(method), path, finalQuote);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

//            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String quoteIt(String postBody) {
        String[] firstSplit = postBody.split("&");

        String author = finalSplit(firstSplit[0]);

        String quote = finalSplit(firstSplit[1]);

        return author + " : \"" + quote + "\"";
    }

    private String finalSplit(String s) {
        String[] firstSplit = s.split("=");

        String[] secondSplit = firstSplit[1].split("\\+");

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < secondSplit.length; i++){
            stringBuilder.append(secondSplit[i]);
            stringBuilder.append(" ");
        }

        String finale = stringBuilder.toString().trim();

        return finale;
    }
}
