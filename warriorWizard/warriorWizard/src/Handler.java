import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<>();

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
}
