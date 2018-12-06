import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrequencyCalculator {
    private String fileDir;
    private List<String> lines;

    public FrequencyCalculator(String fileDir) {
        this.fileDir = fileDir;
    }

    public int calculateFrequency() {
        if (lines == null) {
            lines = readInputFromFile();
        }
        return getFrequenciesStreamFromLinesList(lines).sum();
    }

    public int findFirstRepeatingFrequency() {
        if (lines == null) {
            lines = readInputFromFile();
        }
        List<Integer> changesList = getFrequenciesStreamFromLinesList(lines).boxed().collect(Collectors.toList());
        Set<Integer> frequencies = new HashSet<>();

        int newFrequency = 0;
        while (true) {
            for (Integer change : changesList) {
                newFrequency += change;
                if (frequencies.contains(newFrequency)) {
                    return newFrequency;
                } else {
                    frequencies.add(newFrequency);
                }
            }
        }
    }

    private List<String> readInputFromFile() {
        List<String> lines = null;
        try {
            Scanner scanner = new Scanner(new File(fileDir));
            lines = new LinkedList<>();
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private IntStream getFrequenciesStreamFromLinesList(List<String> lines) {
        return lines.stream().mapToInt(Integer::parseInt);
    }
}