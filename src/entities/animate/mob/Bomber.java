package entities.animate.mob;

import constants.Constants;
import constants.Constants.DIRECTION;
import graphics.Sprite;
import input.KeyBoardInput;
import map.Map;

public class Bomber extends Mob {
    public Bomber(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void move(double xa, double ya) {
        x += xa;
        y += ya;
    }

    private boolean isCanChangeDirection() {
        if (Math.abs(xUnit * Sprite.SCALED_SIZE - x) > 1) {
            return false;
        }
        if (Math.abs(yUnit * Sprite.SCALED_SIZE - y) > 1) {
            return false;
        }
        x = xUnit * Sprite.SCALED_SIZE;
        y = yUnit * Sprite.SCALED_SIZE;
        return true;
    }

    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }
        Constants.KEYBOARD temp = KeyBoardInput.getInput();
        moving = true;
        switch (temp) {
            case UP:
                direction = DIRECTION.UP;
                if (Map.isCanStepOn(xUnit, yUnit - 1))
                    yUnit--;
                else
                    moving = false;
                break;
            case DOWN:
                direction = DIRECTION.DOWN;
                if (Map.isCanStepOn(xUnit, yUnit + 1))
                    yUnit++;
                else
                    moving = false;
                break;
            case LEFT:
                direction = DIRECTION.LEFT;
                if (Map.isCanStepOn(xUnit - 1, yUnit))
                    xUnit--;
                else
                    moving = false;
                break;
            case RIGHT:
                direction = DIRECTION.RIGHT;
                if (Map.isCanStepOn(xUnit + 1, yUnit))
                    xUnit++;
                else
                    moving = false;
                break;
            default:
                direction = DIRECTION.NONE;
                moving = false;
                break;
        }
    }

    @Override
    protected void calculateMove() {
        if (!moving)
            return;
        double xa = 0;
        double ya = 0;
        if (direction == DIRECTION.UP)
            ya -= 1.5;
        if (direction == DIRECTION.DOWN)
            ya += 1.5;
        if (direction == DIRECTION.RIGHT)
            xa += 1.5;
        if (direction == DIRECTION.LEFT)
            xa -= 1.5;
        move(xa, ya);
    }

    @Override
    public void update() {
        setDirection();
        goAnimate();
        calculateMove();
    }
}
