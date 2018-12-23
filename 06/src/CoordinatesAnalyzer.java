import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinatesAnalyzer {
    private List<String> lines;
    private List<int[]> coordinates;
    private int[][] map;
    private int[] sizes;

    public CoordinatesAnalyzer(String fileDir) throws FileNotFoundException {
        lines = InputReader.readAllLinesFromFile(fileDir);
        analyzeInput();
    }

    public int findSizeOfLargestNotInfiniteArea() {
        addCoordinatesToMap();
        fillTheMapByClosestCoords();
        return checkSuspectedCoords(checkTheEdges());
    }

    public int findSizeOfRegionContainingLocationUnder10000(int underThan) {
        addCoordinatesToMap();
        return countIfLocationSumToUnder(underThan);
    }

    private void analyzeInput() {
        coordinates = lines.stream()
                .map(line -> line.split(", "))
                .map(tempCoords -> new int[]{Integer.parseInt(tempCoords[1]), Integer.parseInt(tempCoords[0])})
                .collect(Collectors.toList());
    }

    private void addCoordinatesToMap() {
        int maxRange = coordinates.stream()
                .map(coords -> Arrays.stream(coords).max().getAsInt())
                .mapToInt(i -> i)
                .max()
                .getAsInt() + 1;
        map = new int[maxRange][maxRange];
        int i = 0;
        for (int[] coords : coordinates) {
            map[coords[0]][coords[1]] = i++;
        }
    }

    private void fillTheMapByClosestCoords() {
        sizes = new int[coordinates.size()];
        Arrays.fill(sizes, 1);
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 0) {
                    int coordX = x; int coordY = y;
                    int[] distance = coordinates.stream()
                            .mapToInt(coords -> Math.abs(coords[0] - coordY) + Math.abs(coords[1] - coordX))
                            .toArray();
                    int maxDistance = Integer.MAX_VALUE;
                    int closestCoords = -1;
                    for (int i = 0; i < distance.length; i++) {
                        if (distance[i] < maxDistance) {
                            maxDistance = distance[i];
                            closestCoords = i;
                        } else if (distance[i] == maxDistance) {
                            closestCoords = -1;
                        }
                    }
                    map[y][x] = closestCoords;
                    if (closestCoords != -1) {
                        sizes[closestCoords]++;
                    }
                }
            }
        }
    }

    private int countIfLocationSumToUnder(int underThan) {
        int counter = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int coordX = x; int coordY = y;
                int sum = coordinates.stream()
                        .mapToInt(coords -> Math.abs(coords[0] - coordY) + Math.abs(coords[1] - coordX))
                        .sum();
                if (sum < underThan) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private Set<Integer> checkTheEdges() {
        Set<Integer> edges = new HashSet<>();
        for (int y = 0; y < map.length; y++) {
            if (y == 0 || y == map.length - 1) {
                for (int x = 0; x < map[y].length; x++) {
                    edges.add(map[y][x]);
                }
            } else {
                edges.add(map[y][0]);
                edges.add(map[y][map[y].length - 1]);
            }
        }
        Set<Integer> coordsToCheck = new HashSet<>();
        IntStream.range(0, coordinates.size()).forEach(coordsToCheck::add);
        coordsToCheck.removeIf(edges::contains);
        return coordsToCheck;
    }

    private int checkSuspectedCoords(Set<Integer> suspectedCoords) {
        return suspectedCoords.stream().mapToInt(i -> sizes[i]).max().getAsInt();
    }
}
