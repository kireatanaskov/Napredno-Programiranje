package stadium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Sector {
    private String code;
    private int numSeats;
    private List<Integer> taken;
    HashSet<Integer> types;

    public Sector(String code, int numSeats) {
        this.code = code;
        this.numSeats = numSeats;
        this.taken = new ArrayList<>();
        this.types = new HashSet<Integer>();
    }

    public int free() {
        return this.numSeats - this.taken.size();
    }

    public boolean isTaken(int seat) {
        return taken.contains(seat);
    }

    public void takeSeat(int seat, int type) throws SeatNotAllowedException {
        if (type == 1) {
            if (types.contains(2))
                throw new SeatNotAllowedException();
        } else if (type == 2) {
            if (types.contains(1))
                throw new SeatNotAllowedException();
        }
        types.add(type);
        taken.add(seat);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%",
                this.code,
                this.free(),
                this.numSeats,
                (this.numSeats - this.free()) * 100.0 / this.numSeats);
    }
}
