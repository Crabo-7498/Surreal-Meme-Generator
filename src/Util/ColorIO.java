package Util;

import java.awt.image.BufferedImage;

/**
 * The ColorIO returns the sample RGB values of an image / section
 */
public class ColorIO {
    long r, g, b = 0;

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
    public long[] GetSampleColorOfArea(BufferedImage image, Tuple<Integer, Integer> startPos, Tuple<Float, Float> size) {

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
}
