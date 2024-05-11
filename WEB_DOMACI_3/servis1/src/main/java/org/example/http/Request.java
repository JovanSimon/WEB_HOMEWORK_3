package org.example.http;

import lombok.Getter;

@Getter
public class Request {
    private final HttpMethod httpMethod;

    private final String path;
    private final String body;

    public Request(HttpMethod httpMethod, String path, String body) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.body = body;
    }
}
