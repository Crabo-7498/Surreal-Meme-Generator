package Main;

import ResourceIO.*;
import TextIO.TextClasses;
import TextIO.TextParser;
import Util.Tuple;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Generator {
    public void Generate() {
        ImageLoader imgLoad = new ImageLoader();
        BufferedImage image = imgLoad.LoadImage("t3.jpg");
        Graphics2D g2d = image.createGraphics();
        ImageEditor imgEdit = new ImageEditor(g2d);

        //imgLoad.GetDefaultImageColor(image, new Tuple<>(699, 0), new Tuple<>(1, 1));
        //imgEdit.AddImage(image, new Tuple<Integer, Integer>(0, 0), new Tuple<Integer, Integer>(10, 10));
        //imgEdit.AddText(image, "AAA", new Tuple<String, Float>("mn.ttf", 60f), new Tuple<Integer, Integer>(100, 300));
        //imgLoad.SaveImage("Export.png", image, new Tuple<Integer, Integer>(512, 512));

        TextParser tParser = new TextParser();
        try {
            tParser.ParseText(TextClasses.Fish, "Nouns");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
