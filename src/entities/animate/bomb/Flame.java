package entities.animate.bomb;

import entities.animate.AnimateEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import map.Map;

import java.util.ArrayList;
import java.util.List;

public class Flame extends AnimateEntity {
    private final List<ExplosionUnit> units = new ArrayList<ExplosionUnit>();

    public Flame(int x, int y, int length) {
        units.add(new ExplosionUnit(x, y, Sprite.bomb_exploded));
        for (int i = 1; i <= length; i++) {
            if (Map.availablePosition(x - i, y)) {
                if (i == length) {
                    units.add(new ExplosionUnit(x - i, y, Sprite.explosion_horizontal_left_last));
                } else {
                    units.add(new ExplosionUnit(x - i, y, Sprite.explosion_horizontal));
                }
            } else {
                break;
            }
        }
        for (int i = 1; i <= length; i++) {
            if (Map.availablePosition(x + i, y)) {
                if (i == length) {
                    units.add(new ExplosionUnit(x + i, y, Sprite.explosion_horizontal_right_last));
                } else {
                    units.add(new ExplosionUnit(x + i, y, Sprite.explosion_horizontal));
                }
            } else {
                break;
            }
        }
        for (int i = 1; i <= length; i++) {
            if (Map.availablePosition(x, y - i)) {
                if (i == length) {
                    units.add(new ExplosionUnit(x, y - i, Sprite.explosion_vertical_top_last));
                } else {
                    units.add(new ExplosionUnit(x, y - i, Sprite.explosion_vertical));
                }
            } else {
                break;
            }
        }
        for (int i = 1; i <= length; i++) {
            if (Map.availablePosition(x, y + i)) {
                if (i == length) {
                    units.add(new ExplosionUnit(x, y + i, Sprite.explosion_vertical_down_last));
                } else {
                    units.add(new ExplosionUnit(x, y + i, Sprite.explosion_vertical));
                }
            } else {
                break;
            }
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

    /**
     * This don't need to be initialized
     */
    public void init() {

    }
}
