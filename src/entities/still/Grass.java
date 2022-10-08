package entities.still;

import graphics.Sprite;

public class Grass extends StillObject {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public static boolean isGrass(Object obj) {
        return obj instanceof Grass;
    }

    @Override
    public void update() {
    }
}