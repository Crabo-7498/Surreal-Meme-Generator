package TextIO;

import java.io.*;
import java.util.Random;

public class ConfigEditor {
    public void WriteConfig() {
        TextParser tParser = new TextParser();

        try {
            PrintWriter prtWrite = new PrintWriter(new File("Resources/Data/config.data"));
            String subject = tParser.RandomTextClass("Nouns");

            prtWrite.write("Subject:: " + subject + "\n");
            if (new Random().nextFloat() > 0) {
                prtWrite.write("MVerb:: " + tParser.RandomWord(subject + "!", "Verbs"));
            } else {
                prtWrite.write("MVerb:: " + tParser.RandomWord(null, "Verbs"));
            }
            prtWrite.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String GetConfig(String config) {
        String configLine = null;

        try {
            BufferedReader bfdRead = new BufferedReader(new FileReader("Resources/Data/config.data"));
            String line;

            while ((line = bfdRead.readLine()) != null) {
                String[] sLine = line.split(":: ");
                if (sLine[0].equals(config)) configLine = sLine[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configLine;
    }
}
