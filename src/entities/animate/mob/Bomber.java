package entities.animate.mob;

import constants.Constants;
import constants.Constants.DIRECTION;
import graphics.Sprite;

public class Bomber extends Mob {

    private long timeMoving = System.currentTimeMillis();
    private long deltaTime;

    public Bomber(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void move(double xa, double ya) {
        x += xa * graphics.Sprite.SCALED_SIZE;
        y += ya * graphics.Sprite.SCALED_SIZE;
    }

    public void setDirection() {
        if (Constants.input1.isEmpty()) {
            direction = DIRECTION.NONE;
            return;
        }
        Constants.KEYBOARD temp = Constants.input1.pop();
        switch (temp) {
            case UP:
                direction = DIRECTION.UP;
                break;
            case DOWN:
                direction = DIRECTION.DOWN;
                break;
            case LEFT:
                direction = DIRECTION.LEFT;
                break;
            case RIGHT:
                direction = DIRECTION.RIGHT;
                break;
            default:
                direction = DIRECTION.NONE;
                break;
        }
    }

    @Override
    protected void calculateMove() {
        if (direction == DIRECTION.NONE)
            return;
        double xa = 0;
        double ya = 0;
        if (direction == DIRECTION.UP)
            ya -= 1;
        if (direction == DIRECTION.DOWN)
            ya += 1;
        if (direction == DIRECTION.RIGHT)
            xa += 1;
        if (direction == DIRECTION.LEFT)
            xa -= 1;
        move(xa, ya);
    }

    @Override
    public void update() {
        deltaTime = System.currentTimeMillis() - timeMoving;
        timeMoving += deltaTime;
        setDirection();
        goAnimate();
        calculateMove();
        deltaTime = 0;
    }
}
