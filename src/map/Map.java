package map;

import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

import constants.Constants;
import entities.Entity;
import entities.animate.bomb.Bomb;
import entities.animate.mob.Bomber;
import entities.animate.mob.enemy.Balloon;
import entities.animate.mob.enemy.Enemy;
import entities.items.Items;
import entities.still.Grass;
import factory.AnimateFactory;
import factory.ItemFactory;
import factory.StillFactory;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Map {
    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;

    public List<Entity> animateEntities = new ArrayList<>();
    public List<Entity> stillObjects = new ArrayList<>();
    public List<Bomb> bombList = new ArrayList<>();
    public List<Items> items = new ArrayList<>();

    private static int width;
    private static int height;
    private int level;
    public static int[][] _map;
    protected boolean noEnemyLeft = false;

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
                Entity itemEntity = ItemFactory.getItemEntity(i, j, c);
                if (itemEntity != null) {
                    Items anItem = (Items) itemEntity;
                    items.add(anItem);
                    items.get(items.size() - 1).setBomber(getBomber());
                    _map[i][j] = 0;
                }
            }
        }
        // animateEntities.add(new Balloon(1, 1, Sprite.balloom_left[0]));

        // Lưu ý: Phải setGameMap ở bên ngoài (không thể construct trực tiếp).
        sc.close();
    }

    public void initEntities() {
        for (int i = 0; i < animateEntities.size(); i++) {
            animateEntities.get(i).setGameMap(this);
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).setGameMap(this);
        }
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setBomber(getBomber());
            items.get(i).setGameMap(this);
        }
        for (int i = 0; i < animateEntities.size(); i++) {
            animateEntities.get(i).init();
        }
    }

    public Bomber getBomber() {
        for (int i = 0; i < animateEntities.size(); i++) {
            if (animateEntities.get(i).isBomber == true) {
                return (Bomber) animateEntities.get(i);
            }
        }
        return null;
    }

    public Entity getObjectAt(int xUnit, int yUnit) {
        for (int i = 0; i < animateEntities.size(); i++) {
            if (animateEntities.get(i).getxUnit() == xUnit
                    && animateEntities.get(i).getyUnit() == yUnit) {
                return animateEntities.get(i);
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getxUnit() == xUnit
                    && items.get(i).getyUnit() == yUnit) {
                return items.get(i);
            }
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i).getxUnit() == xUnit
                    && stillObjects.get(i).getyUnit() == yUnit) {
                return stillObjects.get(i);
            }
        }

        return null;
    }

    public void updateMap() {
        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).update();
            if (bombList.get(i).isDestroyed()) {
                bombList.remove(i);
                i--;
            }
        }
        for (int i = 0; i < animateEntities.size(); i++) {
            animateEntities.get(i).update();
            if (animateEntities.get(i).isDestroyed()
                    && animateEntities.get(i).getTimeAfter() == 0
                    && animateEntities.get(i) instanceof Entity) {
                animateEntities.remove(i);
                i--;
            }
        }
        stillObjects.forEach(a -> a.update());
        items.forEach(items -> items.update());
    }

    public void renderMap(GraphicsContext gc) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        stillObjects.forEach(stillObjects -> stillObjects.render(gc));
        items.forEach(items -> items.render(gc));
        animateEntities.forEach(animateEntities -> animateEntities.render(gc));
        bombList.forEach(bombList -> bombList.render(gc));
        if (animateEntities.size() == 1 && !noEnemyLeft) {
            setNoEnemyLeft(true);
        }
    }

    public List<Entity> getAnimateEntities() {
        return animateEntities;
    }

    public void setAnimateEntities(List<Entity> animateEntities) {
        this.animateEntities = animateEntities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void setBombList(List<Bomb> bombList) {
        this.bombList = bombList;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static void setWidth(int width) {
        Map.width = width;
    }

    public static void setHeight(int height) {
        Map.height = height;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int[][] get_map() {
        return _map;
    }

    public static void set_map(int[][] _map) {
        Map._map = _map;
    }

    public boolean isNoEnemyLeft() {
        return noEnemyLeft;
    }

    public void setNoEnemyLeft(boolean noEnemyLeft) {
        this.noEnemyLeft = noEnemyLeft;
    }
}
