package TextIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The SentenceCreator class creates sentences based on a SentenceStructure
 * The sentences are generated word by words, each being either part of a textClass or random
 */
public class SentenceCreator {
    BufferedReader bReader;

    /**
     * Create a sentence
     * Gets a random SentenceStructure from the list
     * Appends an appropriate word to the sentence
     * @return Returns a String that is the sentence
     */
    public String CreateSentence() {
        TextParser tParser = new TextParser();
        StringBuilder sentence = new StringBuilder();

        try {

            // Get a random SentenceStructure
            String[] wordStruct = GetRandomSentenceStructure();

            // Iterates through each of the structs
            for(String word : wordStruct) {
                if(word.startsWith("<W>:")) {
                    sentence.append(word.split(":")[1]);
                } else if(word.contains("::")) {
                    sentence.append(tParser.RandomWord(word.split("::")[1], word.split("::")[0]));
                } else {
                    sentence.append(tParser.RandomWord("None", word));
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
     * @return Returns a String that is the SentenceStructure
     * @throws IOException If an error occurs during the file parsing, it will be thrown
     */
    public String[] GetRandomSentenceStructure() throws IOException {
        ArrayList<String> wordStruct = new ArrayList<>();
        bReader = new BufferedReader(new FileReader("Resources/Text/SentenceStructure.list"));
        String line;

        // Iterate through the file
        while((line = bReader.readLine()) != null) {
            wordStruct.add(line);
        }

        // Return a random SentenceStructure from the ArrayList
        return wordStruct.get((int) (Math.random() * wordStruct.size() - 1)).split(", ");
    }
}
