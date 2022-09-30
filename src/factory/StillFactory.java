package factory;

import entities.animate.Bomber;
import entities.still.Grass;
import entities.still.Wall;
import abstractClasses.Entity;
import graphics.Sprite;

public class StillFactory {
    public static Entity getStillEntity(int i, int j, char c) {
        switch (c) {
            case '#':
                return new Wall(j, i, Sprite.wall.getFxImage());
            default:
                return new Grass(j, i, Sprite.grass.getFxImage());
        }
    }
}
