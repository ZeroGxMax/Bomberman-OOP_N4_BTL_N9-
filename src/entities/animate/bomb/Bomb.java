package entities.animate.bomb;

import constants.Constants.BOMB_STATUS;
import entities.animate.AnimateEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends AnimateEntity {
    private BOMB_STATUS status;
    private BombStart status1;
    private BombExplosion status2;
    private boolean isDestroyed = false;

    public Bomb() {
        super();
        status = BOMB_STATUS.START;
    }

    public Bomb(int x, int y) {
        super(x, y, Sprite.balloom_dead);
        status = BOMB_STATUS.START;
        status1 = new BombStart(x, y);
        status2 = new BombExplosion(x, y);
    }

    public boolean isDestroyed() {
        return status == BOMB_STATUS.DESTROYED ? true : false;
    }

    public void updateStatus() {
        switch (status) {
            case START:
                status = BOMB_STATUS.EXPLOSION;
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
        // TODO Auto-generated method stub
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
        else if(status == BOMB_STATUS.EXPLOSION){
            status2.render(gc);
        }
    }
}
