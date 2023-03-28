package com.example.app.helpers;

import java.util.UUID;

public class Token {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
