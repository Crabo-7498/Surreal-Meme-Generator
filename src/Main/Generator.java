package Main;

import ResourceIO.FileIO;
import ResourceIO.ImageEditor;
import ResourceIO.ImageLoader;
import TextIO.ConfigEditor;
import TextIO.SentenceCreator;
import TextIO.TextParser;
import Util.PositionIO;
import Util.Tuple;

import java.awt.image.BufferedImage;

public class Generator {
    public void Generate() {
        ImageLoader imgLoad = new ImageLoader();
        BufferedImage bg = imgLoad.LoadImage("Test/t5.jpg");
        ImageEditor imgEdit = new ImageEditor(bg);
        TextParser txtParser = new TextParser();
        SentenceCreator stnCreate = new SentenceCreator();

        try {
            ConfigEditor cfgEditor = new ConfigEditor();
            FileIO fIO = new FileIO();
            cfgEditor.WriteConfig();

            //System.out.println(fIO.GetRandomFile("Resources/Images/Surreal"));
            BufferedImage loadedImage = imgLoad.LoadImage("Surreal/" + fIO.GetRandomFile("Resources/Images/Surreal"));
            Tuple<Integer, Integer> size = new Tuple<Integer, Integer>(500, 500);
            imgEdit.AddImage(loadedImage, size, PositionIO.GetRandomPositionForImage(new Tuple<>(bg.getWidth() - size.x, bg.getHeight() - size.y)));

            //imgEdit.AddImage(imgLoad.LoadImage("Test/t6.jpg"), new Tuple<Integer, Integer>(500, 500), new Tuple<Integer, Integer>(70, 70));
            //imgEdit.AddText(bg, stnCreate.CreateSentence(), new Tuple<String, Float>("mn.ttf", 60f), new Tuple<Integer, Integer>(100, 300));
            //imgEdit.AddText(bg, txtParser.RandomWord(null, "Nouns"), new Tuple<String, Float>("mn.ttf", 1000f), new Tuple<Integer, Integer>(500, 700));
            imgLoad.SaveImage("Export.png", bg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
