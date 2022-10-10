package entities.animate.mob.enemy;

import graphics.Sprite;
import map.Map;
import support.Probability;
import tracing.RandomTracing;

public class Balloon extends Enemy {
    public RandomTracing tracing = new RandomTracing();

    public Balloon(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    /**
     * Dựa vào tracing để xác định ví trí di chuyển
     * Dựa theo direction để thay đổi tọa độ
     */
    @Override
    protected void calculateMove() {
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
        // Call parent's method
        super.calculateMove();
    }

    @Override
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }

        int randomNumber = tracing.random.nextInt();

        if (Probability.isSometimes()) {
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
    public void chooseSprite() {
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

