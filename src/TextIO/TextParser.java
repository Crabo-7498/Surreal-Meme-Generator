package TextIO;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The TextParser class parses the dictionary
 * Returns a random word that is found in the dictionary
 */
public class TextParser {
    final String[] availableTypes = {"Nouns", "Verbs"};

    /**
     * Takes in a TextClass and Type
     * Returns a random word that is found in the dictionary
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc. If none, use .None
     * @param type The type of the word, i.e. Noun, Verb or Name
     * @return Returns a String that is a random word
     */
    public String RandomWord(TextClasses textClass, String type) {
        String randWord = "void";

        // Check if there is a specified textClass
        if(textClass == TextClasses.None) textClass = TextClasses.values()[new Random().nextInt(TextClasses.values().length - 1)];

        try {

            // Get a list of the parse dictionary words
            List<String> lValues = ParseText(textClass, type);
            randWord = lValues.get(new Random().nextInt(lValues.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randWord;
    }

    /**
     * Takes in a TextClass and type
     * Reads the [type].list and appends it to an ArrayList
     * Returns the filteredList as an ArrayList of Strings
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc. If none, use .None
     * @param type The type of the word, i.e. Noun, Verb or Name
     * @return Returns an filtered ArrayList of Strings containing all the words in the [type].list
     * @throws Exception Throws and Error if the provided type does not exist
     */
    private List<String> ParseText(TextClasses textClass, String type) throws Exception {
        if(!Arrays.asList(availableTypes).contains(type)) throw new IllegalArgumentException("String type is not legal : Must be [Nouns], [Verbs] or [Name]");

        // Initialize Variables
        List<String> content = new ArrayList<String>();
        List<String> fContent = new ArrayList<String>();
        BufferedReader bReader = new BufferedReader(new FileReader("Resources/Text/" + type + ".list"));
        String line;

        // Iterates through each line, filters the one matching the textClass and returns it
        while((line = bReader.readLine()) != null && fContent.size() == 0)
            if(line.startsWith(textClass.toString())) fContent = Arrays.asList(line.split(textClass + ":: ")[1].split(","));
        return fContent;
    }
}
