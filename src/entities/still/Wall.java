package entities.still;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Wall extends StillObject {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
    }

    @Override
    public void update() {

    }
}

