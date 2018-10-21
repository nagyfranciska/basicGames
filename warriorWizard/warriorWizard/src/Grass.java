import java.awt.*;

public class Grass extends GameObject {

    public Grass(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, 30, 30);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }
}
