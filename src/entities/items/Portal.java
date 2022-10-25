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
        if (timeCount < 0) {
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
        if (!destroyed || timeCount < 0) {
            return;
        }
        if (destroyed == true) {
            if (timeCount == 0) {
                sprite = Sprite.grass;
                Map._map[yUnit][xUnit] = 1;
            } else if (timeCount < Constants.BRICK_DESTROY_TIME/3) {
                sprite = Sprite.brick_exploded[2];
            } else if (timeCount < Constants.BRICK_DESTROY_TIME*2/3) {
                sprite = Sprite.brick_exploded[1];
            } else  {
                sprite = Sprite.brick_exploded[0];
            }
            timeCount--;
            img = sprite.getFxImage();
        }
    }

}
