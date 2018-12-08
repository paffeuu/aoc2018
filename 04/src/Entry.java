import java.time.LocalDateTime;

public class Entry {
    private LocalDateTime dateTime;
    private State state;
    private int guardId = -1;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        BEGINS_SHIFT,
        FALLS_ASLEEP,
        WAKES_UP
    }
}

