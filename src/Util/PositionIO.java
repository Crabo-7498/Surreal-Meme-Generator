package Util;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The PositionIO class generates random positions for items
 */
public class PositionIO {
    Random r = new Random();

    /**
     * Generates a random position for an Image inside Bounds
     * @param image The image to be generated
     * @param bounds The bounds of the background
     * @return Returns a random position x,y in the form of a Tuple
     */
    public Tuple<Integer, Integer> GetRandomPositionForImage(BufferedImage image, Tuple<Integer, Integer> bounds) {
        return new Tuple<>(
                r.nextInt(Math.max(bounds.x - image.getWidth(), 0)),
                r.nextInt(Math.max(bounds.y - image.getHeight(), 0))
        );
    }
}
