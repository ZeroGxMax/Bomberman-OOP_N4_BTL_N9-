package entities.animate.mob;

import graphics.Sprite;
import entities.animate.AnimateEntity;
import constants.Constants.DIRECTION;

public abstract class Mob extends AnimateEntity {
    protected DIRECTION direction = DIRECTION.LEFT;
    protected boolean moving = false;

    public Mob(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean isMoving() {
        return moving;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    protected abstract void calculateMove();

    protected abstract void move(int xa, int ya);
}
