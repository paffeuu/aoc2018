import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class GuardSleepAnalyzer {
    private List<String> lines;
    private List<Entry> entries;
    private Set<Guard> guardSet;

    public GuardSleepAnalyzer(String fileDir) throws FileNotFoundException {
        readInputFromFile(fileDir);
    }

    public int[] findGuardAndMinuteWhenHeOftenSleeps() {
        initialize();
        lines.forEach(this::loadEntryToList);
        entries.sort(Comparator.comparing(Entry::getDateTime));
        analyzeEntries();
        Guard theLongestSleepingGuard = findLongestSleepingGuard();
        int minuteWhenGuardOftenSleeps = findMinuteWhenGuardMostOftenSleeps(theLongestSleepingGuard);
        return new int[] {theLongestSleepingGuard.getId(), minuteWhenGuardOftenSleeps};
    }

    public int[] findGuardWhoMostOftenSleepOnTheSameMinute() {
        initialize();
        lines.forEach(this::loadEntryToList);
        entries.sort(Comparator.comparing(Entry::getDateTime));
        analyzeEntries();
        Guard mostOftenSleepingOnTheSameMinuteGuard =
                guardSet.stream().max(Comparator.comparingInt(guard ->
                        Arrays.stream(guard.getMinutesAsleep()).max().getAsInt())).get();
        int minuteWhenGuardOftenSleeps = findMinuteWhenGuardMostOftenSleeps(mostOftenSleepingOnTheSameMinuteGuard);
        return new int[] {mostOftenSleepingOnTheSameMinuteGuard.getId(), minuteWhenGuardOftenSleeps};
    }

    private void loadEntryToList(String line) {
        Entry entry = new Entry();
        String[] temp = line.split("] ");
        entry.setDateTime(LocalDateTime.parse(temp[0].substring(1).replace(' ', 'T')));
        if (temp[1].equals("falls asleep")) {
            entry.setState(Entry.State.FALLS_ASLEEP);
        } else if (temp[1].equals("wakes up")) {
            entry.setState(Entry.State.WAKES_UP);
        } else if (temp[1].contains("#")) {
            entry.setState(Entry.State.BEGINS_SHIFT);
            entry.setGuardId(Integer.parseInt(temp[1].split("#")[1].split(" ")[0]));
        } else {
            entry = null;
        }
        if (entry != null) {
            entries.add(entry);
        }
    }

    private void analyzeEntries() {
        int guardId = -1;
        int sleepStart = -1, sleepEnd;
        for (Entry entry : entries) {
            if (entry.getState() == Entry.State.BEGINS_SHIFT) {
                guardId = entry.getGuardId();
            } else if (entry.getState() == Entry.State.FALLS_ASLEEP) {
                sleepStart = entry.getDateTime().getMinute();
            } else if (entry.getState() == Entry.State.WAKES_UP) {
                sleepEnd = entry.getDateTime().getMinute();
                final int gId = guardId;
                Optional<Guard> guard = guardSet.stream().filter(g -> g.getId() == gId).findFirst();
                if (!guard.isPresent()) {
                    guard = Optional.of(new Guard(gId));
                    guardSet.add(guard.get());
                }
                int[] minutesAsleep = guard.get().getMinutesAsleep();
                for (int minute = sleepStart; minute < sleepEnd; minute++) {
                    minutesAsleep[minute]++;
                }
            }
        }
    }

    private Guard findLongestSleepingGuard() {
        guardSet.forEach(Guard::countSumOfSleepMinutes);
        return guardSet.stream().max(Guard::compareTo).get();
    }

    private int findMinuteWhenGuardMostOftenSleeps(Guard guard) {
        HashMap<Integer, Integer> minuteMap = new HashMap<>();
        for (int i = 0; i < guard.getMinutesAsleep().length; i++) {
            minuteMap.put(i, guard.getMinutesAsleep()[i]);
        }
        return minuteMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    private void readInputFromFile(String fileDir) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileDir));
        lines = new LinkedList<>();
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
    }

    private void initialize() {
        entries = new ArrayList<>();
        guardSet = new HashSet<>();
    }
}
