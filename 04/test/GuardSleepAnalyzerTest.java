import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class GuardSleepAnalyzerTest {
    @Test
    void solvePuzzle() throws FileNotFoundException {
        GuardSleepAnalyzer guardSleepAnalyzer = new GuardSleepAnalyzer("input.txt");
        int[] strategy1 = guardSleepAnalyzer.findGuardAndMinuteWhenHeOftenSleeps();
        System.out.println("guardId = " + strategy1[0] + " minute = " + strategy1[1] + " and product = " + strategy1[0] * strategy1[1]);
        int[] strategy2 = guardSleepAnalyzer.findGuardWhoMostOftenSleepOnTheSameMinute();
        System.out.println("guardId = " + strategy2[0] + " minute = " + strategy2[1] + " and product = " + strategy2[0] * strategy2[1]);
    }

}