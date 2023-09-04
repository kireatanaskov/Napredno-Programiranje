package calls_midterm2;

public class Call {
    private String uuid;
    private String dialer;
    private String receiver;
    public CallState state;

    public Call(String uuid, String dialer, String receiver, long initialized) {
        this.uuid = uuid;
        this.dialer = dialer;
        this.receiver = receiver;
        state = new InitializedState(this);
        state.initialized = initialized;
    }

    public void updateCall(long timestamp, String action) throws InvalidOperation {
        if (action.equals("ANSWER")) {
            this.state.answer(timestamp);
        } else if (action.equals("END")) {
            this.state.end(timestamp);
        } else if (action.equals("HOLD")) {
            this.state.hold(timestamp);
        } else if (action.equals("RESUME")) {
            this.state.resume(timestamp);
        }
    }

    public long getStart() {
        return state.start == 0 ? state.initialized : state.start;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDialer() {
        return dialer;
    }

    public String getReceiver() {
        return receiver;
    }

    public long getDuration() {
        return state.getTotalDuration();
    }
}
