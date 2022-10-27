package entities.animate.mob.enemy;

import constants.Constants;
import entities.animate.mob.Bomber;
import entities.animate.mob.Mob;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Enemy extends Mob {
    protected Bomber bomber;

    public Enemy(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        velocity = 0.5;
        deadSprites.add(Sprite.mob_dead[0]);
        deadSprites.add(Sprite.mob_dead[1]);
        deadSprites.add(Sprite.mob_dead[2]);
        timeAfter = Constants.ENEMY_DEATH_TIME;
    }

    /**
     * Dựa vào tracing để xác định ví trí di chuyển
     * Dựa theo direction để thay đổi tọa độ
     */
    @Override
    public void calculateMove() {
        double xa = 0;
        double ya = 0;

        if (direction == Constants.DIRECTION.UP) ya -= velocity;
        if (direction == Constants.DIRECTION.DOWN) ya += velocity;
        if (direction == Constants.DIRECTION.RIGHT) xa += velocity;
        if (direction == Constants.DIRECTION.LEFT) xa -= velocity;

        move(xa, ya);
    }

    public void move(double xa, double ya) {
        x += xa;
        y += ya;
    }

    @Override
    public void update() {
        init();
        if (destroyed && timeAfter == -1) {
            return;
        }
        if (destroyed) {
            goAnimate();
            timeAfter--;
            return;
        }
        setDirection();
        goAnimate();
        calculateMove();
        kill();
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(sprite.getFxImage(), x, y);
    }

    public void kill() {
        if (bomber.getxUnit() == this.xUnit
                && bomber.getyUnit() == this.yUnit) {
            bomber.setDestroyed(true);
        }
    }

    public void init() {
        if (bomber == null) {
            this.bomber = gameMap.getBomber();
        }
    }

    public abstract void chooseSprite();

    public abstract void setDirection();
}
