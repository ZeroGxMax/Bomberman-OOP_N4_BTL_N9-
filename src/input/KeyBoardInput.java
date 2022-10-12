package input;

import constants.Constants.KEYBOARD;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Lấy dữ liệu bàn phím từ một Scene.
 */
public class KeyBoardInput {
    private static KEYBOARD _input = KEYBOARD.UNKNOWN;
    private static KEYBOARD input = KEYBOARD.UNKNOWN;

    /**
     * Lấy key input tương ứng với phím được nhập trong Scene. Nếu không có KeyEvent
     * nào trả về UNKNOWN.
     *
     * @return Một giá trị KEYBOARD (constants.Constants)
     */
    public static KEYBOARD getInput() {
        return _input;
    }

    public static KEYBOARD getInput2() {
        return input;
    }

    /**
     * Cài đặt Scene cho lớp.
     *
     * @param scene
     */
    public static void setScene(Scene scene) {
        // Bắt sự kiện khi nhấn phím
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                    case W:
                        _input = KEYBOARD.UP;
                        break;
                    case DOWN:
                    case S:
                        _input = KEYBOARD.DOWN;
                        break;
                    case LEFT:
                    case A:
                        _input = KEYBOARD.LEFT;
                        break;
                    case RIGHT:
                    case D:
                        _input = KEYBOARD.RIGHT;
                        break;
                    case ENTER:
                    case SPACE:
                        input = KEYBOARD.ENTER;
                        break;
                    case ESCAPE:
                        input = KEYBOARD.ESC;
                    default:
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                    case W:
                    case DOWN:
                    case S:
                    case LEFT:
                    case A:
                    case RIGHT:
                    case D:
                        _input = KEYBOARD.UNKNOWN;
                        break;
                    case ENTER:
                    case SPACE:
                    case ESCAPE:
                        input = KEYBOARD.UNKNOWN;
                    default:
                        break;
                }
            }
        });
    }
}
