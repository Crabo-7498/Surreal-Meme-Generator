package ResourceIO;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The FileIO class finds and returns files in a folder
 */
public class FileIO {
    private final Random r = new Random();
    private final FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {

            // Returns true if the file ends with .png and is not a .DS_Store file (MacOS only)
            return pathname.getName().endsWith(".png") && !pathname.getName().contains(".DS_Store");
        }
    };

    /**
     * Takes in a String path and turns it into a File
     * Iterates through the folder and filters them
     * Returns the filtered files with modifications
     *
     * @param path Takes in a String path that is the folder to be searched
     * @return Returns a List of files
     */
    public List<String> GetAllFiles(String path) {
        File folder = new File(path);
        ArrayList<String> files = new ArrayList<>();

        // Returns an Mapped Stream
        return Arrays.stream(Objects.requireNonNull(folder.listFiles(filter))).map(c -> {

            // Splits the directory
            String[] directories = c.getName().split("/");

            // Returns the last item in the split directory
            return directories[directories.length - 1];
        }).collect(Collectors.toList());
    }

    /**
     * Uses GetAllFiles() to get all files from the path
     * Returns a random files inside the List
     *
     * @param path Takes in a String path that is the folder to be searched
     * @return Returns a random file
     */
    public String GetRandomFile(String path) {
        List<String> files = this.GetAllFiles(path);
        return files.get(r.nextInt(files.size()));
    }
}
