package entities.items;

import graphics.Sprite;

public class SpeedupItem extends Items {
    public SpeedupItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.actualSprite = Sprite.powerup_speed;
        effect = Effect.SPEED_UP;
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
