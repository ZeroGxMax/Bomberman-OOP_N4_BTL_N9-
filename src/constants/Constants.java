package constants;

public class Constants {
    public static final String GAME_TITLE = "Bomberman";
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int BOMB_EXPLOSION_TIME = 40;
    public static final int BRICK_DESTROY_TIME = 50;

    public static final String MAP_PATH = Constants.class.getResource("/levels/Level1.txt").getPath();

    public static final String TEXTURE_PATH = "/textures/classic.png";

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

    public enum BOMB_STATUS{
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
}
