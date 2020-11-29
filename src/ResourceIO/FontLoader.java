package ResourceIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {
    public Font LoadFont(String name) {
        Font cFont = null;
        try {
            cFont = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Fonts/" + name));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return cFont;
    }
}
