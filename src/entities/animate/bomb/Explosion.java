package entities.animate.bomb;

import java.util.ArrayList;
import java.util.List;

import constants.Constants.BOMB_STATUS;
import entities.Entity;
import entities.animate.AnimateEntity;
import entities.still.Wall;
import entities.still.destroyable.Brick;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;
import media.SoundController;

public class Explosion extends AnimateEntity {
    private BOMB_STATUS status;
    private Bomb status1;
    private Flame status2;
    protected int bombLength;

    public Explosion() {
        super();
        status = BOMB_STATUS.START;
    }

    public Explosion(int x, int y, int bombLength) {
        super(x, y, Sprite.balloom_dead);
        this.bombLength = bombLength;
        status = BOMB_STATUS.START;
        status1 = new Bomb(x, y);
        status2 = new Flame(x, y, bombLength);
        Map._map[y][x] = 0;
    }

    public boolean isDestroyed() {
        return status == BOMB_STATUS.DESTROYED;
    }

    public void destroyedObjects() {
        List<Entity> destroyedObject = new ArrayList<>();
        for (int i = 1; i <= bombLength; i++) {
            Entity entity = gameMap.getObjectAt(xUnit + i, yUnit);
            if (entity instanceof Wall || (entity instanceof Brick && !entity.isDestroyed())) {
                destroyedObject.add(entity);
                break;
            } else {
                destroyedObject.add(entity);
            }
        }
        for (int i = 1; i <= bombLength; i++) {
            Entity entity = gameMap.getObjectAt(xUnit - i, yUnit);
            if (entity instanceof Wall || (entity instanceof Brick && !entity.isDestroyed())) {
                destroyedObject.add(entity);
                break;
            } else {
                destroyedObject.add(entity);
            }
        }
        for (int i = 1; i <= bombLength; i++) {
            Entity entity = gameMap.getObjectAt(xUnit, yUnit + i);
            if (entity instanceof Wall || (entity instanceof Brick && !entity.isDestroyed())) {
                destroyedObject.add(entity);
                break;
            } else {
                destroyedObject.add(entity);
            }
        }
        for (int i = 1; i <= bombLength; i++) {
            Entity entity = gameMap.getObjectAt(xUnit, yUnit - i);
            if (entity instanceof Wall || (entity instanceof Brick && !entity.isDestroyed())) {
                destroyedObject.add(entity);
                break;
            } else {
                destroyedObject.add(entity);
            }
        }
        for (int i = 0; i < destroyedObject.size(); i++) {
            if (destroyedObject.get(i) != null
                    && !destroyedObject.get(i).isDestroyed()) {
                destroyedObject.get(i).setDestroyed(true);
            }
        }
    }

    public void updateStatus() {
        switch (status) {
            case START:
                status = BOMB_STATUS.EXPLOSION;
                Map._map[yUnit][xUnit] = 1;
                SoundController.playSound(4);
                destroyedObjects();
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
