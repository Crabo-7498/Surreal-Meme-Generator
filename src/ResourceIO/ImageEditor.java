package ResourceIO;

import Util.Tuple;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageEditor {
    private Graphics2D g2d = null;
    private Tuple<Integer, Integer> bgDimensions;
    private ImageLoader imgLoad;

    public ImageEditor(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void AddImage(BufferedImage image, Tuple<Integer, Integer> size, Tuple<Integer, Integer> position) {
        g2d.drawImage(image, position.x, position.y, size.x, size.y, null);
    }

    public void AddText(BufferedImage bg, String text, Tuple<String, Float> font, Tuple<Integer, Integer> position) {
        imgLoad = new ImageLoader();
        FontLoader fl = new FontLoader();
        g2d.setColor(imgLoad.GetDefaultImageColor(bg, position, new Tuple<Float, Float>(text.length() * font.y / 1.8f, font.y)));
        DrawStringWithResizedText(text, bgDimensions.x / 10, font.y, position, fl.LoadFont(font.x));
    }

    private void DrawStringWithResizedText(String text, int padding, float currentSize, Tuple<Integer, Integer> position, Font font) {
        g2d.setFont(font.deriveFont(currentSize));
        while(g2d.getFontMetrics().stringWidth(text) + position.x > bgDimensions.x - (padding * 2)) {
            currentSize -= currentSize / 5;
            g2d.setFont(font.deriveFont(currentSize));
        }

        g2d.drawString(text, position.x, position.y);
    }

    /* Get - Set */

    public void SetBackgroundSize(Tuple<Integer, Integer> size) { bgDimensions = size; }
}
