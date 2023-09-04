package calls_midterm2;

public interface ICallState {
    void answer(long timestamp) throws InvalidOperation;
    void end(long timestamp) throws InvalidOperation;
    void hold(long timestamp) throws InvalidOperation;
    void resume(long timestamp) throws InvalidOperation;
}
