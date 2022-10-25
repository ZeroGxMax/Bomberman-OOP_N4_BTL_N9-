package factory;

import entities.Entity;
import entities.items.LimitBombItem;
import entities.items.Portal;
import entities.items.PowerupFlame;
import entities.items.SpeedupItem;

public class ItemFactory {
    public static Entity getItemEntity(int i, int j, char c) {
        switch (c) {
            case 'b':
                return new LimitBombItem(j, i);
            case 's':
                return new SpeedupItem(j, i);
            case 'f':
                return new PowerupFlame(j, i);
            case 'x':
                return new Portal(j, i);
            default:
                return null;
        }
    }
}
