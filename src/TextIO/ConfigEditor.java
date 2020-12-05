package TextIO;

import java.io.*;
import java.util.Random;

public class ConfigEditor {
    public void WriteConfig() {
        TextParser tParser = new TextParser();

        try {
            PrintWriter pr = new PrintWriter(new File("Resources/Data/config.data"));
            String subject = tParser.RandomTextClass("Nouns");

            pr.write("Subject:: " + subject + "\n");
            if(new Random().nextFloat() > 0) {
                pr.write("MVerb:: " + tParser.RandomWord(subject + "!", "Verbs"));
            } else {
                pr.write("MVerb:: " + tParser.RandomWord(null, "Verbs"));
            }
            pr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String GetConfig(String config) {
        String configLine = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Resources/Data/config.data"));
            String line;

            while((line = br.readLine()) != null) {
                String[] sLine = line.split(":: ");
                if(sLine[0].equals(config)) configLine = sLine[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configLine;
    }
}
