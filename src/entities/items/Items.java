package entities.items;

import entities.animate.mob.Bomber;
import entities.still.StillObject;
import entities.still.Wall;
import entities.still.destroyable.Brick;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class Items extends StillObject {
    protected Bomber bomber = new Bomber();
    protected boolean shown = false;
    protected boolean active = false;// lưu trạng thái item tác dụng tới bomber(nếu có thì true)
    protected boolean end = false;
    protected Brick frontBrick;
    protected Sprite actualSprite;
    protected Effect effect;

    protected long time_start = 0;// thời gian bắt đầu (tính bằng mili s). Giúp tính thời gian đã qua
    protected long time_of_existence = Long.MAX_VALUE;// thời gian tác dụng của item.

    public enum Effect {
        speed_up,
        range_bomb,
        limit_bomb,
        none
    }

    public Items(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.brick);
        this.sprite = Sprite.brick;
    }

    public Effect getEffect() {
        return effect;
    }

    public Brick getFrontBrick() {
        return frontBrick;
    }

    public boolean isActive() {
        return active;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public void setFrontWall() {
        this.frontBrick = new Brick(xUnit, yUnit, Sprite.brick);
    }

    /**
     * Hàm này sẽ đc gọi khi bomber đi lên item.
     */
    public void start() {
        time_start = System.currentTimeMillis();
        time_of_existence = 10000; // 10000 (ms) = 10 (s)
        active = true;
        shown = false;
        setPowerUp();
    }

    @Override
    public void update() {
        if (end) {
            return;
        }
        if (destroyed && !shown && time_start == 0) {
            gameMap._map[yUnit][xUnit] = 1;
            shown = true;
            sprite = actualSprite;
        }
        // kiểm tra vị trí bomber so với item
        if (bomber.getxUnit() == xUnit && bomber.getyUnit() == yUnit && shown) {
            sprite = Sprite.grass;
            start();
        }
        if (time_start == 0) {// time_start == 0 khi chưa start()
            return;
        }
        // kiểm tra xem hết thời gian tác dụng của item chưa
        if (System.currentTimeMillis() - time_start > time_of_existence && active) {
            active = false;
            end = true;
            setPowerUp();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        img = sprite.getFxImage();
        super.render(gc);
    }

    protected abstract void setPowerUp();// tác dụng của item
}
