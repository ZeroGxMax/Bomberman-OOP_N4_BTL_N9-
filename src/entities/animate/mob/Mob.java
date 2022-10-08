package entities.animate.mob;

import graphics.Sprite;
import entities.still.Grass;
import entities.still.StillObject;
import entities.animate.AnimateEntity;
import constants.Constants.DIRECTION;
import javafx.util.Pair;

import java.util.ArrayList;
import map.Map;

public abstract class Mob extends AnimateEntity {
    protected DIRECTION direction = DIRECTION.NONE;
    protected boolean moving = false;
    protected double velocity;

    public Mob() {}

    public Mob(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean isMoving() {
        return moving;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    /**
     * Kiểm tra xem có thể chuyền hướng không. Nhân vật chỉ có thể chuyển hướng khi
     * đã đi đến đúng ô.
     *
     * @return
     */


    protected boolean isCanChangeDirection() {
        // Kiểm tra bomber đã đúng ô chưa. Nếu cách 1 pixel coi như đã đúng vị trí (giá
        // trị 1 có thể thay đổi cho phù hợp).
        if (Math.abs(xUnit * Sprite.SCALED_SIZE - x) > 1.75) {
            return false;
        }
        if (Math.abs(yUnit * Sprite.SCALED_SIZE - y) > 1.75) {
            return false;
        }
        // Cài đặt lại vị trí nhân vật
        x = xUnit * Sprite.SCALED_SIZE;
        y = yUnit * Sprite.SCALED_SIZE;
        return true;
    }

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    public abstract void setDirection();

//    protected abstract void canMove(double x, double y);
}
