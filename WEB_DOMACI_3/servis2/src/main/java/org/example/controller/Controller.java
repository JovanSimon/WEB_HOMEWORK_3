package org.example.controller;


import org.example.Request;
import org.example.response.Response;

public abstract class Controller {
    protected Request request;

    public Controller(Request request) {
        this.request = request;
    }

    public abstract Response doGet();

}
