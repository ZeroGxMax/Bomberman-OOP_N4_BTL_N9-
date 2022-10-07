package tracing;

import constants.Constants.DIRECTION;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Enemy;

public class DirectedTracing extends Tracing {
    Bomber bomber;
    Enemy enemy;

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public DirectedTracing(Enemy enemy) {
        this.enemy = enemy;
    }

    public DirectedTracing(Bomber bomber, Enemy enemy) {
        this.bomber = bomber;
        this.enemy = enemy;
    }

    public DIRECTION calculateDirection() {
        if (bomber == null) {
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
        if(bomber.getxUnit() < enemy.getxUnit()) {
            return DIRECTION.LEFT;
        } else if (bomber.getxUnit() > enemy.getxUnit()) {
            return DIRECTION.RIGHT;
        } else {
            return DIRECTION.NONE;
        }
    }

    protected DIRECTION calculateRowDirection() {
        if(bomber.getyUnit() < enemy.getyUnit()) {
            return DIRECTION.UP;
        } else if(bomber.getyUnit() > enemy.getyUnit()) {
            return DIRECTION.DOWN;
        } else {
            return DIRECTION.NONE;
        }
    }
}
