package student_records;

public class Stat implements Comparable<Stat>{
    private String type;
    private int count;

    public Stat(String type, int count) {
        this.type = type;
        this.count = count;
    }

    @Override
    public int compareTo(Stat o) {
        int c = Integer.compare(o.count, count);
        if (c == 0) {
            return type.compareTo(o.type);
        }
        return c;
    }

    public String getType() {
        return type;
    }
}
