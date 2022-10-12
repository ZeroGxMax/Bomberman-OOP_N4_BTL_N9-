package factory;

import entities.Entity;
import entities.items.LimitBombItem;
import entities.items.SpeedupItem;
import entities.still.Grass;
import entities.still.Wall;
import entities.still.destroyable.Brick;
import graphics.Sprite;

public class ItemFactory {
    public static Entity getItemEntity(int i, int j, char c) {
        switch (c) {
            case 'b':
                return new LimitBombItem(j, i);
            case 's':
                return new SpeedupItem(j, i);
            default:
                return null;
        }
    }
}
