package TextIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextParser {
    private File nouns = null;
    String[] availableTypes = {"Nouns", "Verbs"};

    public void ParseText(TextClasses textClass, String type) throws Exception {
        if(!Arrays.asList(availableTypes).contains(type)) throw new IllegalArgumentException("String type is not legal : Must be [Nouns], [Verbs] or [Name]");

        List<String> content = new ArrayList<String>();
        BufferedReader bReader = new BufferedReader(new FileReader("Resources/Text/" + type + ".list"));
        String line;

        while((line = bReader.readLine()) != null) {
            content.add(line);
        }

        //List<String> fContent = new ArrayList<String>();

        //content.stream().filter(c -> c.split(":: ")[0] == textClass.toString()).getClass();

        //System.out.println(content.stream().filter(c -> c.split(":: ")[0] == textClass.toString()).collect(Collectors.toList())    );
    }
}
