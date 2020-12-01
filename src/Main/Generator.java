package Main;

import ResourceIO.*;
import TextIO.TextClasses;
import TextIO.TextParser;
import Util.Tuple;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Generator {
    public void Generate() {
        /* Init */
        ImageLoader imgLoad = new ImageLoader();
        BufferedImage bg = imgLoad.LoadImage("t5.jpg");
        BufferedImage image;
        Graphics2D g2d = bg.createGraphics();
        ImageEditor imgEdit = new ImageEditor(g2d);
        TextParser tParser = new TextParser();

        imgEdit.SetBackgroundSize(new Tuple<>(bg.getWidth(), bg.getHeight()));

        /* Editing */
        try {
            imgEdit.AddImage(imgLoad.LoadImage("t2.jpg"), new Tuple<Integer, Integer>(500, 500), new Tuple<Integer, Integer>(10, 10));
            imgEdit.AddText(bg, tParser.RandomWord(TextClasses.None, "Nouns"), new Tuple<String, Float>("mn.ttf", 60f), new Tuple<Integer, Integer>(100, 300));
            imgLoad.SaveImage("Export.png", bg, new Tuple<Integer, Integer>(512, 512));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
