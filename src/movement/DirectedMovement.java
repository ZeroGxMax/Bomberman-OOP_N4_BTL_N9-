package movement;

import constants.Constants.DIRECTION;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Enemy;
import map.Map;

public class DirectedMovement extends Movement {

    public DirectedMovement() {}

    public DirectedMovement(Bomber bomber, Enemy enemy, Map gameMap) {
        this.bomber = bomber;
        this.enemy = enemy;
        this.gameMap = gameMap;
    }

    public DIRECTION calculateDirection() {
        if (bomber == null || !canMoveAhead(enemy.getDirection())) {
            return DIRECTION.NONE;
        }

        int vertical = random.nextInt(2);

        if(vertical == 1) {
            DIRECTION v = calculateRowDirection();
            if (v != DIRECTION.NONE) {
                return v;
            } else {
                return calculateColDirection();
            }
        } else {
            DIRECTION h = calculateColDirection();

            if (h != DIRECTION.NONE) {
                return h;
            } else {
                return calculateRowDirection();
            }
        }
    }

    protected DIRECTION calculateColDirection() {
        if (gameMap == null) {
            return DIRECTION.NONE;
        }
        if(bomber.getxUnit() < enemy.getxUnit()
                && canMoveAhead(DIRECTION.LEFT)) {
            return DIRECTION.LEFT;
        } else if (bomber.getxUnit() > enemy.getxUnit()
                && canMoveAhead(DIRECTION.RIGHT)) {
            return DIRECTION.RIGHT;
        } else {
            return calculateRowDirection();
        }
    }

    protected DIRECTION calculateRowDirection() {
        if (gameMap == null) {
            return DIRECTION.NONE;
        }
        if (bomber.getyUnit() < enemy.getyUnit()
                && canMoveAhead(DIRECTION.UP)) {
            return DIRECTION.UP;
        } else if (bomber.getyUnit() > enemy.getyUnit()
                && canMoveAhead(DIRECTION.DOWN)) {
            return DIRECTION.DOWN;
        } else {
            if (bomber.getxUnit() < enemy.getxUnit()) {
                return DIRECTION.LEFT;
            } else {
                return DIRECTION.RIGHT;
            }
        }
    }
}
