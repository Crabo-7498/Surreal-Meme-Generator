package TextIO;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TextParser {
    String[] availableTypes = {"Nouns", "Verbs"};

    public String RandomWord(TextClasses textClass, String type) {
        String randWord = "void";

        if(textClass == TextClasses.None) textClass = TextClasses.values()[new Random().nextInt(TextClasses.values().length - 1)];
        //System.out.println(textClass);

        try {
            List<String> lValues = ParseText(textClass, type);
            randWord = lValues.get(new Random().nextInt(lValues.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randWord;
    }

    private List<String> ParseText(TextClasses textClass, String type) throws Exception {
        if(!Arrays.asList(availableTypes).contains(type)) throw new IllegalArgumentException("String type is not legal : Must be [Nouns], [Verbs] or [Name]");

        List<String> content = new ArrayList<String>();
        List<String> fContent = new ArrayList<String>();
        BufferedReader bReader = new BufferedReader(new FileReader("Resources/Text/" + type + ".list"));
        String line;

        while((line = bReader.readLine()) != null && fContent.size() == 0)
            if(line.startsWith(textClass.toString())) fContent = Arrays.asList(line.split(textClass + ":: ")[1].split(","));
        return fContent;
    }
}
