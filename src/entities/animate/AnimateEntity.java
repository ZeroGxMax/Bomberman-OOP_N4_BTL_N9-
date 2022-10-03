package entities.animate;

import entities.Entity;
import graphics.Sprite;
import javafx.util.Pair;

import java.util.ArrayList;

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

    public ArrayList<Pair<Integer, Integer>> getBorder() {
        int width = (int) sprite.getFxImage().getWidth();
        int height = (int) sprite.getFxImage().getHeight();
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        result.add(new Pair<>(xUnit, yUnit));
        result.add(new Pair<>(xUnit + height - 1, yUnit));
        result.add(new Pair<>(xUnit, yUnit + width - 1));
        result.add(new Pair<>(xUnit + height - 1, yUnit + width - 1));
        return result;
    }
}
