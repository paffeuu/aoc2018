import java.util.Arrays;

public class Guard implements Comparable<Guard>{
    private int id;
    private int[] minutesAsleep;
    private int sumOfMinutesAsleep;

    public Guard(int id) {
        this.id = id;
        minutesAsleep = new int[60];
    }

    public void countSumOfSleepMinutes() {
        sumOfMinutesAsleep = Arrays.stream(minutesAsleep).sum();
    }

    @Override
    public int compareTo(Guard o) {
        return Integer.compare(sumOfMinutesAsleep, o.sumOfMinutesAsleep);
    }

    public int getId() {
        return id;
    }

    public int[] getMinutesAsleep() {
        return minutesAsleep;
    }
}
