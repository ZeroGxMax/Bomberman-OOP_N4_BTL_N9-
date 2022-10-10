package entities.still.destroyable;

<<<<<<< HEAD:src/entities/still/Brick.java
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
=======
import entities.still.StillObject;
import graphics.Sprite;
>>>>>>> Zero_branch_2:src/entities/still/destroyable/Brick.java

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
