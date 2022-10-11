package entities.items;

import graphics.Sprite;

public class LimitBombItem extends Items {
    public LimitBombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.powerup_bombs);
        effect = Effect.limit_bomb;
    }

    @Override
    protected void setPowerUp() {
        if (active) {
            bomber.setMax_Bombs(2);
        } else {
            bomber.setMax_Bombs(1);
        }
    }
}
