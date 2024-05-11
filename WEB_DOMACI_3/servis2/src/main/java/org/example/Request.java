package org.example;

import lombok.Getter;

@Getter
public class Request {
    private final HttpMethod httpMethod;

    private final String path;
    private final String quoteOfTheDay;

    public Request(HttpMethod httpMethod, String path, String quoteOfTheDay) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.quoteOfTheDay = quoteOfTheDay;
    }
}
