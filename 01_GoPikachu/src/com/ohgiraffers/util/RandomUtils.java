package com.ohgiraffers.util;

public class RandomUtils {

    public static int generateRandom(int bound) {
        return generateRandom(1, bound);
    }

    public static int generateRandom(int start, int bound) {
        return (int) (Math.random() * bound) + start;
    }

    public static boolean validate(int n, int bound) {
        return generateRandom(bound) == n;
    }

    public static boolean checkProbability(double percent) {
        return Math.random() <= percent;
    }
}
