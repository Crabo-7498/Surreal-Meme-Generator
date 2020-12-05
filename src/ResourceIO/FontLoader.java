package ResourceIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FontLoader {
    private final HashMap<String, Font> fonts = new HashMap<String, Font>();

    public Font LoadFont(String name) {
        try {
            if(fonts.get(name) == null) {
                Font nFont = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Fonts/" + name));
                fonts.put(name, nFont);
                return nFont;
            }

            return fonts.get(name);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
