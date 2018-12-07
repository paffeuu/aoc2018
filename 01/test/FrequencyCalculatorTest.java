import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class FrequencyCalculatorTest {
    FrequencyCalculator frequencyCalculator;

    @Test
    void solvePuzzle() throws FileNotFoundException {

        frequencyCalculator = new FrequencyCalculator("puzzle01.txt");
        System.out.println("frequency = " + frequencyCalculator.calculateFrequency());
        System.out.println("first repeating frequency = " + frequencyCalculator.findFirstRepeatingFrequency());
    }

}