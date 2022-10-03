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
<<<<<<< HEAD
    private static int width;
    private static int[][] _map;
    private int level;
    private List<Entity> stillObjects = new ArrayList<>();
=======
    private List<Entity> stillObjects = new ArrayList<>();
    private static int[][] _map;
    private int level;
    private static int width;
>>>>>>> 5bdb1658fe5352bf649d393722be7c40fbc391b5
    private static int height;

    public int getLevel() {
        return level;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Kiểm tra ô có tọa độ (x, y) trong bản đồ bomber có thể đi vào được không.
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> 5bdb1658fe5352bf649d393722be7c40fbc391b5
     * @param x Tọa độ theo chiều ngang, hướng trái -> phải, bắt đầu từ 0
     * @param y Tọa độ theo chiều dọc, hướng từ trên -> dưới, bắt đầu từ 0
     * @return Khả năng đi vào ô (x, y)
     */
    public static boolean isCanStepOn(int x, int y) {
        // Kiểm tra tọa độ có trong map không
        if (x < 0 || y < 0 || x >= width || y >= height)
            return false;
        return _map[y][x] == 1 ? true : false;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(mapPath));
        level = sc.nextInt();
        height = sc.nextInt();
        width = sc.nextInt();
        _map = new int[height][width];

        sc.nextLine();

        for (int i = 0; i < height; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < width; j++) {
                char c = str.charAt(j);
                Entity temp = StillFactory.getStillEntity(i, j, c);
                stillObjects.add(temp);
                _map[i][j] = Grass.isGrass(temp) ? 1 : 0;
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