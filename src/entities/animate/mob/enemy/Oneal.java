package entities.animate.mob.enemy;

import constants.Constants;
import constants.Constants.DIRECTION;
import graphics.Sprite;
import map.Map;
import support.Probability;
import tracing.DirectedTracing;

public class Oneal extends Enemy {
    public DirectedTracing tracing = new DirectedTracing(this);

    public Oneal(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        tracing.setBomber(gameMap.getBomber());
        deadSprites.add(Sprite.oneal_dead);
    }

    protected void changeVelocity() {
        int randomNumber = tracing.random.nextInt() % 3;
        switch (randomNumber) {
            case 0:
                velocity = 0.5;
                break;
            case 1:
                velocity = 1;
                break;
            case 2:
                velocity = 1.5;
                break;
        }
    }

    /**
     * Dựa vào tracing để xác định ví trí di chuyển
     * Dựa theo direction để thay đổi tọa độ
     */
    @Override
    protected void calculateMove() {
        tracing.setBomber(gameMap.getBomber());
        if (!moving) {
            direction = DIRECTION.NONE;
        }
        // Call parent's method
        super.calculateMove();
    }

    @Override
    public void setDirection() {
        tracing.setBomber(gameMap.getBomber());
        if (!isCanChangeDirection()) {
            return;
        }

        int randomNumber = tracing.random.nextInt();
        if (Probability.isSometimes()) {
            changeVelocity();
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

    public void chooseSprite() {
        if (destroyed && timeAfter == -1) {
            return;
        }
        if (destroyed) {
            if (timeAfter == 0) {
                gameMap.animateEntities.remove(gameMap.getObjectAt(xUnit, yUnit));
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
                sprite = Sprite.movingSprite(Sprite.oneal_right, animate, 60);
                img = sprite.getFxImage();
                break;
            case DOWN:
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.oneal_left, animate, 60);
                img = sprite.getFxImage();
                break;
            case NONE:
                break;
        }
    }
}
