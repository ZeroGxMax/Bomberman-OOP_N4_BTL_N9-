package entities.animate.mob;

import constants.Constants;
import constants.Constants.DIRECTION;
import constants.Constants.KEYBOARD;
import entities.animate.bomb.Explosion;
import entities.still.Wall;
import graphics.Sprite;
import input.KeyBoardInput;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import media.SoundController;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Mob {
    private int Max_Bombs = 1;
    private List<Explosion> b = new ArrayList<>();
    private boolean wall_pass = false;
    private int time_between_bomb = 0;
    private int bomb_length = 1;

    public Bomber() {
    }

    public Bomber(double x, double y, Sprite sprite) {
        super(x, y, sprite);
        isBomber = true;
        velocity = 1.75;
        timeAfter = Constants.PLAYER_DEATH_TIME;
        deadSprites.add(Sprite.player_dead[0]);
        deadSprites.add(Sprite.player_dead[1]);
        deadSprites.add(Sprite.player_dead[2]);
    }

    public int getBomb_length() {
        return bomb_length;
    }

    public void setBomb_length(int bomb_length) {
        this.bomb_length = bomb_length;
    }

    public void setMax_Bombs(int max_Bomb) {
        Max_Bombs = max_Bomb;
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
        if (KeyBoardInput.getInput2() == KEYBOARD.ENTER) {
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
                if (isCanStepOn(xUnit, yUnit - 1))
                    yUnit--;
                else
                    moving = false;
                break;
            case DOWN:
                direction = DIRECTION.DOWN;
                if (isCanStepOn(xUnit, yUnit + 1)) {
                    yUnit++;
                } else {
                    moving = false;
                }
                break;
            case LEFT:
                direction = DIRECTION.LEFT;
                if (isCanStepOn(xUnit - 1, yUnit)) {
                    xUnit--;
                } else {
                    moving = false;
                }
                break;
            case RIGHT:
                direction = DIRECTION.RIGHT;
                if (isCanStepOn(xUnit + 1, yUnit)) {
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
        gc.drawImage(sprite.getFxImage(), x, y);
    }

    public void chooseSprite() {
        if (destroyed && timeAfter == -1) {
            return;
        }
        if (destroyed) {
            if (timeAfter == 0) {
                return;
            } else if (timeAfter < Constants.PLAYER_DEATH_TIME / 4) {
                sprite = deadSprites.get(2);
            } else if (timeAfter < Constants.PLAYER_DEATH_TIME / 2) {
                sprite = deadSprites.get(1);
            } else {
                sprite = deadSprites.get(0);
            }
            return;
        }

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
        Explosion temp = new Explosion(xUnit, yUnit, bomb_length);
        temp.setGameMap(gameMap);
        b.add(temp);
        time_between_bomb = 10;
    }

    @Override
    public void update() {
        if (destroyed && timeAfter == -1) {
            return;
        }
        if (destroyed) {
            SoundController.playSound(0);
            goAnimate();
            timeAfter--;
            return;
        }
        setActive();
        calculateMove();
        goAnimate();
    }

    public boolean isCanStepOn(int x, int y) {
        if (gameMap == null) {
            return false;
        }
        if (!wall_pass) {
            return super.isCanStepOn(x, y);
        } else {
            return !(gameMap.getObjectAt(x, y) instanceof Wall);
        }
    }

    public void init() {

    }

    public boolean isWall_pass() {
        return wall_pass;
    }

    public void setWall_pass(boolean wall_pass) {
        this.wall_pass = wall_pass;
    }
}
