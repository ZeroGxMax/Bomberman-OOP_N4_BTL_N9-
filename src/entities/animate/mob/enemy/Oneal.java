package entities.animate.mob.enemy;

import entities.Entity;
import entities.animate.AnimateEntity;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void chooseSprite() {
        switch (direction) {
            case UP:
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.oneal_right[0], Sprite.oneal_right[1],
                        Sprite.oneal_right[2], animate, 60);
                img = sprite.getFxImage();
                break;
            case DOWN:
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.oneal_left[0], Sprite.oneal_left[1],
                        Sprite.oneal_left[2], animate, 60);
                img = sprite.getFxImage();
                break;
            case NONE:
                break;
        }
    }
}
