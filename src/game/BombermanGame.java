package game;

import constants.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import levels.Levels;

public class BombermanGame extends Application {

    public static Canvas canvas = new Canvas();
    public static GraphicsContext gc = canvas.getGraphicsContext2D();
    public static Scene scene;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(Constants.GAME_TITLE);

        Levels myLevel = new Levels();
        myLevel.setLevelIntroduction(stage);
    }
}