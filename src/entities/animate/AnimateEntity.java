package entities.animate;

import abstractClasses.Entity;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.font.ImageGraphicAttribute;

public abstract class AnimateEntity extends Entity {
    public AnimateEntity(int x, int y, Image img) {
        super(x, y, img);
    }
}
