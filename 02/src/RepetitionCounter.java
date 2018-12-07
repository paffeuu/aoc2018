import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RepetitionCounter {
    private List<String> lines;

    public RepetitionCounter(String fileDir) {

    }

    private void readInputFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileDir));
        lines = new LinkedList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }
}
