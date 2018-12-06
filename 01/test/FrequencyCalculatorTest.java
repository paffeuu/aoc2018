import org.junit.jupiter.api.Test;

class FrequencyCalculatorTest {
    FrequencyCalculator frequencyCalculator;

    @Test
    void solvePuzzle() {
        frequencyCalculator = new FrequencyCalculator("puzzle01.txt");
        System.out.println("frequency = " + frequencyCalculator.calculateFrequency());
        System.out.println("first repeating frequency = " + frequencyCalculator.findFirstRepeatingFrequency());
    }

}