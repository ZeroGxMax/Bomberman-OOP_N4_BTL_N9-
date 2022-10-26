package movement;

import constants.Constants;

import static constants.Constants.DIRECTION.UP;

public class NearMovement extends Movement {
    public NearMovement() {
    }

    @Override
    public Constants.DIRECTION calculateDirection() {
        if (gameMap == null) {
            return UP;
        }

        if (getDistance(enemy, bomber) < 10 && canMoveAhead(enemy.getDirection())) {
            return new DirectedMovement(bomber, enemy, gameMap).calculateDirection();
        } else {
            return new RandomMovement(bomber, enemy, gameMap).calculateDirection();
        }
    }
}
