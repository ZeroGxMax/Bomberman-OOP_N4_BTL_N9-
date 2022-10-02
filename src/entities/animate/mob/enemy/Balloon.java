package entities.animate.mob.enemy;

import entities.animate.AnimateEntity;
import entities.animate.mob.Mob;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void chooseSprite() {
        switch (direction) {
            case UP:
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.balloom_right[0], Sprite.balloom_right[1],
                        Sprite.balloom_right[2], animate, 60);
                img = sprite.getFxImage();
                break;
            case DOWN:
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.balloom_left[0], Sprite.balloom_left[1],
                        Sprite.balloom_left[2], animate, 60);
                img = sprite.getFxImage();
                break;
            case NONE:
                break;
        }
    }
}

