package entities.items;

import graphics.Sprite;

public class SpeedupItem extends Items {
    public SpeedupItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public void addPowerup() {
        if (shown) {
            setBomber(gameMap.getBomber());
        }
        if (active) {
            bomber.setVelocity(bomber.getVelocity() + 0.5);
        }
    }

    @Override
    public void update() {

    }
}
