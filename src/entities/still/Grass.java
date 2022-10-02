package entities.still;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends Entity {

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

