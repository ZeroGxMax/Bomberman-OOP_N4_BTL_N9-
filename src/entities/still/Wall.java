package entities.still;

import graphics.Sprite;

public class Wall extends StillObject {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {

    }

    /**
     * This don't need to be initialized
     */
    public void init() {

    }
}

