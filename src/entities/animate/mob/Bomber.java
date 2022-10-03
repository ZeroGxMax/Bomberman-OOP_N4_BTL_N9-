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

    /**
     * Lấy key input để set hướng cho nhân vật, cập nhật trạng thái di chuyển trong
     * biến moving.
     */
    @Override
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }
        // Lấy input
        Constants.KEYBOARD temp = KeyBoardInput.getInput();
        // Update direction and moving
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
        if (!moving) // Nếu không di chuyển -> thoát hàm
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
        // goAnimate();
        calculateMove();
    }
}