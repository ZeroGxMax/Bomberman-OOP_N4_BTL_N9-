package entities.items;

import entities.Entity;
import entities.animate.bomb.Bomb;
import entities.animate.mob.Bomber;
import graphics.Sprite;

public abstract class Items extends Entity {
    protected Bomber bomber = new Bomber();
    protected boolean shown = false;
    protected boolean active = false;
    protected Effect effect;

    public enum Effect {
        speedup,
        rangebomb,
        limitbomb
    }

    public Items(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public Effect getEffect() {
        return effect;
    }

    public boolean isActive() {
        return active;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
