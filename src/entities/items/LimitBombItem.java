package entities.items;

import graphics.Sprite;

public class LimitBombItem extends Items {
    public LimitBombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.actualSprite = Sprite.powerup_bombs;
        effect = Effect.LIMIT_BOMB;
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
