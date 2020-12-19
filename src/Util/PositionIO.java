package Util;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * The PositionIO class generates random positions for items
 */
public class PositionIO {
    static Random r = new Random();

    /**
     * Generates a random position for an Image inside Bounds
     *
     * @param bounds The bounds of the background
     * @return Returns a random position x,y in the form of a Tuple
     */
    public static Tuple<Integer, Integer> GetRandomPositionForImage(Tuple<Integer, Integer> bounds) {
        return new Tuple<>(
                r.nextInt(Math.max(bounds.x, 1)),
                r.nextInt(Math.max(bounds.y, 1))
        );
    }

    /**
     * Generates a random position for Text inside bounds
     *
     * @param bounds The bounds of the background
     * @return Returns a random position x,y in the form of a Tuple
     */
    public static Tuple<Integer, Integer> GetRandomPositionForText(Tuple<Integer, Integer> bounds) {
        return new Tuple<>(
                r.nextInt(Math.min(Math.max(bounds.x, bounds.x / 10), bounds.x / 2)),
                r.nextInt((int) Math.min(Math.max(bounds.y, bounds.y / 10), bounds.y * 0.9f))
        );
    }
}
