package tracing;

import constants.Constants.DIRECTION;

import java.util.Random;

public class RandomTracing extends Tracing {
    public static final int TIME_EACH_DIRECTION_MAX = 100;
    protected static Random random = new Random();
    public int timeEachDirection = 95; // Some overhead before first moving

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
