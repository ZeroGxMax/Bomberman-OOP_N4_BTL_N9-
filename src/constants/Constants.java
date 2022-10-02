package constants;

public class Constants {
    public static final String GAME_TITLE = "Bomberman";
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static final String MAP_PATH = Constants.class.getResource("/levels/Level1.txt").getPath();

    public static final String TEXTURE_PATH = "/textures/classic.png";

    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }
}

