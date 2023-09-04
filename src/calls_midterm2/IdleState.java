package calls_midterm2;

public class IdleState extends CallState{
    public IdleState(CallState callState) {
        super(callState);
    }

    @Override
    public void answer(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }

    @Override
    public void end(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }

    @Override
    public void hold(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }

    @Override
    public void resume(long timestamp) throws InvalidOperation {
        throw new InvalidOperation();
    }
}
