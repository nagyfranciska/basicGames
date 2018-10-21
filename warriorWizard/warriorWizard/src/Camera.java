public class Camera {

    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject gameObject) {

//        x = gameObject.getX() + 900/2;
//        y = gameObject.getY() + 540/2;

        x += ((gameObject.getX() - x) - 900/2) * 0.05f;
        y += ((gameObject.getY() - y) - 540/2) * 0.05f;

        if (x <= 0) x = 0;
        if (x >= 930) x = 935;
        if (y <= 0) y = 0;
        if (y >= 540 + 30) y = 540 + 30;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
