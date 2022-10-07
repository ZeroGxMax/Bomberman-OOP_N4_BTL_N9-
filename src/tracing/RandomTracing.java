package tracing;

import constants.Constants.DIRECTION;

import java.util.Random;

public class RandomTracing extends Tracing {
<<<<<<< HEAD
    public static final int TIME_EACH_DIRECTION_MAX = 100;
    public int timeEachDirection = 95; // Some overhead before first moving
=======
    public static final int TIME_EACH_DIRECTION_MAX = 10;
    public static Random random = new Random();
    public int timeEachDirection = random.nextInt(10); // Some overhead before first moving
>>>>>>> main

    @Override
    public DIRECTION calculateDirection() {
        int randomNumber = random.nextInt() % 4;
        switch (randomNumber) {
            case 0:
                return DIRECTION.UP;
            case 1:
                return DIRECTION.RIGHT;
            case 2:
                return DIRECTION.DOWN;
            case 3:
                return DIRECTION.LEFT;
            default:
                return DIRECTION.NONE;
        }
    }

    /**
     * Enemy will change to adjacent side instead of random.
     */
    public DIRECTION toAdjacentDirection(DIRECTION direction) {
        int randomNumber = random.nextInt() % 2;
        if (direction == DIRECTION.UP || direction == DIRECTION.DOWN) {
            switch (randomNumber) {
                case 0:
                    return DIRECTION.RIGHT;
                case 1:
                    return DIRECTION.LEFT;
                default:
                    return DIRECTION.NONE;
            }
<<<<<<< HEAD
        } else {
=======
        }
        if (direction == DIRECTION.LEFT || direction == DIRECTION.RIGHT) {
>>>>>>> main
            switch (randomNumber) {
                case 0:
                    return DIRECTION.UP;
                case 1:
                    return DIRECTION.DOWN;
                default:
                    return DIRECTION.NONE;
            }
        }
<<<<<<< HEAD
=======
        return DIRECTION.LEFT;
>>>>>>> main
    }
}
