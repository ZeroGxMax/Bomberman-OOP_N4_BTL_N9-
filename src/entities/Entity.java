package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import support.Unit;
import graphics.Sprite;
import map.Map;
import constants.Constants;

public abstract class Entity {
    protected static Map gameMap;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    // Tọa độ ô trong board
    protected int xUnit;
    protected int yUnit;
    protected Sprite sprite;
    protected Image img;
    protected boolean canBlock = false;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Sprite sprite) {
        this.xUnit = (int) xUnit;
        this.yUnit = (int) yUnit;
        this.x = Unit.unitToCoordinate(xUnit);
        this.y = Unit.unitToCoordinate(yUnit);
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean isCollision(Entity entity) {
        if (entity == null) {
            return false;
        } else {
            return this.getBoundary().intersects(entity.getBoundary());
        }
    }

    public abstract void update();

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}


