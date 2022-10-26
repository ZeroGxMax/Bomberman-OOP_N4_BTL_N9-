package entities.animate.mob.enemy;

import constants.Constants;
import graphics.Sprite;
import map.Map;
import movement.NearMovement;
import movement.Movement;

public class Minvo extends Enemy {
    public Movement movement = new NearMovement();

    public Minvo(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        deadSprites.add(Sprite.minvo_dead);
        velocity = 1.5;
    }

    /**
     * Dựa vào tracing để xác định ví trí di chuyển
     * Dựa theo direction để thay đổi tọa độ
     */
    @Override
    protected void calculateMove() {
        if (movement.getEnemy() == null) {
            movement.setEnemy(this);
        }
        if (movement.getGameMap() == null) {
            movement.setGameMap(gameMap);
        }
        if (movement.getBomber() == null) {
            movement.setBomber(gameMap.getBomber());
        }
        if (!moving) {
            direction = movement.calculateDirection();
        }
        // Call parent's method
        super.calculateMove();
    }

    @Override
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }

        direction = movement.calculateDirection();

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
                sprite = Sprite.movingSprite(Sprite.minvo_right, animate, 60);
                img = sprite.getFxImage();
                break;
            case DOWN:
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.minvo_left, animate, 60);
                img = sprite.getFxImage();
                break;
            case NONE:
                break;
        }
    }

    @Override
    public void init() {
        super.init();
        if (movement.getGameMap() == null) {
            movement.setGameMap(gameMap);
        }
        if (movement.getBomber() == null) {
            movement.setBomber(gameMap.getBomber());
        }
        if (movement.getEnemy() == null) {
            movement.setEnemy(this);
        }
    }
}
