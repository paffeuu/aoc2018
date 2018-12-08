import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FabricAnalyzer {
    private int fabricSize;
    private List<String> lines;
    private int[][] fabricMap;
    private Set<Claim> claimSet;

    public FabricAnalyzer(String fileDir, int size) throws FileNotFoundException {
        this.fabricSize = size;
        readInputFromFile(fileDir);
        clean();
    }

    public int countOverlappedSquares() {
        lines.forEach(this::analyzeClaim);
        claimSet.forEach(this::markClaimOnMap);
        int counter = 0;
        for (int[] col : fabricMap) {
            for (int elem : col) {
                if (elem > 1) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public Claim findIntactClaim() {
        clean();
        lines.forEach(this::analyzeClaim);
        claimSet.forEach(this::markClaimOnMap);
        for (Claim claim : claimSet) {
            boolean allNotOverlapped = true;
            for (int i = claim.getInchesFromTop(); i < claim.getInchesFromTop() + claim.getHeight(); i++) {
                for (int j = claim.getInchesFromLeft(); j < claim.getInchesFromLeft() + claim.getWidth(); j++) {
                    if (fabricMap[i][j] != 1) {
                        allNotOverlapped = false;
                        break;
                    }
                }
                if (!allNotOverlapped) {
                    break;
                }
            }
            if (allNotOverlapped) {
                return claim;
            }
        }
        return null;
    }

    private void analyzeClaim(String line) {
        Claim newClaim = new Claim();
        String[] temp = line.substring(1).split("@");
        newClaim.setId(Integer.parseInt(temp[0].trim()));
        temp = temp[1].split(",");
        newClaim.setInchesFromLeft(Integer.parseInt(temp[0].trim()));
        temp = temp[1].split(":");
        newClaim.setInchesFromTop(Integer.parseInt(temp[0].trim()));
        temp = temp[1].split("x");
        newClaim.setWidth(Integer.parseInt(temp[0].trim()));
        newClaim.setHeight(Integer.parseInt(temp[1].trim()));
        claimSet.add(newClaim);
    }

    private void markClaimOnMap(Claim claim) {
        for (int i = claim.getInchesFromTop(); i < claim.getInchesFromTop() + claim.getHeight(); i++) {
            for (int j = claim.getInchesFromLeft(); j < claim.getInchesFromLeft() + claim.getWidth(); j++) {
                fabricMap[i][j]++;
            }
        }
    }

    private void readInputFromFile(String fileDir) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileDir));
        lines = new LinkedList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }

    private void clean() {
        fabricMap = new int[fabricSize][fabricSize];
        claimSet = new HashSet<>();
    }
}
