package ResourceIO;

import Util.ColorIO;
import Util.Tuple;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

    public Color GetDefaultImageColor(BufferedImage image, Tuple<Integer, Integer> start, Tuple<Float, Float> size) {
        ColorIO cl = new ColorIO();
        long[] rgbValues = cl.GetSampleColorOfArea(image, start, size);
        float[] cRGB = {rgbValues[0] / (size.x * size.y), rgbValues[1] / (size.x * size.y), rgbValues[2] / (size.x * size.y)};
        System.out.println(Arrays.toString(cRGB));

        if((cRGB[0] < 180 && cRGB[1] < 100 && cRGB[2] >= 245) || (cRGB[0] < 115 && cRGB[1] < 115 && cRGB[2] < 190)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public void SaveImage(String name, BufferedImage image, Tuple<Integer, Integer> dimensions) {
        try {
            ImageIO.write(image, "PNG", new File("Output/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
