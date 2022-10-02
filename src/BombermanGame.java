import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import map.Map;
import entities.animate.mob.Bomber;
import entities.Entity;
import entities.still.Grass;
import entities.still.Wall;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class BombermanGame extends Application {

    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(Constants.GAME_TITLE);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        Map gameMap = new Map();
        try {
            gameMap.createMap(Constants.MAP_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create map!");
        }


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameMap.renderMap(gc);
                gameMap.updateMap();
            }
        };
        timer.start();
    }
}