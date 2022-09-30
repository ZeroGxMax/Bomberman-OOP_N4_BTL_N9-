package entities.animate;

import abstractClasses.Entity;

import graphics.Sprite;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.font.ImageGraphicAttribute;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentSprite;

    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

//    public void updateAnimation(int time) {
//        this.sprite = Sprite.movingSprite(currentSprite, time);
//        this.img = this.sprite.getFxImage();
//    }
}
