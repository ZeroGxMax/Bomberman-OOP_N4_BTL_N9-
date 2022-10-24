package game;

import java.io.FileNotFoundException;

import constants.Constants;
import graphics.Sprite;
import input.KeyBoardInput;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import levels.Levels;
import map.Map;
import constants.Constants.MENU_STATUS;
import support.Delay;

import static constants.Constants.MENU_STATUS.PLAYING;
import static graphics.Sprite.SCALED_SIZE;

public class BombermanGame extends Application {
    public static MENU_STATUS menu_status;
    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;

    public static Canvas canvas = new Canvas();
    public static GraphicsContext gc = canvas.getGraphicsContext2D();
    public static Scene level_1_scene;
    public static Scene scene;
    public static Map gameMap = new Map();
    public static Stage stage;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public void setMainScene(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene và set scene cho đầu vào bàn phím
        scene = new Scene(root);
        KeyBoardInput.setScene(scene);

        try {
            gameMap.createMap(Constants.MAP_PATH);
            gameMap.initEntities();
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

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(Constants.GAME_TITLE);

        Levels myLevel = new Levels();
        myLevel.setLevel1Introdution(stage);
    }
}