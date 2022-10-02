package entities.animate.mob;

import graphics.Sprite;
import entities.animate.AnimateEntity;
import constants.Constants.DIRECTION;

public abstract class Mob extends AnimateEntity {
    protected DIRECTION direction = DIRECTION.NONE;
    protected boolean moving = false;

    public Mob(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean isMoving() {
        return moving;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);
}
