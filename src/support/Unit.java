package support;

import graphics.Sprite;

public class Unit {
    public static double unitToCoordinate(double unit) {
        return unit * Sprite.SCALED_SIZE;
    }

    public static double unitToCoordinate(int unit) {
        return ((double) unit) * Sprite.SCALED_SIZE;
    }

    public static int coordinateToUnit(double coordinate) {
        return (int) (coordinate / (double) Sprite.SCALED_SIZE);
    }
}
