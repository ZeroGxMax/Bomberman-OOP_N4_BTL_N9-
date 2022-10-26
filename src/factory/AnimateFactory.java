package factory;

import entities.Entity;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.*;
import graphics.Sprite;

public class AnimateFactory {
    public static Entity getAnimateEntity(int i, int j, char c) {
        switch (c) {
            case 'p':
                return new Bomber(j, i, Sprite.player_right[0]);
            case '1':
                return new Balloon(j, i, Sprite.balloom_left[0]);
            case '2':
                return new Oneal(j, i, Sprite.oneal_left[0]);
            case '3':
                return new Dahl(j, i,Sprite.doll_left[0]);
            case '4':
                return new Kondoria(j, i,Sprite.kondoria_left[0]);
            case '5':
                return new Minvo(j, i,Sprite.minvo_left[0]);
            case '6':
                return new Ghost(j, i,Sprite.ghost_left[0]);
            default:
                return null;
        }
    }
}
