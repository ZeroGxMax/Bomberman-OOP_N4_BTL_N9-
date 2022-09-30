package factory;

import abstractClasses.Entity;
import entities.animate.Bomber;
import graphics.Sprite;

public class AnimateFactory {
    public static Entity getAnimateEntity(int i, int j, char c) {
        switch (c) {
            case 'p':
                return new Bomber(j, i, Sprite.player_right.getFxImage());
            default:
                return null;
        }
    }
}
