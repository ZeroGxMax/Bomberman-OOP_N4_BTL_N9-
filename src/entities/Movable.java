package entities;

public interface Movable {
    boolean isCanChangeDirection();

    void calculateMove();

    void move(double xa, double ya);

    void setDirection();
}
