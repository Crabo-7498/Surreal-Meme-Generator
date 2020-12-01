package ResourceIO;

import Util.ColorIO;
import Util.Tuple;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * The ImageLoader class loads and saves images as well as other utility methods
 */
public class ImageLoader {

    /**
     * Takes in a filename and loads that file through the Resources path
     * @param name The name of the file
     * @return Returns a BufferedImage
     */
    public BufferedImage LoadImage(String name) {
        BufferedImage loadedImage = null;
        try {

            //Load image from /Resources/Images/
            loadedImage = ImageIO.read(new File("Resources/Images/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedImage;
    }

    /**
     * Takes in an image, start position and area
     * Compares the light-dark RGB values to the sampled RGB values
     * Return WHITE or BLACK as a Color
     * @param image The background image
     * @param start The start position of the area
     * @param size The size of the area
     * @return return WHITE or BLACK as a Color
     */
    public Color GetDefaultImageColor(BufferedImage image, Tuple<Integer, Integer> start, Tuple<Float, Float> size) {
        ColorIO cl = new ColorIO();
        long[] rgbValues = cl.GetSampleColorOfArea(image, start, size);

        // Converts the long RGB values to floats
        float[] cRGB = {rgbValues[0] / (size.x * size.y), rgbValues[1] / (size.x * size.y), rgbValues[2] / (size.x * size.y)};

        // Compares the RGB values
        if((cRGB[0] < 180 && cRGB[1] < 100 && cRGB[2] >= 245) || (cRGB[0] < 115 && cRGB[1] < 115 && cRGB[2] < 190)) return Color.WHITE;
        return Color.BLACK;
    }

    /**
     * Takes in String and writes the edited image to the desired location
     * @param name The name of the file
     * @param image The final image
     */
    public void SaveImage(String name, BufferedImage image) {
        try {
            ImageIO.write(image, "PNG", new File("Output/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
