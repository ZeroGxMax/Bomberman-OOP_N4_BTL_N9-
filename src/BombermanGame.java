import java.io.FileNotFoundException;

import javafx.concurrent.Task;
import support.Delay;

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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import map.Map;

public class BombermanGame extends Application {

    public static final int WIDTH = Constants.WIDTH;
    public static final int HEIGHT = Constants.HEIGHT;

    private GraphicsContext gc;
    private Canvas canvas;
    public static Scene level_1_scene;
    public static Scene scene;
    public static Map gameMap = new Map();

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
//                System.out.println(gameMap.items.size());
                gameMap.renderMap(gc);
                gameMap.updateMap();
            }
        };
        timer.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(Constants.GAME_TITLE);

        Pane mainPane = (Pane) FXMLLoader.load(Constants.LEVEL_1_FXML);
        // Tao scene và set scene cho đầu vào bàn phím
        level_1_scene = new Scene(mainPane);
        stage.setScene(level_1_scene);
        stage.show();
        delay(1000, () -> setMainScene(stage));
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
}