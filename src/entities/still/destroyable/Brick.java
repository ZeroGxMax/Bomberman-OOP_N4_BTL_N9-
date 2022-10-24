package entities.still.destroyable;

import constants.Constants;
import entities.still.StillObject;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

public class Brick extends StillObject {
    protected int animate = 0;
    protected int MAX_ANIMATE = 1000;

    int timeCount = Constants.BRICK_DESTROY_TIME;

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
        if (!destroyed || timeCount < 0) {
            return;
        }
        if (destroyed == true) {
            if (timeCount == 0) {
                sprite = Sprite.grass;
                Map._map[yUnit][xUnit] = 1;
            } else if (timeCount < Constants.BRICK_DESTROY_TIME/3) {
                sprite = Sprite.brick_exploded[2];
            } else if (timeCount < Constants.BRICK_DESTROY_TIME*2/3) {
                sprite = Sprite.brick_exploded[1];
            } else  {
                sprite = Sprite.brick_exploded[0];
            }
            timeCount--;
            img = sprite.getFxImage();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
