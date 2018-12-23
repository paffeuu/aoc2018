import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class CoordinatesAnalyzerTest {

    @Test
    public void solvePuzzle() throws FileNotFoundException {
        CoordinatesAnalyzer analyzer = new CoordinatesAnalyzer("input.txt");
        System.out.println("Size of largest not infinite area = " + analyzer.findSizeOfLargestNotInfiniteArea());
        System.out.println("Size of region containing all locations with sum of distances to coords under 10000  = " +
                analyzer.findSizeOfRegionContainingLocationUnder10000(10000));
    }

}