package calls_midterm2;

public class PausedState extends CallState{
    public PausedState(CallState callState) {
        super(callState);
    }

    @Override
    public void answer(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }

    @Override
    public void end(long timestamp) throws InvalidOperation {
        this.endHold(timestamp);
        this.end = timestamp;
        call.state = new IdleState(this);
    }

    @Override
    public void hold(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }

    @Override
    public void resume(long timestamp) throws InvalidOperation {
        this.endHold(timestamp);
        call.state = new InProgressState(this);
    }
}
