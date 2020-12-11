package TextIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The SentenceCreator class creates sentences based on a SentenceStructure
 * The sentences are generated word by words, each being either part of a textClass or random
 */
public class SentenceCreator {
    BufferedReader bfdRead;
    final Random r = new Random();

    /**
     * Create a sentence
     * Gets a random SentenceStructure from the list
     * Appends an appropriate word to the sentence
     *
     * @return Returns a String that is the sentence
     */
    public String CreateSentence() {
        TextParser txtParser = new TextParser();
        StringBuilder sentence = new StringBuilder();
        ConfigEditor cfgEditor = new ConfigEditor();

        try {

            // Get a random SentenceStructure
            String[] wordStruct = GetRandomSentenceStructure();

            // Iterates through each of the structs
            for (String word : wordStruct) {
                if (word.equals("<Subject>")) {
                    sentence.append(txtParser.RandomWord(cfgEditor.GetConfig("Subject"), "Nouns"));
                } else if (word.equals("<MVerb>")) {
                    sentence.append(cfgEditor.GetConfig("MVerb"));
                } else if (word.startsWith("<!W>::")) {
                    sentence.append(word.split("::")[1]);
                } else if (word.startsWith("<!S>::")) {
                    sentence.append(txtParser.RandomWord(word.split("::")[1], word.split("::")[2]));
                } else if (word.startsWith("<?O>")) {
                    if (new Random().nextFloat() > 0.5) sentence.append(txtParser.RandomWord(null, word.split("::")[1]));
                } else if (word.startsWith("<$")) {
                    sentence.deleteCharAt(sentence.length() - 1).append(word.charAt(2));
                } else {
                    sentence.append(txtParser.RandomWord(null, word));
                }
                sentence.append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentence.toString();
    }

    /**
     * Gets a random SentenceStructure
     * Creates a BufferedReader and uses it to get a random structure from the list
     *
     * @return Returns a String that is the SentenceStructure
     * @throws IOException If an error occurs during the file parsing, it will be thrown
     */
    public String[] GetRandomSentenceStructure() throws IOException {
        ArrayList<String> wordStruct = new ArrayList<>();
        bfdRead = new BufferedReader(new FileReader("Resources/Text/SentenceStructure.list"));
        String line;

        // Iterate through the file
        while ((line = bfdRead.readLine()) != null) {
            if (line.startsWith("//") || line.isEmpty()) continue;
            wordStruct.add(line);
        }

        //System.out.println(r.nextInt(wordStruct.size()));
        // Return a random SentenceStructure from the ArrayList
        return wordStruct.get(r.nextInt(wordStruct.size())).split(", ");
    }
}
