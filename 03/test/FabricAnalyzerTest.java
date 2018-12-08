import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FabricAnalyzerTest {

    @Test
    void solvePuzzle() throws FileNotFoundException {
        FabricAnalyzer fabricAnalyzer = new FabricAnalyzer("input.txt", 1000);
        System.out.println("overlapped squares = " + fabricAnalyzer.countOverlappedSquares());
        System.out.println("intact square ID = " + fabricAnalyzer.findIntactClaim().getId());
    }
}