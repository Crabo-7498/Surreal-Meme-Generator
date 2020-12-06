package TextIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The TextParser class parses the dictionary
 * Returns a random word that is found in the dictionary
 */
public class TextParser {
    final HashSet<String> availableTypes = new HashSet<String>(Arrays.asList("Nouns", "Verbs", "Adjectives", "Conjunctions", "LinkingVerbs"));
    BufferedReader bfdRead;
    Random r = new Random();

    /**
     * Takes in a String and Type
     * Returns a random word that is found in the dictionary
     *
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc. If none, use null
     * @param type      The type of the word, i.e. Noun, Verb or Name
     * @return Returns a String that is a random word
     */
    public String RandomWord(String textClass, String type) {
        String randWord = null;
        List<String> lValues;
        if (textClass == null) textClass = RandomTextClass(type);

        // Get a list of the parse dictionary words
        try {
            lValues = ParseText(textClass, type);
            if(lValues == null) lValues = ParseText(RandomTextClass(type), type);
            randWord = lValues.get(r.nextInt(lValues.size()));
            return randWord;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    final Map<String, List<String>> cachedParsedText = new HashMap<>();

    /**
     * Takes in a TextClass and type
     * Checks the cached text and if it exists, returns it
     * Otherwise reads the [type].list and appends it to an ArrayList
     * Returns the filteredList as an ArrayList of Strings
     *
     * @param textClass The class of an object, e.g. Furniture, Animal, Metal, etc.
     * @param type      The type of the word, i.e. Noun, Verb or Name
     * @return Returns an filtered ArrayList of Strings containing all the words in the [type].list
     * @throws Exception Throws and Error if the provided type does not exist
     */
    private List<String> ParseText(String textClass, String type) throws Exception {
        if (!availableTypes.contains(type))
            throw new IllegalArgumentException("String type is not legal : Must be [Nouns], [Verbs] or [Name]");

        if (cachedParsedText.get(textClass + type) != null) return cachedParsedText.get(textClass + type);

        // Initialize Variables
        List<String> content = new ArrayList<>();
        bfdRead = new BufferedReader(new FileReader("Resources/Text/" + type + ".list"));
        String line;

        // Iterates through each line, filters the one matching the textClass and returns it
        while ((line = bfdRead.readLine()) != null) {
            if (!line.startsWith(textClass)) continue;
            List<String> fLine = Arrays.asList(line.split(textClass + ":: ")[1].split(","));
            cachedParsedText.put(textClass + type, fLine);
            return fLine;
        }
        return null;
    }

    /**
     * Takes in a String
     * Create a new ArrayList and initializes the BufferedReader
     * Uses the BufferedReader to append all the TextClasses to the ArrayList
     *
     * @param filetype The type of file to search
     * @return Returns a String that is one of the textClasses in the filetype
     */
    public String RandomTextClass(String filetype) {
        List<String> textClasses = new ArrayList<>();

        try {
            bfdRead = new BufferedReader(new FileReader("Resources/Text/" + filetype + ".list"));
            String line;

            // Iterates through each line of the file and adds the split lines;
            while ((line = bfdRead.readLine()) != null) {
                if (line.isEmpty() || line.startsWith("//") || line.contains("!")) continue;
                textClasses.add(line.split(":: ")[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Returns a random TextClass
        return textClasses.get(r.nextInt(textClasses.size()));
    }
}
