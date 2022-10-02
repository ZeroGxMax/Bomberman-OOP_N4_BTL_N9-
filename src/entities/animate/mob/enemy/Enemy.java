package entities.animate.mob.enemy;

import constants.Constants.DIRECTION;
import tracing.RandomTracing;
import entities.animate.mob.Mob;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import tracing.Tracing;

public abstract class Enemy extends Mob {
    public RandomTracing tracing = new RandomTracing();

    public Enemy(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        goAnimate();
        calculateMove();
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(sprite.getFxImage(), x, y);
    }

    @Override
    protected void calculateMove() {
        double xa = 0;
        double ya = 0;

        if (tracing.timeEachDirection == tracing.TIME_EACH_DIRECTION_MAX) {
            direction = tracing.calculateDirection();
            tracing.timeEachDirection = 0;
        } else {
            tracing.timeEachDirection++;
        }

        if (direction == DIRECTION.UP) ya += 0.5;
        if (direction == DIRECTION.DOWN) ya -= 0.5;
        if (direction == DIRECTION.RIGHT) xa += 0.5;
        if (direction == DIRECTION.LEFT) xa -= 0.5;

        move(xa, ya);
    }

    public void move(double xa, double ya) {
        x += xa;
        y += ya;
    }

    public abstract void chooseSprite();
}
