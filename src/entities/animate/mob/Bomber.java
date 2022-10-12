package entities.animate.mob;

import java.util.ArrayList;
import java.util.List;

import constants.Constants.DIRECTION;
import constants.Constants.KEYBOARD;
import entities.animate.bomb.Bomb;
import graphics.Sprite;
import input.KeyBoardInput;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

public class Bomber extends Mob {
    private int Max_Bombs = 1;
    private List<Bomb> b = new ArrayList<>();
    private int time_between_bomb = 10;

    public int getMax_Bombs() {
        return Max_Bombs;
    }

    public void setMax_Bombs(int max_Bomb) {
        Max_Bombs = max_Bomb;
        System.out.println("bombs set to " + getMax_Bombs());
    }

    public Bomber() {
    }

    public Bomber(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        velocity = 1.75;
        isBomber = true;
        velocity = 1.75;
    }

    @Override
    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
        b = gameMap.bombList;
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

    private void setActive() {
        time_between_bomb--;
<<<<<<< Updated upstream
        if (KeyBoardInput.getInput2() == KEYBOARD.ENTER) {
=======
        if (KeyBoardInput.getInput() == KEYBOARD.ENTER) {
>>>>>>> Stashed changes
            if (time_between_bomb <= 0 && b.size() < Max_Bombs)
                makeBomb();
        }
        setDirection();
    }

    public void setDirection(KEYBOARD key) {
        moving = true;
        switch (key) {
            case UP:
                direction = DIRECTION.UP;
                if (Map.isCanStepOn(xUnit, yUnit - 1))
                    yUnit--;
                else
                    moving = false;
                break;
            case DOWN:
                direction = DIRECTION.DOWN;
                if (Map.isCanStepOn(xUnit, yUnit + 1)) {
                    yUnit++;
                } else {
                    moving = false;
                }
                break;
            case LEFT:
                direction = DIRECTION.LEFT;
                if (Map.isCanStepOn(xUnit - 1, yUnit)) {
                    xUnit--;
                } else {
                    moving = false;
                }
                break;
            case RIGHT:
                direction = DIRECTION.RIGHT;
                if (Map.isCanStepOn(xUnit + 1, yUnit)) {
                    xUnit++;
                } else {
                    moving = false;
                }
                break;
            default:
                direction = DIRECTION.NONE;
                moving = false;
                break;
        }

    }

    @Override
    public void setDirection() {
        if (!isCanChangeDirection()) {
            return;
        }
        setDirection(KeyBoardInput.getInput());
    }

    @Override
    protected void calculateMove() {
        if (!moving) // Nếu không di chuyển -> thoát hàm
            return;
        double xa = 0;
        double ya = 0;
        if (direction == DIRECTION.UP)
            ya -= velocity;
        if (direction == DIRECTION.DOWN)
            ya += velocity;
        if (direction == DIRECTION.RIGHT)
            xa += velocity;
        if (direction == DIRECTION.LEFT)
            xa -= velocity;
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
        Bomb temp = new Bomb(xUnit, yUnit);
        temp.setGameMap(gameMap);
        b.add(temp);
        time_between_bomb = 10;
    }

    @Override
    public void update() {
        setActive();
        calculateMove();
        goAnimate();
    }
}
