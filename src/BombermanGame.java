import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.Constants.KEYBOARD;
import entities.Entity;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import map.Map;

public class BombermanGame extends Application {

    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;

    private GraphicsContext gc;
    private Canvas canvas;
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

        // Tao scene và set scene cho đầu vào bàn phím
        Constants.scene = new Scene(root);
        Constants.input1.setScene(Constants.scene);

        Map gameMap = new Map();
        try {
            gameMap.createMap(Constants.MAP_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create map!");
        }

        // Them scene vao stage
        stage.setScene(Constants.scene);
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