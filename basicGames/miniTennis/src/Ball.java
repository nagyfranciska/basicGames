import java.awt.*;

public class Ball {

    private static final int DIAMETER = 30;
    //    position
    private int x = 0;
    private int y = 0;
    //    direction
    private int xa = x;
    private int ya = y;

    private Game game;

    public Ball(Game game) {
        this.game = game;
    }

    public void move() {
        boolean changesDirection = true;

        if (x + xa <= 0) {
            xa = game.SPEED;
        } else if (x + xa > game.getWidth() - DIAMETER) {
            xa = -game.SPEED;
        } else if (y + ya <= 0) {
            ya = game.SPEED;
        } else if (y + ya > game.getHeight() - DIAMETER) {
            game.gameOver();
        } else if (isCollision()) {
            ya = -game.SPEED;
            y = game.slider.getTopY() - DIAMETER;
            game.score ++;
        } else {
            changesDirection = false;
        }
        if (changesDirection) {
            Sound.BOUNCE.play();
        }

        x += xa;
        y += ya;
    }

    public boolean isCollision() {
        return game.slider.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER/2, DIAMETER);
    }
}
