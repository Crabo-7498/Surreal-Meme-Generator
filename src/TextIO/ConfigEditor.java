package TextIO;

import java.io.*;

public class ConfigEditor {
    public void WriteConfig() {
        TextParser tParser = new TextParser();

        try {
            PrintWriter pr = new PrintWriter(new File("Resources/Data/config.data"));
            String subject = tParser.RandomTextClass("Nouns");

            pr.write("Subject:: " + subject + "\n");
            if(Math.random() > 0) {
                pr.write("MVerb:: " + tParser.RandomWord(subject + "!", "Verbs"));
            } else {
                pr.write("MVerb:: " + tParser.RandomWord("None", "Verbs"));
            }
            pr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String GetConfig(String config) {
        String configLine = "None";

        try {
            BufferedReader br = new BufferedReader(new FileReader("Resources/Data/config.data"));
            String line;

            while((line = br.readLine()) != null) {
                if(line.split(":: ")[0].equals(config)) configLine = line.split(":: ")[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return configLine;
    }
}
