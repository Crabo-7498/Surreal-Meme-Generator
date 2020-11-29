package Util;

import java.awt.image.BufferedImage;

public class ColorIO {
    long r, g, b = 0;
    public long[] GetSampleColorOfArea(BufferedImage image, Tuple<Integer, Integer> startPos, Tuple<Float, Float> size) {
        for(int iW = 0; iW < image.getWidth(); iW++) {
            if(iW < startPos.x || iW >= startPos.x + size.x ) continue;

            for(int iH = 0; iH < image.getHeight(); iH++) {
                if(iH < startPos.y || iH >= startPos.y + size.y) continue;

                int pixel = image.getRGB(iW, iH);
                r += (pixel >> 16) & 0xff;
                g += (pixel >> 8) & 0xff;
                b += (pixel) & 0xff;
            }
        }
        return new long[]{r, g, b};
    }
}
