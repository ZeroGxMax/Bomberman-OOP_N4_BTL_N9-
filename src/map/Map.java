package map;


import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import entities.animate.AnimateEntity;
import entities.Entity;
import entities.still.Grass;
import entities.still.Wall;
import factory.*;
import graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;

public class Map {
    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;
    private List<Entity> animateEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private int level;
    private int width;
    private int height;

    public int getLevel() {
        return level;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(mapPath));
        level = sc.nextInt();
        height = sc.nextInt();
        width = sc.nextInt();

        sc.nextLine();

        for (int i = 0; i < height; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < width; j++) {
                char c = str.charAt(j);
                stillObjects.add(StillFactory.getStillEntity(i, j, c));
                Entity animateOne = AnimateFactory.getAnimateEntity(i, j, c);
                if (animateOne != null) {
                    animateEntities.add(animateOne);
                }
            }
        }
        sc.close();
    }

    public void updateMap() {
        animateEntities.forEach(Entity::update);
    }

    public void renderMap(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        stillObjects.forEach(stillObjects -> stillObjects.render(gc));
        animateEntities.forEach(animateEntities -> animateEntities.render(gc));
    }
}
