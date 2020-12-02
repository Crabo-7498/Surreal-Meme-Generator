package Main;

import ResourceIO.*;
import TextIO.SentenceCreator;
import TextIO.TextParser;
import Util.Tuple;
import java.awt.image.BufferedImage;

public class Generator {
    public void Generate() {
        ImageLoader imgLoad = new ImageLoader();
        BufferedImage bg = imgLoad.LoadImage("t5.jpg");
        ImageEditor imgEdit = new ImageEditor(bg);
        TextParser tParser = new TextParser();
        SentenceCreator sCreate = new SentenceCreator();

        try {
            imgEdit.AddImage(imgLoad.LoadImage("t2.jpg"), new Tuple<Integer, Integer>(500, 500), new Tuple<Integer, Integer>(10, 10));
            imgEdit.AddImage(imgLoad.LoadImage("t6.jpg"), new Tuple<Integer, Integer>(500, 500), new Tuple<Integer, Integer>(70, 70));
            imgEdit.AddText(bg, sCreate.CreateSentence(), new Tuple<String, Float>("mn.ttf", 60f), new Tuple<Integer, Integer>(100, 300));
            imgEdit.AddText(bg, tParser.RandomWord("None", "Nouns"), new Tuple<String, Float>("mn.ttf", 1000f), new Tuple<Integer, Integer>(500, 700));
            imgLoad.SaveImage("Export.png", bg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
