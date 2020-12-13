package ResourceIO;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.stream.Collectors;

public class FileIO {
    private final Random r = new Random();
    private final FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".png") && !pathname.getName().contains(".DS_Store");
        }
    };

    public List<String> GetAllFiles(String path) {
        File folder = new File(path);

        return Arrays.stream(Objects.requireNonNull(folder.listFiles(filter))).map(c -> {
            String[] directories = c.getName().split("/");
            return directories[directories.length - 1];
        }).collect(Collectors.toList());
    }

    public String GetRandomFile(String path) {
        List<String> files = this.GetAllFiles(path);
        return files.get(r.nextInt(files.size()));
    }
}
