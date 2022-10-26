package entities.items;

import graphics.Sprite;

public class ThroughWallItem extends Items {
    public ThroughWallItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.actualSprite = Sprite.powerup_wallpass;
        effect = Effect.WALL_PASS;
    }

    @Override
    protected void setPowerUp() {
        bomber.setWall_pass(active);
    }
}