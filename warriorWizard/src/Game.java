import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;

    public Game() {
        new Window(1000, 560, "Wizard Game", this);
    }

    public static void main(String args[]) {
        new Game();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
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
//        int updates = 0;
//        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
//                updates++;
                delta--;
            }
            render();
//            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames + " TICKS: " + updates);
//                frames = 0;
//                updates = 0;
            }

        }
        stop();
    }

    public void tick() {}

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



        ///////////////////////////////////////////
        g.dispose();
        bs.show();
    }
}
