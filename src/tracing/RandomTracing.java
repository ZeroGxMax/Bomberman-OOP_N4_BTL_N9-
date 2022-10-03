package tracing;

import constants.Constants.DIRECTION;

import java.util.Random;

public class RandomTracing extends Tracing {
    public static final int TIME_EACH_DIRECTION_MAX = 100;
    protected static Random random = new Random();
<<<<<<< HEAD
    public int timeEachDirection = 95; // Some overhead before first moving
=======
    public int timeEachDirection = 0;
>>>>>>> 5bdb1658fe5352bf649d393722be7c40fbc391b5

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
}
