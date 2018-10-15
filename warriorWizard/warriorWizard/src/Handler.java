import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();

    private boolean up, down, right, left;

    public void tick(){
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            //updates every object in list
            temp.tick();
        }
    }

    public void render(Graphics2D g){
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            //draws every object in list
            temp.render(g);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
