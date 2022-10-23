package graphics;

import java.awt.*;

public class Screen {
    protected int width;
    protected int height;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("Arial", Font.PLAIN, 20 * Sprite.DEFAULT_SIZE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);
    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRealWidth() {
        return width * Sprite.DEFAULT_SIZE;
    }

    public int getRealHeight() {
        return height * Sprite.DEFAULT_SIZE;
    }
}
