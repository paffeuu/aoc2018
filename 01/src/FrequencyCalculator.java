import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrequencyCalculator {
    private List<String> lines;

    public FrequencyCalculator(String fileDir) throws FileNotFoundException {
        readInputFromFile(fileDir);
    }

    public int calculateFrequency() {
        return getFrequenciesStreamFromLinesList(lines).sum();
    }

    public int findFirstRepeatingFrequency() {
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

    private void readInputFromFile(String fileDir) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(fileDir));
        lines = new LinkedList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }

    private IntStream getFrequenciesStreamFromLinesList(List<String> lines) {
        return lines.stream().mapToInt(Integer::parseInt);
    }
}
