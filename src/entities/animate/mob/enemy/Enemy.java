package entities.animate.mob.enemy;

import constants.Constants.DIRECTION;
import constants.Constants;
import entities.animate.mob.Mob;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Enemy extends Mob {
    protected int finalAnimation = 30;

    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        goAnimate();
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(sprite.getFxImage(), x, y);
    }

    @Override
    protected void calculateMove() {
        int xa = 0;
        int ya = 0;
        if (direction == DIRECTION.UP) ya++;
        if (direction == DIRECTION.DOWN) ya--;
        if (direction == DIRECTION.RIGHT) xa++;
        if (direction == DIRECTION.LEFT) xa--;

        move(xa, ya);
    }

    public void move(int xa, int ya) {
        x += xa;
        y += ya;
    }

    public abstract void chooseSprite();
}
