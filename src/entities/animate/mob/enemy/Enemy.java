package entities.animate.mob.enemy;

import constants.Constants;
import constants.Constants.DIRECTION;
import entities.Entity;
import input.KeyBoardInput;
import tracing.RandomTracing;
import entities.animate.mob.Mob;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import support.Unit;
import tracing.Tracing;

public abstract class Enemy extends Mob {

    public Enemy(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        velocity = 0.5;
    }

    /**
     * Dựa vào tracing để xác định ví trí di chuyển
     * Dựa theo direction để thay đổi tọa độ
     */
    @Override
    protected void calculateMove() {
        double xa = 0;
        double ya = 0;

<<<<<<< HEAD
        if (direction == Constants.DIRECTION.UP) ya -= velocity;
        if (direction == Constants.DIRECTION.DOWN) ya += velocity;
        if (direction == Constants.DIRECTION.RIGHT) xa += velocity;
        if (direction == Constants.DIRECTION.LEFT) xa -= velocity;
=======

        if (tracing.timeEachDirection >= tracing.TIME_EACH_DIRECTION_MAX
                && isCanChangeDirection()) {
            direction = tracing.calculateDirection();
            tracing.timeEachDirection = 0;
        } else {
            // If the enemy hit the wall, change it direction.
            if (!moving) {
                direction = tracing.calculateDirection();
            }
            tracing.timeEachDirection++;
        }

        if (direction == DIRECTION.UP) ya -= 0.5;
        if (direction == DIRECTION.DOWN) ya += 0.5;
        if (direction == DIRECTION.RIGHT) xa += 0.5;
        if (direction == DIRECTION.LEFT) xa -= 0.5;
>>>>>>> main

        move(xa, ya);
    }

    public void move(double xa, double ya) {
        x += xa;
        y += ya;
    }

    @Override
<<<<<<< HEAD
    public void update() {
        setDirection();
        goAnimate();
        calculateMove();
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(sprite.getFxImage(), x, y);
=======
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }

        int randomNumber = tracing.random.nextInt();

        if (randomNumber % 4 == 0) {
            direction = tracing.calculateDirection();
        }

        // Depend on direction determine if it can still move:
        moving = true;
        switch (direction) {
            case UP:
                if (Map.isCanStepOn(xUnit, yUnit - 1)) {
                    yUnit--;
                } else {
                    moving = false;
                }
                break;
            case DOWN:
                if (Map.isCanStepOn(xUnit, yUnit + 1)) {
                    yUnit++;
                } else {
                    moving = false;
                }
                break;
            case LEFT:
                if (Map.isCanStepOn(xUnit - 1, yUnit)) {
                    xUnit--;
                } else {
                    moving = false;
                }
                break;
            case RIGHT:
                if (Map.isCanStepOn(xUnit + 1, yUnit)) {
                    xUnit++;
                } else {
                    moving = false;
                }
                break;
            default:
                moving = false;
                break;
        }
    }

    @Override
    public void update() {
        setDirection();
        goAnimate();
        calculateMove();
>>>>>>> main
    }

    public abstract void chooseSprite();
}
