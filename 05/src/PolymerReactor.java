import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class PolymerReactor {
    private String input;
    private List<Character> chars;

    public PolymerReactor(String fileDir) throws FileNotFoundException {
        readInputFromFile(fileDir);
    }

    public int reduceReactions() {
        getCharListFromString();
        return reduceReactions(chars);
    }

    public int reduceReactionsWithUnitsOfOneTypeRemoved() {
        getCharListFromString();
        List<List<Character>> charsListWithoutOneType = new ArrayList<>();
        IntStream.range(97, 124).forEach(i -> charsListWithoutOneType.add(removeOneTypeOfChars((char)i)));
        return charsListWithoutOneType.stream().mapToInt(this::reduceReactions).min().getAsInt();
    }

    private int reduceReactions(List<Character> charList) {
        ListIterator<Character> listIterator = charList.listIterator();
        while (listIterator.hasNext()) {
            Character next = listIterator.next();
            if (listIterator.hasNext() && Math.abs(next - listIterator.next()) == 32) {
                listIterator.remove();
                listIterator.previous();
                listIterator.remove();
            }
            if (listIterator.hasPrevious() && listIterator.hasNext()) {
                listIterator.previous();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        charList.forEach(stringBuilder::append);
        return stringBuilder.toString().length();
    }

    private void getCharListFromString() {
        chars = new LinkedList<>();
        for (char c : input.toCharArray()) {
            chars.add(c);
        }
    }

    private List<Character> removeOneTypeOfChars(char ch) {
        List<Character> charsWithoutOneType = new LinkedList<>(chars);
        charsWithoutOneType.removeIf(currCh -> currCh == ch || currCh == Character.toUpperCase(ch));
        return charsWithoutOneType;
    }

    private void readInputFromFile(String fileDir) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileDir));
        input = scanner.nextLine();
        scanner.close();
    }
}
