import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class RepetitionCounterTest {
    RepetitionCounter repetitionCounter;

    @Test
    void solvePuzzle() throws FileNotFoundException {
        repetitionCounter = new RepetitionCounter("input.txt");
        System.out.println("cheksum = " + repetitionCounter.countCheckSum());
        System.out.println("common part of two strings differing by one char = "
                + repetitionCounter.findTwoStringDifferingByOneChar());
    }
}