package entities.still.destroyable;

import entities.still.StillObject;
import graphics.Sprite;

public class Brick extends StillObject {
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
    }

    @Override
    public void update() {
    }
}
