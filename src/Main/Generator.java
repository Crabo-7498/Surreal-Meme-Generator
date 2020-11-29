package Main;

import ResourceIO.*;
import Util.Tuple;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Generator {
    public void Generate() {
        ImageLoader imgLoad = new ImageLoader();
        BufferedImage image = imgLoad.LoadImage("t.jpg");
        Graphics2D g2d = image.createGraphics();
        ImageEditor imgEdit = new ImageEditor(g2d);

        imgEdit.AddImage(image, new Tuple<Integer, Integer>(50, 50), new Tuple<Integer, Integer>(10, 10));
        imgEdit.AddText("The funne froge", new Tuple<String, Float>("mn.ttf", 60f), new Tuple<Integer, Integer>(200, 200));
        imgLoad.SaveImage("Export.png", image, new Tuple<Integer, Integer>(512, 512));
    }
}
