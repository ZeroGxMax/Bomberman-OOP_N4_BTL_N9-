package entities.still.destroyable;

import entities.still.StillObject;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Brick extends StillObject {
    protected int animate = 0;
    protected int MAX_ANIMATE = 1000;

    int destroyTime = 100;
    int count = 100;

    protected void goAnimate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        canBlock = true;
    }

    @Override
    public void update() {

        if (count == -1) {
            return;
        }

        if (destroy == true) {
            if (count == 0) {
                gameMap._map[yUnit][xUnit] = 1;
                sprite = Sprite.grass;
            } else if (count < destroyTime / 3) {
                sprite = Sprite.brick_exploded[2];
            } else if (count < destroyTime * 2 / 3) {
                sprite = Sprite.brick_exploded[1];
            } else {
                sprite = Sprite.brick_exploded[0];
            }
            img = sprite.getFxImage();
            count--;
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    
}
