package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
    public static List<String> readAllLinesFromFile(String fileDir) throws FileNotFoundException {
        List<String> lines = new LinkedList<>();
        try (Scanner scanner = new Scanner(new File(fileDir))) {
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return !lines.isEmpty() ? lines : null;
    }

    public static String readOneLineFromFile(String fileDir) throws FileNotFoundException {
        String line = null;
        try (Scanner scanner = new Scanner(new File(fileDir))) {
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
        }
        return line;
    }
}
