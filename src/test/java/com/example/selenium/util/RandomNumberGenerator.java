package com.example.selenium.util;

import java.util.Random;

public class RandomNumberGenerator {

    public static int generateNumberInRange(int min, int max) {
        Random rn = new Random();
        int range = max - min + 1;
        return rn.nextInt(range) + min;
    }

}
