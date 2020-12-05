package TextIO;

import TextIO.TextClasses.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The TextParser class parses the dictionary
 * Returns a random word that is found in the dictionary
 */
public class TextParser {
    final List<String> availableTypes = Arrays.asList("Nouns", "Verbs", "Adjectives", "Conjunctions", "LinkingVerbs");
    BufferedReader bReader;
    Random r = new Random();

    /**
     * Takes in a String and Type
     * Returns a random word that is found in the dictionary
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc. If none, use null
     * @param type The type of the word, i.e. Noun, Verb or Name
     * @return Returns a String that is a random word
     */
    public String RandomWord(String textClass, String type) {
        String randWord = null;
        List<String> lValues = null;
        if(textClass == null) textClass = RandomTextClass(type);

        try {
            // Get a list of the parse dictionary words
            lValues = ParseText(textClass, type);
            randWord = lValues.get(r.nextInt(lValues.size()));
        } catch (Exception e) {
            try {
                lValues = ParseText(RandomTextClass(type), type);
                randWord = lValues.get(r.nextInt(lValues.size()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return randWord;
    }

    /**
     * Takes in a TextClass and type
     * Reads the [type].list and appends it to an ArrayList
     * Returns the filteredList as an ArrayList of Strings
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc.
     * @param type The type of the word, i.e. Noun, Verb or Name
     * @return Returns an filtered ArrayList of Strings containing all the words in the [type].list
     * @throws Exception Throws and Error if the provided type does not exist
     */
    private List<String> ParseText(String textClass, String type) throws Exception {
        if(!availableTypes.contains(type)) throw new IllegalArgumentException("String type is not legal : Must be [Nouns], [Verbs] or [Name]");

        // Initialize Variables
        List<String> content = new ArrayList<>();
        bReader = new BufferedReader(new FileReader("Resources/Text/" + type + ".list"));
        String line;

        // Iterates through each line, filters the one matching the textClass and returns it
        while((line = bReader.readLine()) != null) {
            if(!line.startsWith(textClass)) continue;
            return Arrays.asList(line.split(textClass + ":: ")[1].split(","));
        }
        return null;
    }

    /**
     * Takes in a String
     * Create a new ArrayList and initializes the BufferedReader
     * Uses the BufferedReader to append all the TextClasses to the ArrayList
     * @param filetype The type of file to search
     * @return Returns a String that is one of the textClasses in the filetype
     */
    public String RandomTextClass(String filetype) {
        List<String> textClasses = new ArrayList<>();

        try {
            bReader = new BufferedReader(new FileReader("Resources/Text/" + filetype + ".list"));
            String line;

            // Iterates through each line of the file and adds the split lines;
            while((line = bReader.readLine()) != null) {
                if(line.isEmpty() || line.startsWith("//") || line.contains("!")) continue;
                textClasses.add(line.split(":: ")[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(textClasses.toString());

        // Returns a random TextClass
        return textClasses.get(r.nextInt(textClasses.size()));
    }
}
