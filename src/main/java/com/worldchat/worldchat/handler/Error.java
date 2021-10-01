package com.worldchat.worldchat.handler;

import org.springframework.http.HttpStatus;

public class Error {
    public String message;
    public HttpStatus statusText;
    public Integer status;

    public Error(HttpStatus statusText, String message) {
        this.statusText = statusText;
        this.message = message;
        this.status = this.statusText.value();
    }
}
