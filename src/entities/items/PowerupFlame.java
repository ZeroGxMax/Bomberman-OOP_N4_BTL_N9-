package entities.items;

import graphics.Sprite;

public class PowerupFlame extends Items {

    public PowerupFlame(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.actualSprite = Sprite.powerup_flames;
        effect = Effect.RANGE_BOMB;
    }

    @Override
    protected void setPowerUp() {
        if (active) {
            bomber.setBomb_length(bomber.getBomb_length() + 1);
        } else {
            bomber.setBomb_length(1);
        }
    }

}
