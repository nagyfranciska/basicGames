import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private BufferedImage img;

    public BufferedImage loadImage(String path) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
