package entities.animate.bomb;

import constants.Constants.BOMB_STATUS;
import entities.Entity;
import entities.animate.AnimateEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

public class Bomb extends AnimateEntity {
    private BOMB_STATUS status;
    private BombStart status1;
    private BombExplosion status2;

    public Bomb() {
        super();
        status = BOMB_STATUS.START;
    }

    public Bomb(int x, int y, int length) {
        super(x, y, Sprite.balloom_dead);
        status = BOMB_STATUS.START;
        status1 = new BombStart(x, y);
        status2 = new BombExplosion(x, y,length);
        Map._map[y][x] = 0;
    }

    public boolean isDestroyed() {
        return status == BOMB_STATUS.DESTROYED ? true : false;
    }

    public void updateStatus() {
        switch (status) {
            case START:
                status = BOMB_STATUS.EXPLOSION;
                Map._map[yUnit][xUnit] = 1;
                Entity detroyedObject[] = {
                        gameMap.getObjectAt(xUnit + 1, yUnit),
                        gameMap.getObjectAt(xUnit - 1, yUnit),
                        gameMap.getObjectAt(xUnit, yUnit + 1),
                        gameMap.getObjectAt(xUnit, yUnit - 1)
                };
                for (int i = 0; i < 4; i++) {
                    if (detroyedObject[i] != null
                            && !detroyedObject[i].isDestroyed()) {
                        detroyedObject[i].setDestroyed(true);
                    }
                }
                break;
            case EXPLOSION:
                status = BOMB_STATUS.DESTROYED;
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        if (status == BOMB_STATUS.START) {
            status1.update();
            if (status1.isNext())
                updateStatus();
        }
        if (status == BOMB_STATUS.EXPLOSION) {
            status2.update();
            if (status2.isDestroy()) {
                updateStatus();
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (status == BOMB_STATUS.START)
            status1.render(gc);
        else if (status == BOMB_STATUS.EXPLOSION) {
            status2.render(gc);
        }
    }
}
