package factory;

import entities.animate.Bomber;
import entities.still.*;
import abstractClasses.Entity;
import graphics.Sprite;

public class StillFactory {
    public static Entity getStillEntity(int i, int j, char c) {
        switch (c) {
            case '#':
                return new Wall(j, i, Sprite.wall);
            case '*':
                return new Brick(j, i, Sprite.brick);
            default:
                return new Grass(j, i, Sprite.grass);
        }
    }
}
