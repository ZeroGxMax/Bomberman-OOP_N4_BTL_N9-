package entities.animate;

import entities.Entity;
import graphics.Sprite;

public abstract class AnimateEntity extends Entity {
    protected int animate = 0;
    protected int MAX_ANIMATE = 1000;

    public AnimateEntity() {
    }

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
