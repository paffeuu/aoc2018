import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RepetitionCounter {
    private List<String> lines;
    private final static int ASCII_a = 97;

    public RepetitionCounter(String fileDir) throws FileNotFoundException {
        readInputFromFile(fileDir);
    }

    public int countCheckSum() {
        int repeatedTwice = 0, repeatedThreeTimes = 0;
        for (String line : lines) {
            boolean isRepeatedTwice = false, isRepeatedThreeTimes = false;
            int[] repetitionsOfChars = new int[26];
            for (char ch : line.toCharArray()) {
                repetitionsOfChars[ch - ASCII_a]++;
            }
            for (int repetitionsOfChar : repetitionsOfChars) {
                if (repetitionsOfChar == 2 && !isRepeatedTwice) {
                    isRepeatedTwice = true;
                    repeatedTwice++;
                }
                if (repetitionsOfChar == 3 && !isRepeatedThreeTimes) {
                    isRepeatedThreeTimes = true;
                    repeatedThreeTimes++;
                }
                if (isRepeatedTwice && isRepeatedThreeTimes) {
                    break;
                }
            }
        }
        return repeatedTwice * repeatedThreeTimes;
    }

    public String findTwoStringDifferingByOneChar() {
        for (int i = 0; i < lines.size(); i++ ) {
            for (int j = i; j < lines.size(); j++) {
                int differences = 0;
                int positionOfDifference = -1;
                for (int k = 0; k < lines.get(i).length(); k++) {
                    if (lines.get(i).charAt(k) != lines.get(j).charAt(k)) {
                        differences++;
                        if (differences > 1) {
                            break;
                        }
                        positionOfDifference = k;
                    }
                }
                if (differences == 1) {
                    return lines.get(i).substring(0, positionOfDifference) + lines.get(i).substring(positionOfDifference+1);
                }
            }
        }
        return null;
    }

    private void readInputFromFile(String fileDir) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileDir));
        lines = new LinkedList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }
}
