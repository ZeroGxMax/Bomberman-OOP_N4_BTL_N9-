package movement;

import constants.Constants.DIRECTION;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Enemy;
import map.Map;
import support.Probability;

import java.util.ArrayList;
import java.util.Random;

import static constants.Constants.DIRECTION.*;

public class RandomMovement extends Movement {
    public static final int TIME_EACH_DIRECTION_MAX = 100;
    public int timeEachDirection = 95; // Some overhead before first moving

    public RandomMovement() {}

    public RandomMovement(Bomber bomber, Enemy enemy, Map gameMap) {
        this.bomber = bomber;
        this.enemy = enemy;
        this.gameMap = gameMap;
    }

    @Override
    public DIRECTION calculateDirection() {
        if (gameMap == null) {
            return UP;
        }
        // Map bi nguoc
        ArrayList<DIRECTION> availableDirection = new ArrayList<>();
        if (gameMap.isCanStepOn(enemy.getxUnit(), enemy.getyUnit() - 1)) {
            availableDirection.add(UP);
        }
        if (gameMap.isCanStepOn(enemy.getxUnit() + 1, enemy.getyUnit())) {
            availableDirection.add(RIGHT);
        }
        if (gameMap.isCanStepOn(enemy.getxUnit(), enemy.getyUnit() + 1)) {
            availableDirection.add(DOWN);
        }
        if (gameMap.isCanStepOn(enemy.getxUnit() - 1, enemy.getyUnit())) {
            availableDirection.add(LEFT);
        }
        if (Probability.isUsually()) {
            int rand = new Random().nextInt(availableDirection.size());
            return availableDirection.get(rand);
        } else {
            return enemy.getDirection();
        }
    }
}
