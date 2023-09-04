package calls_midterm2;

public class InitializedState extends CallState{
    public InitializedState(Call call) {
        super(call);
    }

    @Override
    public void answer(long timestamp){
        this.start = timestamp;
        call.state = new InProgressState(this);
    }

    @Override
    public void end(long timestamp) throws InvalidOperation {
        this.start = timestamp;
        call.state = new IdleState(this);
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
