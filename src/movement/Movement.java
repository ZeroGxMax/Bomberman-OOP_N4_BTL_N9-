package movement;

import constants.Constants.DIRECTION;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Enemy;
import map.Map;

import java.util.Random;

public abstract class Movement {
    protected Bomber bomber;
    protected Enemy enemy;
    protected Map gameMap;

    public int getDistance(Enemy enemy, Bomber player) {
        return (int) Math.sqrt(
                (enemy.getxUnit() - player.getxUnit())
                        * (enemy.getxUnit() - player.getxUnit())
                        + (enemy.getyUnit() - player.getyUnit())
                        * (enemy.getyUnit() - player.getyUnit()));
    }

    public boolean canMoveAhead(DIRECTION direction) {
        if (gameMap == null) {
            return false;
        }
        if (direction == DIRECTION.UP
                && !enemy.isCanStepOn(enemy.getxUnit(), enemy.getyUnit() - 1)) {
            return false;
        }
        if (direction == DIRECTION.RIGHT
                && !enemy.isCanStepOn(enemy.getxUnit() + 1, enemy.getyUnit())) {
            return false;
        }
        if (direction == DIRECTION.DOWN
                && !enemy.isCanStepOn(enemy.getxUnit(), enemy.getyUnit() + 1)) {
            return false;
        }
        if (direction == DIRECTION.LEFT
                && !enemy.isCanStepOn(enemy.getxUnit() - 1, enemy.getyUnit())) {
            return false;
        }
        return true;
    }

    public abstract DIRECTION calculateDirection();

    public Random random = new Random();

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public Bomber getBomber() {
        return bomber;
    }

    public Map getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
