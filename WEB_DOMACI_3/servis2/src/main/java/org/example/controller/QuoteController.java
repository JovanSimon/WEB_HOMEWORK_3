package org.example.controller;


import org.example.Request;
import org.example.response.QuoteResponse;
import org.example.response.Response;

public class QuoteController extends Controller {
    public QuoteController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        String json = "{\"quote\":\"" + request.getQuoteOfTheDay() + "\"}";
        return new QuoteResponse(json);
    }

}
