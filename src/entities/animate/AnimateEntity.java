package entities.animate;

import entities.Entity;

import graphics.Sprite;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.font.ImageGraphicAttribute;

public abstract class AnimateEntity extends Entity {
    protected int animate = 0;
    protected int MAX_ANIMATE = 1000;

    public AnimateEntity(double x, double y, Sprite sprite) {
        super(x, y, sprite);
    }

    protected void goAnimate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }
}
