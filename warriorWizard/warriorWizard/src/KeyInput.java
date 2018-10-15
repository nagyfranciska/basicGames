import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {handler.setUp(true);}
                if (key == KeyEvent.VK_DOWN) {handler.setDown(true);}
                if (key == KeyEvent.VK_LEFT) {handler.setLeft(true);}
                if (key == KeyEvent.VK_RIGHT) {handler.setRight(true);}
//                if (key == KeyEvent.VK_SPACE) {temp.isShooting == true;}
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {handler.setUp(false);}
                if (key == KeyEvent.VK_DOWN) {handler.setDown(false);}
                if (key == KeyEvent.VK_LEFT) {handler.setLeft(false);}
                if (key == KeyEvent.VK_RIGHT) {handler.setRight(false);}
//                if (key == KeyEvent.VK_SPACE) {temp.isShooting == true;}
            }
        }
    }
}
