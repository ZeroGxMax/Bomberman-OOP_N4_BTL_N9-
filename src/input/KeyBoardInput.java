package input;

import constants.Constants.KEYBOARD;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Lấy dữ liệu bàn phím từ một Scene.
 */
public class KeyBoardInput {
    private static KEYBOARD _input;

    /**
     * Lấy key input tương ứng với phím được nhập trong Scene. Nếu không có KeyEvent
     * nào trả về UNKNOWN.
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> 5bdb1658fe5352bf649d393722be7c40fbc391b5
     * @return Một giá trị KEYBOARD (constants.Constants)
     */
    public static KEYBOARD getInput() {
        KEYBOARD answer = _input == null ? KEYBOARD.UNKNOWN : _input;
        _input = null;
        return answer;
    }

    /**
     * Cài đặt Scene cho lớp.
<<<<<<< HEAD
     *
=======
     * 
>>>>>>> 5bdb1658fe5352bf649d393722be7c40fbc391b5
     * @param scene
     */
    public static void setScene(Scene scene) {
        // Bắt sự kiện khi nhấn phím
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 5bdb1658fe5352bf649d393722be7c40fbc391b5
