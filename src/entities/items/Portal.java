package entities.items;

import graphics.Sprite;
import map.Map;

public class Portal extends Items {

    public Portal(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.actualSprite = Sprite.portal;
    }

    @Override
    protected void setPowerUp() {
    }

    @Override
    public void update() {
        if (end) {
            return;
        }
        if (destroyed && !shown && time_start == 0) {
            Map._map[yUnit][xUnit] = 1;
            shown = true;
            sprite = actualSprite;
        }
        // kiểm tra vị trí bomber so với item
        if (bomber.getxUnit() == xUnit && bomber.getyUnit() == yUnit && shown) {
            // sprite = Sprite.grass;
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

}
