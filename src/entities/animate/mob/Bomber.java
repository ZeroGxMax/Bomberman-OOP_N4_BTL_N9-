package entities.animate.mob;

import constants.Constants;
import constants.Constants.DIRECTION;
import constants.Constants.KEYBOARD;
import entities.animate.bomb.Bomb;
import graphics.Sprite;
import input.KeyBoardInput;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

public class Bomber extends Mob {
    private int countBomb = 0;
    private int Max_Bomb = 1;
    private Bomb b = new Bomb();

    public Bomber() {
    }

    public Bomber(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        velocity = 1.75;
        isBomber = true;
    }

    @Override
    public void move(double xa, double ya) {
        x += xa;
        y += ya;
    }

    /**
     * Lấy key input để set hướng cho nhân vật, cập nhật trạng thái di chuyển trong
     * biến moving.
     */

    @Override
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }
        // Lấy input
        Constants.KEYBOARD temp = KeyBoardInput.getInput();
        // Update direction and moving
        moving = true;
        switch (temp) {
            case UP:
                direction = DIRECTION.UP;
                if (Map.isCanStepOn(xUnit, yUnit - 1))
                    yUnit--;
                else
                    moving = false;
                break;
            case DOWN:
                direction = DIRECTION.DOWN;
                if (Map.isCanStepOn(xUnit, yUnit + 1))
                    yUnit++;
                else
                    moving = false;
                break;
            case LEFT:
                direction = DIRECTION.LEFT;
                if (Map.isCanStepOn(xUnit - 1, yUnit))
                    xUnit--;
                else
                    moving = false;
                break;
            case RIGHT:
                direction = DIRECTION.RIGHT;
                if (Map.isCanStepOn(xUnit + 1, yUnit))
                    xUnit++;
                else
                    moving = false;
                break;
            default:
                direction = DIRECTION.NONE;
                moving = false;
                break;
        }
    }

    @Override
    protected void calculateMove() {
        if (!moving) // Nếu không di chuyển -> thoát hàm
            return;
        double xa = 0;
        double ya = 0;
        if (direction == DIRECTION.UP)
            ya -= 1.75;
        if (direction == DIRECTION.DOWN)
            ya += 1.75;
        if (direction == DIRECTION.RIGHT)
            xa += 1.75;
        if (direction == DIRECTION.LEFT)
            xa -= 1.75;
        move(xa, ya);
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(img, x, y);
    }

    public void chooseSprite() {
        switch (direction) {
            case UP:
                sprite = Sprite.movingSprite(Sprite.player_up, animate, 30);
                break;
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.player_right, animate, 30);
                break;
            case DOWN:
                sprite = Sprite.movingSprite(Sprite.player_down, animate, 30);
                break;
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.player_left, animate, 30);
                break;
            case NONE:
                sprite = Sprite.player_down[0];
                break;
        }
        img = sprite.getFxImage();
    }

    private void makeBomb() {
        b = new Bomb(xUnit, yUnit);
        gameMap.bomb.add(b);
        countBomb++;
    }

    @Override
    public void update() {
        if (KeyBoardInput.getInput() == KEYBOARD.ENTER && countBomb < Max_Bomb) {
            makeBomb();
        }
        setDirection();
        calculateMove();
        goAnimate();
        if (b.isDestroyed()) {
            countBomb = 0;
        }
    }
}
