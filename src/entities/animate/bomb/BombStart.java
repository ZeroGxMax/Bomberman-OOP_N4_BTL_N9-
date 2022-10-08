package entities.animate.bomb;

import graphics.Sprite;

public class BombStart extends BombUnit {
    public BombStart(int x, int y) {
        super(x, y, Sprite.bomb);
    }

    public boolean isNext() {
        return isDestroy();
    }
}
