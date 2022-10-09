package support;

import java.util.Random;

public class Probability {
    public static Random random = new Random();

    public static boolean isUsually() {
        int randomNumber = random.nextInt();
        return randomNumber % 2 == 0;
    }

    public static boolean isOften() {
        int randomNumber = random.nextInt();
        return randomNumber % 3 == 0;
    }

    public static boolean isSometimes() {
        int randomNumber = random.nextInt();
        return randomNumber % 5 == 0;
    }

    public static boolean isRarely() {
        int randomNumber = random.nextInt();
        return randomNumber % 10 == 0;
    }
}
