package entities.items;

import constants.Constants;
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
            if (gameMap.isNoEnemyLeft()) {
                start();
            }
        }
        if (time_start == 0) {// time_start == 0 khi chưa start()
            return;
        }
        // kiểm tra xem hết thời gian tác dụng của item chưa
        if (System.currentTimeMillis() - time_start > Constants.STAGE_PASSED_TIME && active) {
            //TODO PLAYSOUND HERE
            gameMap.setStagePassed(true);
        }
    }

}
