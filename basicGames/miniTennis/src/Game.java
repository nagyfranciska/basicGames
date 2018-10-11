import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel {

    Ball ball = new Ball(this);
    Slider slider = new Slider(this);
    final int WIDTH = 300;
    final int HEIGHT = 400;
    int score = 0;
    final int SPEED = 2;

    private int getScore() {
        return score;
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                slider.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                slider.keyReleased(e);
            }
        });
        setFocusable(true);
        Sound.BACKGROUND.loop();
    }

    private void move() {
        ball.move();
        slider.move();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        slider.paint(g2d);
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Hervecia", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
    }

    public void gameOver() {
        Sound.BACKGROUND.stop();
        Sound.GAMEOVER.play();

        Object[] options = { "HELL YEAH", "NOPE" };
        JOptionPane.showOptionDialog(this, "Your score is: " + getScore() + "\nTry again?", "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//         how to start again ? :)
        System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();
        frame.add(game);
        frame.setSize(game.WIDTH, game.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
