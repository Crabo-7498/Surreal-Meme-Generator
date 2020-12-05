package ResourceIO;

import Util.Tuple;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The ImageEditor class manipulates the base image
 * Each method creates and disposes of a graphics component attached to the base image
 */
public class ImageEditor {
    private FontLoader fl = new FontLoader();
    private Graphics2D g2d = null;
    private BufferedImage bg;
    private ImageLoader imgLoad;

    public ImageEditor(BufferedImage bg) {
        this.bg = bg;
    }

    /**
     * Takes in a new image, size and position
     * Draws the new image to the base image
     * @param image - The new image to be drawn
     * @param size - The size of the new image
     * @param position - The position of the new image
     */
    public void AddImage(BufferedImage image, Tuple<Integer, Integer> size, Tuple<Integer, Integer> position) {
        g2d = bg.createGraphics();
        g2d.drawImage(image, position.x, position.y, size.x, size.y, null);
        g2d.dispose();
    }

    /**
     * Takes in a String and Position
     * Sets color to contrast background
     * Draws text to the base image
     * @param bg The background image
     * @param text The Text to be drawn
     * @param font The Font to be used
     * @param position The position of where the text will be drawn
     */
    public void AddText(BufferedImage bg, String text, Tuple<String, Float> font, Tuple<Integer, Integer> position) {
        g2d = bg.createGraphics();

        // Creates a new ImageLoader and FontLoader
        imgLoad = new ImageLoader();


        // Sets Graphics color to contrast the background
        g2d.setColor(imgLoad.GetDefaultImageColor(bg, position, new Tuple<Float, Float>((float) g2d.getFontMetrics().stringWidth(text), font.y)));
        DrawStringWithResizedText(text, bg.getWidth() / 10, font.y, position, fl.LoadFont(font.x));

        // Dispose of the Graphics
        g2d.dispose();
    }

    /**
     * Takes in text and boundary config
     * Resizes text to fit within boundary
     * Draws the text to base image
     * @param text The text to be added
     * @param padding The padding (width * 2) of the boundary
     * @param currentSize The current/default size of the font
     * @param position The position of where the text should be drawn
     * @param font The font of the text
     */
    private void DrawStringWithResizedText(String text, int padding, float currentSize, Tuple<Integer, Integer> position, Font font) {
        g2d.setFont(font.deriveFont(currentSize));

        // Loops though sizes until text fits horizontally
        while(g2d.getFontMetrics().stringWidth(text) + position.x > bg.getWidth() - (padding * 2)) {
            currentSize -= currentSize / 5;
            g2d.setFont(font.deriveFont(currentSize));
        }

        // Draws text to base image
        g2d.drawString(text, position.x, position.y);
    }
}
