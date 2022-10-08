package entities.still;

import graphics.Sprite;

public class Wall extends StillObject {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
    }

    @Override
    public void update() {

    }
}

