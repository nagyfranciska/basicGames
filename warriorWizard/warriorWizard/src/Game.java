import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
//    private BufferedImage level = null;
    private Level level;
    private Camera camera;

    public Game() {
        new Window(900, 540, "Wizard Game", this);
        start();

        handler = new Handler();
        camera = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));
//        ImageLoader imageLoader = new ImageLoader();
//        wizard = imageLoader.loadImage("assets/wizard.png");

        level = new Level();
        loadMap(level);

        handler.addObject(new Wizard(100, 100, ID.Player, handler));
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
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                camera.tick(handler.objects.get(i));
            }
        }

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

//        for background:
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.translate(-camera.getX(), -camera.getY());

        //for objects:

        handler.render(g);

        g.translate(camera.getX(), camera.getY());

        ///////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    private void loadMap(Level level) {
        for (int i = 0; i < level.map.length; i++) {
            for (int j = 0; j < level.map[i].length; j++) {
                if (level.map[i][j] == 1) {
                    handler.addObject(new Block(j * 30, i * 30, ID.Block));
                }
                else {
                    handler.addObject(new Grass(j * 30, i * 30, ID.Grass));
                }
            }
        }
    }

    public static void main(String args[]) {
        new Game();
    }
}
