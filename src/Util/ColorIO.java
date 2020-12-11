package Util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The ColorIO returns the sample RGB values of an image / section
 */
public class ColorIO {

    /**
     * Takes in an image, size and position
     * Iterates through each pixel and add the RGB values
     * Converts the RGB values to integers
     *
     * @param image    The image to be used
     * @param startPos The start position of the iteration-area
     * @param size     The area of the to-be-iterated pixels
     * @return Return a long[] with the sums of the RGB values
     */
    private static long[] GetSampleColorOfArea(BufferedImage image, Tuple<Integer, Integer> startPos, Tuple<Float, Float> size) {
        long r = 0, g = 0, b = 0;

        // Iterates through the columns
        for (int iW = 0; iW < image.getWidth(); iW++) {
            if (iW < startPos.x || iW >= startPos.x + size.x) continue;

            // Iterates through the rows
            for (int iH = 0; iH < image.getHeight(); iH++) {
                if (iH < startPos.y || iH >= startPos.y + size.y) continue;

                // Converts the RGB values from bits to ints
                int pixel = image.getRGB(iW, iH);
                r += (pixel >> 16) & 0xff;
                g += (pixel >> 8) & 0xff;
                b += (pixel) & 0xff;
            }
        }
        return new long[]{r, g, b};
    }


    /**
     * Takes in an image, start position and area
     * Compares the light-dark RGB values to the sampled RGB values
     * Return WHITE or BLACK as a Color
     *
     * @param image The background image
     * @param start The start position of the area
     * @param size  The size of the area
     * @return return WHITE or BLACK as a Color
     */
    public Color GetDefaultImageColor(BufferedImage image, Tuple<Integer, Integer> start, Tuple<Float, Float> size) {
        ColorIO cl = new ColorIO();
        long[] rgbValues = cl.GetSampleColorOfArea(image, start, size);

        // Converts the long RGB values to floats
        float[] cRGB = {rgbValues[0] / (size.x * size.y), rgbValues[1] / (size.x * size.y), rgbValues[2] / (size.x * size.y)};

        // Compares the RGB values
        if ((cRGB[0] < 180 && cRGB[1] < 100 && cRGB[2] >= 245) || (cRGB[0] < 115 && cRGB[1] < 115 && cRGB[2] < 190))
            return Color.WHITE;
        return Color.BLACK;
    }
}
