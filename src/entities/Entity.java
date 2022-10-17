package entities;

import graphics.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.Map;
import support.Unit;

public abstract class Entity {
    // Lưu ý: Phải setGameMap ở bên ngoài (không thể construct trực tiếp).
    protected Map gameMap = new Map();
    // Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    // Tọa độ ô trong board
    protected int xUnit;
    protected int yUnit;
    protected Sprite sprite;
    protected Image img;
    protected boolean canBlock = false;
    public boolean isBomber = false;
    protected boolean destroyed = false;
    protected int timeAfter;

    public Entity() {
    }

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Sprite sprite) {
        this.xUnit = (int) xUnit;
        this.yUnit = (int) yUnit;
        this.x = Unit.unitToCoordinate(xUnit);
        this.y = Unit.unitToCoordinate(yUnit);
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public int getTimeAfter() {
        return timeAfter;
    }

    public void setTimeAfter(int timeAfter) {
        this.timeAfter = timeAfter;
    }

    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getxUnit() {
        return xUnit;
    }

    public int getyUnit() {
        return yUnit;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
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

    public void init() {

    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
