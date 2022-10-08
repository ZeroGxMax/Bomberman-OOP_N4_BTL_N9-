package entities.animate.bomb;

import graphics.Sprite;
import map.Map;

public class BombStart extends BombUnit {
    public BombStart(int x, int y) {
        super(x, y, Sprite.bomb);
        Map._map[y][x] = 0;
    }

    public boolean isNext() {
        return isDestroy();
    }
}
