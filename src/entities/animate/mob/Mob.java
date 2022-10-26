package entities.animate.mob;

import constants.Constants.DIRECTION;
import entities.animate.AnimateEntity;
import graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends AnimateEntity {
    protected List<Sprite> deadSprites = new ArrayList<>();
    protected DIRECTION direction = DIRECTION.NONE;
    protected boolean moving = false;
    protected double velocity = 1;

    public Mob() {
    }

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
        if (Math.abs(xUnit * Sprite.SCALED_SIZE - x) > velocity) {
            return false;
        }
        if (Math.abs(yUnit * Sprite.SCALED_SIZE - y) > velocity) {
            return false;
        }
        // Cài đặt lại vị trí nhân vật
        x = xUnit * Sprite.SCALED_SIZE;
        y = yUnit * Sprite.SCALED_SIZE;
        moving = false;
        return true;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean isCanStepOn(int x, int y) {
        return gameMap.isCanStepOn(x, y);
    }

    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    public abstract void setDirection();

    // protected abstract void canMove(double x, double y);
}
