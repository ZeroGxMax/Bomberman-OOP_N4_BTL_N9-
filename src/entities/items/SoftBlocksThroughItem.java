package entities.items;

import graphics.Sprite;

public class SoftBlocksThroughItem extends Items {
    public SoftBlocksThroughItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.actualSprite = Sprite.powerup_wallpass;
        effect = Effect.SOFT_BLOCK_PASS;
    }

    @Override
    protected void setPowerUp() {
        bomber.setWall_pass(active);
    }
}