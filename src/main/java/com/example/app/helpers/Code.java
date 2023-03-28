package com.example.app.helpers;

import java.util.Random;

public class Code {

    public static Integer generateCode() {
        Random random = new Random();
        int bound = 123;
        return bound * random.nextInt(bound);
    }
}
