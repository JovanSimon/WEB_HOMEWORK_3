package org.example.http.controller;

import com.sun.tools.javac.Main;
import org.example.http.Request;
import org.example.http.Server;
import org.example.http.response.HtmlResponse;
import org.example.http.response.RedirectResponse;
import org.example.http.response.Response;

import java.util.Iterator;

public class QuoteController extends Controller{
    public QuoteController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Domaci 3</title>\n" +
                "<style>\n" +
                "    body {\n" +
                "      display: flex;\n" +
                "      flex-direction: column;\n" +
                "      align-items: center;\n" +
                "      min-height: 100vh;\n" +
                "      margin: 0;\n" +
                "      padding: 20px;\n" +
                "    }\n" +
                "    .form-container {\n" +
                "      text-align: center;\n" +
                "      margin-bottom: 20px;\n" +
                "    }\n" +
                "    input[type=\"text\"],\n" +
                "    textarea {\n" +
                "      width: 300px;\n" +
                "      padding: 10px;\n" +
                "      margin: 5px;\n" +
                "      border-radius: 5px;\n" +
                "      border: 1px solid #ccc;\n" +
                "      font-size: 16px;\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "    button {\n" +
                "      padding: 10px 20px;\n" +
                "      margin-top: 10px;\n" +
                "      font-size: 16px;\n" +
                "      border-radius: 5px;\n" +
                "      border: none;\n" +
                "      background-color: #007bff;\n" +
                "      color: #fff;\n" +
                "      cursor: pointer;\n" +
                "    }\n" +
                "    .saved-quotes-container {\n" +
                "      text-align: center;\n" +
                "      border: 1px solid #ccc;\n" +
                "      padding: 10px;\n" +
                "      width: 100%;\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "  </style>" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"form-container\">\n" +
                "  <form id=\"quoteForm\" method=\"POST\" action=\"save-quote\">\n" +
                "    <label for=\"author\">Author</label><br>\n" +
                "    <input type=\"text\" id=\"author\" name=\"author\"><br>\n" +
                "    <label for=\"quote\">Quote</label><br>\n" +
                "    <textarea id=\"quote\" name=\"quote\" rows=\"4\"></textarea><br>\n" +
                "    <button type=\"submit\">Save Quote</button>\n" +
                "  </form>\n" +
                "</div>\n";

        html += "<div class=\"saved-quotes-container\">\n" +
                "  <h2>Quote of the day:</h2>\n" +
                "  <p>" + Server.quoteOfTheDay + "</p>" +
                "</div>";

        if(!Server.listOfQuotes.isEmpty()){
            html += "<div class=\"saved-quotes-container\">\n" +
                    "  <h2>Saved quotes</h2>";
            Iterator<String> iterator = Server.listOfQuotes.iterator();
            while (iterator.hasNext()){
                String item = iterator.next();
                html += "<p>" + item + "</p>";
            }
            html += "</div>";
        }

                html += "</body>\n" +
                "</html>\n";

        return new HtmlResponse(html);
    }

    @Override
    public Response doPost() {
        Server.listOfQuotes.add(request.getBody());
        return new RedirectResponse("/quotes");
    }
}
