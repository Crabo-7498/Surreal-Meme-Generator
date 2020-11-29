package ResourceIO;

import Util.Tuple;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public BufferedImage LoadImage(String name) {
        BufferedImage loadedImage = null;
        try {
            loadedImage = ImageIO.read(new File("Resources/Images/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedImage;
    }

    public void SaveImage(String name, BufferedImage image, Tuple<Integer, Integer> dimensions) {
        try {
            ImageIO.write(image, "PNG", new File("Output/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
