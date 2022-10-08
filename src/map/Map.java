package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import constants.Constants;
import entities.Entity;
import entities.animate.bomb.Bomb;
import entities.animate.mob.Bomber;
import entities.still.Brick;
import entities.still.Grass;
import factory.AnimateFactory;
import factory.StillFactory;
import javafx.scene.canvas.GraphicsContext;

public class Map {
    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;

    public List<Entity> animateEntities = new ArrayList<>();
    public List<Entity> stillObjects = new ArrayList<>();
    public List<Bomb> bomb = new ArrayList<>();

    private static int width;
    private static int height;
    private int level;
    public static int[][] _map;

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
     *
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
        for (int i = 0; i < animateEntities.size(); i++) {
            animateEntities.get(i).setGameMap(this);
        }
        // Lưu ý: Phải setGameMap ở bên ngoài (không thể construct trực tiếp).
        sc.close();
    }

    public Bomber getBomber() {
        for (int i = 0; i < animateEntities.size(); i++) {
            if (animateEntities.get(i).isBomber == true) {
                return (Bomber) animateEntities.get(i);
            }
        }
        return null;
    }

    public Brick getBrickAt(int xUnit, int yUnit) {
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i) instanceof Brick && stillObjects.get(i).getxUnit() == xUnit && stillObjects.get(i).getyUnit() == yUnit) {
                return (Brick) stillObjects.get(i);
            }
        }
        return null;
    }

    public void updateMap() {

        animateEntities.forEach(Entity::update);
        stillObjects.forEach(a->a.update());
        bomb.forEach(bomb->bomb.update());
    }

    public void renderMap(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        stillObjects.forEach(stillObjects -> stillObjects.render(gc));
        animateEntities.forEach(animateEntities -> animateEntities.render(gc));
        bomb.forEach(bomb -> bomb.render(gc));
    }
}
