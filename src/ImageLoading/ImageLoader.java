package ImageLoading;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public BufferedImage LoadImage(String url) {
        BufferedImage loadedImage = null;
        try {
            loadedImage = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedImage;
    }
}
