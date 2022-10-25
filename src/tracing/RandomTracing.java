package tracing;

import constants.Constants.DIRECTION;

public class RandomTracing extends Tracing {
    public static final int TIME_EACH_DIRECTION_MAX = 100;
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
        } else {
            switch (randomNumber) {
                case 0:
                    return DIRECTION.UP;
                case 1:
                    return DIRECTION.DOWN;
                default:
                    return DIRECTION.NONE;
            }
        }
    }
}
