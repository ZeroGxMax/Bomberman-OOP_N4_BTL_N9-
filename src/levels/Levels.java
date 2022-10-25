package levels;

import java.io.FileNotFoundException;
import constants.Constants;
import graphics.Sprite;
import input.KeyBoardInput;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import map.Map;
import support.Delay;

import static constants.Constants.WIDTH;

public class Levels {
    public Map gameMap = new Map();

    public void setLevel1Introdution(Stage stage) {
        gameMap.reset();
        gameMap = new Map();
        Pane mainPane = new Pane();
        try {
            mainPane = (Pane) FXMLLoader.load(Constants.LEVEL_FXML[0]);
        } catch (Exception e) {
            System.out.println("Error: Cannot load Level_1 fxml file");
        }
        // Tao scene và set scene cho đầu vào bàn phím
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
        try {
            Delay.delay(Constants.LEVEL_DELAY_TIME, () -> this.setLevel1(stage));
        } catch (Exception e) {
            System.out.println("Error beginning level 1");
        }
    }


    public void setLevel1(Stage stage) {
        // Tao Canvas
        Canvas canvas = new Canvas(Sprite.SCALED_SIZE * Constants.WIDTH,
                Sprite.SCALED_SIZE * Constants.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene và set scene cho đầu vào bàn phím
        Scene scene = new Scene(root);
        KeyBoardInput.setScene(scene);

        try {
            gameMap.createMap(Constants.MAP_PATH[0]);
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
                if (gameMap.getBomber() != null
                        && gameMap.getBomber().isDestroyed()
                        && gameMap.getBomber().getTimeAfter() < 0) {
                    try {
                        setLevel1Introdution(stage);
                        this.stop();
                    } catch (Exception e) {
                        System.out.println("Error: Cannnot return to level 1");
                    }
                }
                if (gameMap != null
                        && gameMap.isStagePassed()) {
                    setLevel2Introduction(stage);
                    this.stop();
                }
            }
        };
        timer.start();

    }

    public void setLevel2Introduction(Stage stage) {
        gameMap.reset();
        gameMap = new Map();
        Pane mainPane = new Pane();
        try {
            mainPane = (Pane) FXMLLoader.load(Constants.LEVEL_FXML[1]);
        } catch (Exception e) {
            System.out.println("Error: Cannot load Level_1 fxml file");
        }
        // Tao scene và set scene cho đầu vào bàn phím
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
        try {
            Delay.delay(Constants.LEVEL_DELAY_TIME, () -> this.setLevel2(stage));
        } catch (Exception e) {
            System.out.println("Error beginning level 2");
        }
    }

    public void setLevel2(Stage stage) {
        // Tao Canvas
        Canvas canvas = new Canvas(Sprite.SCALED_SIZE * Constants.WIDTH,
                Sprite.SCALED_SIZE * Constants.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene và set scene cho đầu vào bàn phím
        Scene scene = new Scene(root);
        KeyBoardInput.setScene(scene);

        try {
            gameMap.createMap(Constants.MAP_PATH[1]);
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
                if (gameMap.getBomber() != null
                        && gameMap.getBomber().isDestroyed()
                        && gameMap.getBomber().getTimeAfter() < 0) {
                    try {
                        setLevel1Introdution(stage);
                        this.stop();
                    } catch (Exception e) {
                        System.out.println("Error: Cannnot return to level 1");
                    }
                }
                if (gameMap != null
                        && gameMap.isStagePassed()) {
                    setLevel2Introduction(stage);
                    this.stop();
                }
            }
        };
        timer.start();

    }
}
