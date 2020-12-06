package ResourceIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * The FontLoader Class store and load fonts
 */
public class FontLoader {
    private final HashMap<String, Font> cachedFonts = new HashMap<String, Font>();

    /**
     * Takes in a String name
     * Checks if the font already exists on cache and returns it
     * Otherwise create the fond and add it to cache
     *
     * @param name The name of the font
     * @return The loaded font
     */
    public Font LoadFont(String name) {
        try {

            // Checks if the font is in cache
            if (cachedFonts.get(name) == null) {
                Font nFont = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Fonts/" + name));
                cachedFonts.put(name, nFont);
                return nFont;
            }

            // Returns the cached font
            return cachedFonts.get(name);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
