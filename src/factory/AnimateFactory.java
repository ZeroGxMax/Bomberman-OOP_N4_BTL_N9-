package factory;

import entities.Entity;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Balloon;
import entities.animate.mob.enemy.Oneal;
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
            default:
                return null;
        }
    }
}
