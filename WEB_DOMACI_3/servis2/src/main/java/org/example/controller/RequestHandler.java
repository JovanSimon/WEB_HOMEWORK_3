package org.example.controller;


import org.example.HttpMethod;
import org.example.Request;
import org.example.response.Response;

public class RequestHandler {
    public Response handle(Request request) throws Exception {
        if (request.getPath().equals("/get-quote") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return (new QuoteController(request)).doGet();
        }

        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
