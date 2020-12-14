package Util;

import java.awt.image.BufferedImage;
import java.util.Random;

public class PositionIO {
    Random r = new Random();

    public Tuple<Integer, Integer> GetRandomPosition(BufferedImage image, Tuple<Integer, Integer> bounds) {
        return new Tuple<>(
                r.nextInt(Math.max(bounds.x - image.getWidth(), 0)),
                r.nextInt(Math.max(bounds.y - image.getHeight(), 0))
        );
    }
}
