package factory;

import abstractClasses.Entity;
import entities.animate.*;
import graphics.Sprite;

public class AnimateFactory {
    public static Entity getAnimateEntity(int i, int j, char c) {
        switch (c) {
            case 'p':
                return new Bomber(j, i, Sprite.player_right.getFxImage());
            case '1':
                return new Balloon(j, i, Sprite.balloom_left1.getFxImage());
            case '2':
                return new Oneal(j, i, Sprite.oneal_left1.getFxImage());
            default:
                return null;
        }
    }
}
