package entities.animate.bomb;

import java.util.ArrayList;
import java.util.List;

import entities.animate.AnimateEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

public class BombExplosion extends AnimateEntity {
    private List<BombUnit> units = new ArrayList<BombUnit>();

    public BombExplosion(int x, int y) {
        units.add(new BombUnit(x, y, Sprite.bomb_exploded));
        if (gameMap.isCanStepOn(x - 1, y)) {
            units.add(new BombUnit(x - 1, y, Sprite.explosion_horizontal_left_last));
        }
        if (gameMap.isCanStepOn(x + 1, y)) {
            units.add(new BombUnit(x + 1, y, Sprite.explosion_horizontal_right_last));
        }
        if (gameMap.isCanStepOn(x, y + 1)) {
            units.add(new BombUnit(x, y + 1, Sprite.explosion_vertical_down_last));
        }

        if (gameMap.isCanStepOn(x, y - 1)) {
            units.add(new BombUnit(x, y - 1, Sprite.explosion_vertical_top_last));
        }
    }

    @Override
    public void update() {
        units.forEach(units -> units.update());
    }

    @Override
    public void render(GraphicsContext gc) {
        units.forEach(units -> units.render(gc));
    }

    public boolean isDestroy() {
        return units.get(0).isDestroy();
    }
}
