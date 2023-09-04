package calls_midterm2;

public class InProgressState extends CallState{
    public InProgressState(CallState callState) {
        super(callState);
    }

    @Override
    public void answer(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }

    @Override
    public void end(long timestamp) throws InvalidOperation {
        this.end = timestamp;
        call.state = new IdleState(this);
    }

    @Override
    public void hold(long timestamp) throws InvalidOperation {
        this.holdStarted = timestamp;
        call.state = new PausedState(this);
    }

    @Override
    public void resume(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }
}
