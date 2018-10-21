import java.awt.*;

public class Wizard extends GameObject {

    Handler handler;

    public Wizard(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

        if (handler.isUp()) {velY = -5;} else if (!handler.isDown()) {velY = 0;}
        if (handler.isDown()) {velY = 5;} else if (!handler.isUp()) {velY = 0;}
        if (handler.isLeft()) {velX = -5;} else if (!handler.isRight()) {velX = 0;}
        if (handler.isRight()) {velX = 5;} else if (!handler.isLeft()) {velX = 0;}
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size() ; i++) {
            GameObject object = handler.objects.get(i);

            if (object.getId() == ID.Block) {
                if (getBounds().intersects(object.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 30, 40);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 40);
    }
}
