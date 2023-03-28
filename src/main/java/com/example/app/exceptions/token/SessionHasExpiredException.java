package com.example.app.exceptions.token;

public class SessionHasExpiredException extends RuntimeException{
    public SessionHasExpiredException() {
        super("This session has expired");
    }
}
