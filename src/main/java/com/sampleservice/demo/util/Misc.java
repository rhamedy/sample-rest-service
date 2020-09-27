package com.sampleservice.demo.util;

import org.springframework.util.Assert;

/**
 * The following methods are used to automatically generate unit test for.
 */
public class Misc {
    enum Color {
        RED,
        YELLOW,
        BLUE
    }

    public static int sum(int arg1, int arg2) {
        return arg1 + arg2;
    }

    public static double divide(int divide, int divideBy) {
        if (divideBy == 0) {
            throw new RuntimeException("This operation would result in division by zero error.");
        }

        return divide / divideBy;
    }

    public static boolean isColorSupported(Color color) {
        Assert.isTrue(color != null, "color cannot be null");

        switch (color) {
            case RED:
                /* fallthrough */
            case YELLOW:
            case BLUE: return true;
        }

        return false;
    }

    public static long calculateFactorial(int num) {
        if (num >= 1) {
            return num * calculateFactorial(num - 1);
        }
        else {
            return 1;
        }
    }

    public static boolean isPrime(int n, int i) {
        if (n <= 2) {
            return (n == 2) ? true : false;
        }
        if (n % i == 0) {
            return false;
        }
        if (i * i > n) {
            return true;
        }

        return isPrime(n, i + 1);
    }

    public static boolean isEven(int n) {
        if (n % 2 == 0) {
            return true;
        }

        return false;
    }
}
