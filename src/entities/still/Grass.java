package entities.still;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends StillObject {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = false;
    }

    @Override
    public void update() {
    }
}

