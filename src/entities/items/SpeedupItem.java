package entities.items;

import graphics.Sprite;

public class SpeedupItem extends Items {
    public SpeedupItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_speed);
        effect = Effect.speed_up;
    }

    @Override
    protected void setPowerUp() {
        if (active) {
            bomber.setVelocity(bomber.getVelocity() + 0.5);
        } else {
            bomber.setVelocity(bomber.getVelocity() - 0.5);
        }
    }
}
