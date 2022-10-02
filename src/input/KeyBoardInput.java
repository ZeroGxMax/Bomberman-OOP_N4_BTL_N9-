package input;

import constants.Constants.KEYBOARD;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyBoardInput {
    private static KEYBOARD _input;

    public static KEYBOARD getInput() {
        KEYBOARD answer = _input == null ? KEYBOARD.UNKNOWN : _input;
        _input = null;
        return answer;
    }

    public static void setScene(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        _input = KEYBOARD.UP;
                        break;
                    case DOWN:
                        _input = KEYBOARD.DOWN;
                        break;
                    case LEFT:
                        _input = KEYBOARD.LEFT;
                        break;
                    case RIGHT:
                        _input = KEYBOARD.RIGHT;
                        break;
                    case ENTER:
                        _input = KEYBOARD.ENTER;
                        break;
                    case ESCAPE:
                        _input = KEYBOARD.ESC;
                    default:
                        _input = KEYBOARD.UNKNOWN;
                        break;
                }
            }
        });
    }
}
