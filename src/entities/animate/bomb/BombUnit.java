package entities.animate.bomb;

import constants.Constants.BOMB_UNITS;
import entities.animate.AnimateEntity;
import graphics.Sprite;

public class BombUnit extends AnimateEntity {
    protected Sprite[] sprites = new Sprite[3];

    private boolean destroy = false;
    private int countFrames = 0;

    public BombUnit(int x, int y, Sprite[] sprites) {
        super(x, y, sprites[0]);
        this.sprites = sprites;
    }

    public BombUnit(int x, int y, BOMB_UNITS type) {
        new BombUnit(x, y, chooseSprite(type));
    }

    public boolean isDestroy() {
        return destroy;
    }

    public static Sprite[] chooseSprite(BOMB_UNITS unit) {
        switch (unit) {
            case BOMB:
                return Sprite.bomb;
            case EXPLOSION_HORIZONTAL:
                return Sprite.explosion_horizontal;
            case EXPLOSION_VERTICAL:
                return Sprite.explosion_vertical;
            case EXPLOSION_HORIZONTAL_LEFT_LAST:
                return Sprite.explosion_horizontal_left_last;
            case EXPLOSION_HORIZONTAL_RIGHT_LAST:
                return Sprite.explosion_horizontal_right_last;
            case EXPLOSION_CENTER:
                return Sprite.bomb_exploded;
            case EXPLOSION_VERTICAL_DOWN_LAST:
                return Sprite.explosion_vertical_down_last;
            case EXPLOSION_VERTICAL_TOP_LAST:
                return Sprite.explosion_vertical_top_last;
            default:
                return null;
        }
    }

    @Override
    public void update() {
        if (destroy)
            return;
        goAnimate();
        sprite = Sprite.movingSprite(sprites, animate, 300);
        if (sprite != sprites[countFrames]) {
            countFrames++;
        }
        if (countFrames == 3)
            destroy = true;
        img = sprite.getFxImage();
    }
}
