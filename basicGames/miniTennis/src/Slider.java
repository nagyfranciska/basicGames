import java.awt.*;
import java.awt.event.KeyEvent;

public class Slider {

    private static final int Y = 330;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    private int x = 0;
    private int xa = x;
    private Game game;

    public Slider(Game game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
            x += xa;
        }
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = game.SPEED;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -game.SPEED;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y;
    }

}
