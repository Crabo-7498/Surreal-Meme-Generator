package TextIO;

import java.io.*;
import java.util.Random;

/**
 * The ConfigEditor Class Edits and parses the config.data file
 */
public class ConfigEditor {

    /**
     * Writes a random Subject and MVerb to the config.data
     */
    public void WriteConfig() {
        TextParser tParser = new TextParser();

        try {
            PrintWriter prtWrite = new PrintWriter(new File("Resources/Data/config.data"));
            String subject = tParser.RandomTextClass("Nouns");

            // Clear all text and write to the file
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

    /**
     * Takes in a String config and returns that config
     * @param config a config
     * @return returns the value of the config
     */
    public String GetConfig(String config) {
        String configLine = null;

        try {
            BufferedReader bfdRead = new BufferedReader(new FileReader("Resources/Data/config.data"));
            String line;

            // Iterate through each line to find the config
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
