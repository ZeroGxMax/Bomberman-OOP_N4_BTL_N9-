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

    @Override
    public void update() {
        if (destroy)
            return;
        goAnimate();
        sprite = Sprite.movingSprite(sprites, animate, 120);
        if (sprite != sprites[countFrames]) {
            countFrames++;
        }
        if (countFrames == 3) {
            destroy = true;
        }
            
            
        img = sprite.getFxImage();
    }
}
