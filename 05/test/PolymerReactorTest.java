import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class PolymerReactorTest {

    @Test
    void solvePuzzle() throws FileNotFoundException {
        PolymerReactor polymerReactor = new PolymerReactor("input.txt");
        System.out.println("reduced polymer units' length= " + polymerReactor.reduceReactions());
        System.out.println("reduced polymer units' shortest possible length = "
                + polymerReactor.reduceReactionsWithUnitsOfOneTypeRemoved());
    }
}