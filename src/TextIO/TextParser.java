package TextIO;

import TextIO.TextClasses.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The TextParser class parses the dictionary
 * Returns a random word that is found in the dictionary
 */
public class TextParser {
    final String[] availableTypes = {"Nouns", "Verbs", "Adjectives", "Conjunctions", "LinkingVerbs"};
    BufferedReader bReader;

    /**
     * Takes in a String and Type
     * Returns a random word that is found in the dictionary
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc. If none, use .None
     * @param type The type of the word, i.e. Noun, Verb or Name
     * @return Returns a String that is a random word
     */
    public String RandomWord(String textClass, String type) {
        String randWord = "void";

        if(textClass.equals("None")) textClass = RandomTextClass(type);

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
    private List<String> ParseText(String textClass, String type) throws Exception {
        if(!Arrays.asList(availableTypes).contains(type)) throw new IllegalArgumentException("String type is not legal : Must be [Nouns], [Verbs] or [Name]");

        // Initialize Variables
        List<String> content = new ArrayList<String>();
        List<String> fContent = new ArrayList<String>();
        bReader = new BufferedReader(new FileReader("Resources/Text/" + type + ".list"));
        String line;

        // Iterates through each line, filters the one matching the textClass and returns it
        while((line = bReader.readLine()) != null && fContent.size() == 0)
            if(line.startsWith(textClass)) fContent = Arrays.asList(line.split(textClass + ":: ")[1].split(","));
        return fContent;
    }

    /**
     * Takes in a String
     * Create a new ArrayList and initializes the BufferedReader
     * Uses the BufferedReader to append all the TextClasses to the ArrayList
     * @param filetype The type of file to search
     * @return Returns a String that is one of the textClasses in the filetype
     */
    private String RandomTextClass(String filetype) {
        ArrayList<String> textClasses = new ArrayList<>();

        try {
            bReader = new BufferedReader(new FileReader("Resources/Text/" + filetype + ".list"));
            String line;

            // Iterates through each line of the file and adds the split lines;
            while((line = bReader.readLine()) != null) {
                textClasses.add(line.split(":: ")[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Returns a random TextClass
        return textClasses.get((int) (Math.random() * textClasses.size() - 1));
    }
}
