package input;

import java.util.LinkedList;
import java.util.Queue;

import constants.Constants;
import constants.Constants.KEYBOARD;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Lưu tất cả các input từ từ bàn phím vào một hàng đợi có các phần tử là
 * KEYBOARD (enum trong constants.Constants).
 */
public class KeyBoardInput {
    private long time = System.currentTimeMillis();
    private Queue<Constants.KEYBOARD> KEY_BOARD_INPUT = new LinkedList<>();

    public void push(KEYBOARD input) {
        if (KEY_BOARD_INPUT.size() < 2 && System.currentTimeMillis() - time > 50) {
            time = System.currentTimeMillis();
            KEY_BOARD_INPUT.add(input);
        }
    }

    public KEYBOARD pop() {
        return KEY_BOARD_INPUT.poll();
    }

    public boolean isEmpty() {
        return KEY_BOARD_INPUT.isEmpty();
    }

    public void setScene(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        push(KEYBOARD.UP);
                        break;
                    case DOWN:
                        push(KEYBOARD.DOWN);
                        break;
                    case LEFT:
                        push(KEYBOARD.LEFT);
                        break;
                    case RIGHT:
                        push(KEYBOARD.RIGHT);
                        break;
                    case ENTER:
                        push(KEYBOARD.ENTER);
                        break;
                    case ESCAPE:
                        push(KEYBOARD.ESC);
                    default:
                        push(KEYBOARD.UNKNOWN);
                        break;
                }
            }
        });
    }
}
