package tracing;

import constants.Constants.DIRECTION;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Enemy;

import java.util.Random;

public abstract class Tracing {
    protected Bomber bomber;
    protected Enemy enemy;

    public Random random = new Random();

    public abstract DIRECTION calculateDirection();

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public Bomber getBomber() {
        return bomber;
    }
}
