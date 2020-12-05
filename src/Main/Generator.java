package Main;

import ResourceIO.*;
import TextIO.ConfigEditor;
import TextIO.SentenceCreator;
import TextIO.TextParser;
import Util.Tuple;
import java.awt.image.BufferedImage;

public class Generator {
    public void Generate() {
        ImageLoader imgLoad = new ImageLoader();
        BufferedImage bg = imgLoad.LoadImage("t5.jpg");
        ImageEditor imgEdit = new ImageEditor(bg);
        TextParser txtParser = new TextParser();
        SentenceCreator stnCreate = new SentenceCreator();

        try {
            ConfigEditor ce = new ConfigEditor();
            ce.WriteConfig();
            imgEdit.AddImage(imgLoad.LoadImage("t2.jpg"), new Tuple<Integer, Integer>(500, 500), new Tuple<Integer, Integer>(10, 10));
            imgEdit.AddImage(imgLoad.LoadImage("t6.jpg"), new Tuple<Integer, Integer>(500, 500), new Tuple<Integer, Integer>(70, 70));
            imgEdit.AddText(bg, stnCreate.CreateSentence(), new Tuple<String, Float>("mn.ttf", 60f), new Tuple<Integer, Integer>(100, 300));
            //imgEdit.AddText(bg, txtParser.RandomWord(null, "Nouns"), new Tuple<String, Float>("mn.ttf", 1000f), new Tuple<Integer, Integer>(500, 700));
            imgLoad.SaveImage("Export.png", bg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
