package entities.still;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Brick extends StillObject {
    protected int animate = 0;
    protected int MAX_ANIMATE = 1000;

    protected void goAnimate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }

    private boolean destroy = false;

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
    }

    @Override
    public void update() {
    
        if (destroy == true) {
            goAnimate();
            sprite = Sprite.movingSprite(Sprite.brick_exploded, animate, 60);
            img = sprite.getFxImage();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
