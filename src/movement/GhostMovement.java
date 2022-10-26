package movement;

import constants.Constants.DIRECTION;

public class GhostMovement extends Movement {
    @Override
    public DIRECTION calculateDirection() {
        if (gameMap == null) {
            return DIRECTION.NONE;
        }
        if (getDistance(enemy, bomber) < 20) {
            return new DirectedMovement(bomber, enemy, gameMap).calculateDirection();
        } else {
            return new DahlMovement(bomber, enemy, gameMap).calculateDirection();
        }
    }
}
