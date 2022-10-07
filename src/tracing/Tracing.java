package tracing;

import constants.Constants.DIRECTION;

import java.util.Random;

public abstract class Tracing {
    public static Random random = new Random();

    public abstract DIRECTION calculateDirection();

}
