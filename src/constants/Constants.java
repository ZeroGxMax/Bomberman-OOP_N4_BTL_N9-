package constants;

import java.net.URL;

public class Constants {
    public static final String GAME_TITLE = "Bomberman";
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int BOMB_WAITING_TIME = 100;
    public static final int BOMB_EXPLOSION_TIME = 40;
    public static final int BRICK_DESTROY_TIME = 40;
    public static final int ENEMY_DEATH_TIME = 100;
    public static final int PLAYER_DEATH_TIME = 100;

    public static final String MAP_PATH = Constants.class.getResource("/levels/Level1.txt").getPath();

    public static final String TEXTURE_PATH = "/textures/classic.png";

    public static final String SOUND_PATH = Constants.class.getResource("/sound/").getPath();

    public static final URL LEVEL_1_FXML = Constants.class.getResource("/levels/Level_1.fxml");

    public static final int LEVEL_DELAY_TIME = 1000;

    public static final int BOMBER_DEAD_DELAY_TIME = 1000;

    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

    public enum KEYBOARD {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        K,
        ENTER,
        ESC,
        UNKNOWN
    }

    public enum BOMB_STATUS {
        START,
        EXPLOSION,
        DESTROYED
    }

    public enum BOMB_UNITS {
        BOMB,
        EXPLOSION_CENTER,
        EXPLOSION_VERTICAL,
        EXPLOSION_VERTICAL_TOP_LAST,
        EXPLOSION_VERTICAL_DOWN_LAST,
        EXPLOSION_HORIZONTAL,
        EXPLOSION_HORIZONTAL_LEFT_LAST,
        EXPLOSION_HORIZONTAL_RIGHT_LAST
    }

    public enum MENU_STATUS {
        MENU,
        GUIDE,
        PAUSE,
        GAME_OVER,
        GAME_WIN,

        PLAYING,
        NEXT_STAGE
    }

    public static String[] FONT_PATH = {
            Constants.class.getResource("/fonts/font1.ttf").getPath(),
            Constants.class.getResource("/fonts/font2.TTF").getPath(),
            Constants.class.getResource("/fonts/font3.otf").getPath()
    };
}
