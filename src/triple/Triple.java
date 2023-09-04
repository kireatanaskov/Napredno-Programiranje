package triple;

public class Triple<T extends Number> {
    private T num1;
    private T num2;
    private T num3;

    public Triple(T num1, T num2, T num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public double max() {
        double max = num1.doubleValue();
        if (num2.doubleValue() > max) {
            max = num2.doubleValue();
        }
        if (num3.doubleValue() > max) {
            max = num3.doubleValue();
        }
        return max;
    }

    public double average() {
        double sum = num1.doubleValue() + num2.doubleValue() + num3.doubleValue();
        return sum / 3;
    }

    public void sort() {
        if (num2.doubleValue() > num3.doubleValue()) {
            T temp = num3;
            num3 = num2;
            num2 = temp;
        }
        if (num1.doubleValue() > num2.doubleValue()) {
            T temp = num2;
            num2 = num1;
            num1 = temp;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f",
                (Double) num1,
                (Double) num2,
                (Double) num3);
    }
}
