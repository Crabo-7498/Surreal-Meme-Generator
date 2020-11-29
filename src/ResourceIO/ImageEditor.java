package ResourceIO;

import Util.Tuple;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageEditor {
    private Graphics2D g2d = null;
    public ImageEditor(Graphics2D g2d) {
        this.g2d = g2d;
    }
    public void Edit() {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(10, 10, 50, 50);
    }

    public void AddImage(BufferedImage image, Tuple<Integer, Integer> size, Tuple<Integer, Integer> position) {
        g2d.drawImage(image, position.x, position.y, size.x, size.y, null);
    }

    public void AddText(String text, Tuple<String, Float> font, Tuple<Integer, Integer> position) {
        FontLoader fl = new FontLoader();
        g2d.setFont(fl.LoadFont(font.x).deriveFont(font.y));
        g2d.drawString(text, position.x, position.y);
    }
}
