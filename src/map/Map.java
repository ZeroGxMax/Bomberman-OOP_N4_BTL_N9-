package map;


import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import entities.Bomber;
import entities.Entity;
import entities.Grass;
import entities.Wall;
import graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;

public class Map {
    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;
    private List<Entity> entities = new ArrayList<>();
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

    public Entity charIntoEntity(int i, int j, char c) {
        Entity object;
        switch (c) {
            case '#':
                object = new Wall(j, i, Sprite.wall.getFxImage());
                break;
//            case 'p':
//                object = new Bomber(j, i, Sprite.player_right.getFxImage());
//                entities.add(object);
//                break;
            default:
                object = new Grass(j, i, Sprite.grass.getFxImage());
                break;
        }
        return object;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        Scanner sc = new Scanner(new File(mapPath));
        level = sc.nextInt();
        height = sc.nextInt();
        width = sc.nextInt();

        sc.nextLine();

        for (int i = 0; i < height; i++) {
            String str = sc.nextLine();
//            System.out.println(str);
            for (int j = 0; j < width; j++) {
                Entity object;
                char c = str.charAt(j);
                object = charIntoEntity(i, j, c);
                stillObjects.add(object);
            }
        }
        sc.close();
    }

//    public void createMap(String mapPath) {
//        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//        entities.add(bomberman);
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                } else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
//    }

    public void updateMap() {
        entities.forEach(Entity::update);
    }

    public void renderMap(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
