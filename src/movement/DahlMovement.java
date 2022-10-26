package movement;

import constants.Constants.DIRECTION;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Enemy;
import map.Map;
import support.Probability;

public class DahlMovement extends Movement {

    private boolean horizonBound = false;

    private boolean verticalBound = false;

    public DahlMovement() {}

    public DahlMovement(Bomber bomber, Enemy enemy, Map gameMap) {
        this.bomber = bomber;
        this.enemy = enemy;
        this.gameMap = gameMap;
    }
    @Override
    public DIRECTION calculateDirection() {
        if (Probability.isUsually()) {
            return upOrDown();
        }
        return leftOrRight();
    }

    public DIRECTION leftOrRight() {
        if (enemy == null) {
            return DIRECTION.NONE;
        }
        if (enemy.getDirection() == DIRECTION.RIGHT
                && !canMoveAhead(DIRECTION.RIGHT)) {
            horizonBound = true;
        }
        if (enemy.getDirection() == DIRECTION.LEFT
                && !canMoveAhead(DIRECTION.LEFT)) {
            horizonBound = false;
        }
        if (!horizonBound) {
            return DIRECTION.RIGHT;
        } else {
            return DIRECTION.LEFT;
        }
    }

    public DIRECTION upOrDown() {
        if (enemy == null) {
            return DIRECTION.NONE;
        }
        if (enemy.getDirection() == DIRECTION.UP
                && !canMoveAhead(DIRECTION.UP)) {
            verticalBound = true;
        }
        if (enemy.getDirection() == DIRECTION.DOWN
                && !canMoveAhead(DIRECTION.DOWN)) {
            verticalBound = false;
        }
        if (!verticalBound) {
            return DIRECTION.UP;
        } else {
            return DIRECTION.DOWN;
        }
    }
}
