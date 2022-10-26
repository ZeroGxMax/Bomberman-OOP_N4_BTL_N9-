package entities.animate.mob.enemy;

import constants.Constants;
import graphics.Sprite;
import map.Map;
import movement.RandomMovement;

public class Balloon extends Enemy {
    public RandomMovement tracing = new RandomMovement();

    public Balloon(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        deadSprites.add(Sprite.balloom_dead);
    }

    /**
     * Dựa vào tracing để xác định ví trí di chuyển
     * Dựa theo direction để thay đổi tọa độ
     */
    @Override
    protected void calculateMove() {
        if (tracing.getEnemy() == null) {
            tracing.setEnemy(this);
        }
        if (tracing.getGameMap() == null) {
            tracing.setGameMap(gameMap);
        }
        if (tracing.getBomber() == null) {
            tracing.setBomber(gameMap.getBomber());
        }
        if (!moving) {
            direction = tracing.calculateDirection();
        }
        // Call parent's method
        super.calculateMove();
    }

    @Override
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }
        direction = tracing.calculateDirection();

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
    public void chooseSprite() {
        if (destroyed && timeAfter == -1) {
            return;
        }
        if (destroyed) {
            if (timeAfter == 0) {
                return;
            } else if (timeAfter < Constants.ENEMY_DEATH_TIME/4) {
                sprite = deadSprites.get(2);
            } else if (timeAfter < Constants.ENEMY_DEATH_TIME/3) {
                sprite = deadSprites.get(1);
            } else if (timeAfter < Constants.ENEMY_DEATH_TIME/2) {
                sprite = deadSprites.get(0);
            } else {
                sprite = deadSprites.get(3);
            }
            return;
        }

        switch (direction) {
            case UP:
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.balloom_right, animate, 60);
                img = sprite.getFxImage();
                break;
            case DOWN:
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.balloom_left, animate, 60);
                img = sprite.getFxImage();
                break;
            case NONE:
                break;
        }
    }
}

