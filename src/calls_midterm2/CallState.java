package calls_midterm2;

public abstract class CallState implements ICallState{
    Call call;
    public long initialized;
    public long start;
    public long end;
    public long holdStarted;
    public long holdEnded;
    public int durationHold;

    public CallState(Call call) {
        this.call = call;
    }

    public CallState(CallState oldState) {
        this.initialized = oldState.initialized;
        this.start = oldState.start;
        this.end = oldState.end;
        this.holdStarted = oldState.holdStarted;
        this.holdEnded = oldState.holdEnded;
        this.durationHold = oldState.durationHold;
        this.call = oldState.call;
    }

    public long getTotalDuration() {
        return end == 0 ? 0 : (end - start - durationHold);
    }

    public void endHold(long timestamp) {
        this.holdEnded = timestamp;
        this.durationHold += (holdEnded - holdStarted);
        this.holdStarted = 0;
        this.holdEnded = 0;
    }
}
