package entities.items;

import entities.animate.mob.Bomber;
import entities.still.Brick;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

public abstract class Items extends Brick {
    protected Bomber bomber = new Bomber();
    protected boolean shown = false;
    protected boolean active = false;// lưu trạng thái item tác dụng tới bomber(nếu có thì true)
    protected boolean end = false;
    protected Brick frontBrick;
    protected Sprite actualSprite;
    protected Effect effect;

    protected long timeStart = 0;// thời gian bắt đầu (tính bằng mili s). Giúp tính thời gian đã qua
    protected long timeOfExistence = Long.MAX_VALUE;// thời gian tác dụng của item.

    public Items(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.brick);
        this.sprite = Sprite.brick;
        this.behindSprite = actualSprite;
    }

    /**
     * Hàm này sẽ đc gọi khi bomber đi lên item.
     */
    public void start() {
        timeStart = System.currentTimeMillis();
        timeOfExistence = Long.MAX_VALUE; // 10000 (ms) = 10 (s)
        active = true;
        shown = false;
        setPowerUp();
    }

    @Override
    public void update() {
        if (timeCount < 0) {
            if (end) {
                return;
            }
            if (destroyed && !shown && timeStart == 0) {
                Map.map[yUnit][xUnit] = 1;
                shown = true;
                sprite = actualSprite;
            }
            // kiểm tra vị trí bomber so với item
            if (bomber.getxUnit() == xUnit && bomber.getyUnit() == yUnit && shown) {
                sprite = Sprite.grass;
                start();
            }
            if (timeStart == 0) {// time_start == 0 khi chưa start()
                return;
            }
            // kiểm tra xem hết thời gian tác dụng của item chưa
            if (System.currentTimeMillis() - timeStart > timeOfExistence && active) {
                active = false;
                end = true;
                setPowerUp();
            }
            return;
        }
        super.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        img = sprite.getFxImage();
        super.render(gc);
    }

    protected abstract void setPowerUp();// tác dụng của item

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Brick getFrontBrick() {
        return frontBrick;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public void setFrontWall() {
        this.frontBrick = new Brick(xUnit, yUnit, Sprite.brick);
    }

    public enum Effect {
        SPEED_UP,
        RANGE_BOMB,
        LIMIT_BOMB,
        SOFT_BLOCK_PASS,
        NONE
    }
}
