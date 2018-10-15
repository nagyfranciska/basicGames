import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    public Game() {
        new Window(1000, 560, "Wizard Game", this);
        start();
    }

    public static void main(String args[]) {
        new Game();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();

        handler = new Handler();

        handler.addObject(new Box(100, 100, ID.Block));
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    public void tick() {
        handler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            // pre-loading 3 frames before they are shown;
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        //////////////////////////////////////////
                //DRAWING happens here//

        //for background:
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        //for objects:
        handler.render(g);

        ///////////////////////////////////////////
        g.dispose();
        bs.show();
    }
}
