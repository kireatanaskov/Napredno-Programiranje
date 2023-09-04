package double_matrix;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DoubleMatrix {
    private double[][] data;

    public DoubleMatrix(double a[], int m, int n) throws InsufficientElementsException {
        if (a.length < m * n)
            throw new InsufficientElementsException();

        data = new double[m][n];
        int k = a.length - (m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = a[k++];
            }
        }
    }

    public String getDimensions() {
        return String.format("[%d x %d]", data.length, data[0].length);
    }

    public int rows() {
        return data.length;
    }

    public int columns() {
        return data[0].length;
    }

    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if (row < 1 || row > rows())
            throw new InvalidRowNumberException();

        double max = 0;
        for (int j = 0; j < data[0].length; j++) {
            max = Math.max(max, data[row - 1][j]);
        }
        return max;
    }

    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if (column < 1 || column > columns())
            throw new InvalidColumnNumberException();

        double max = 0;
        for (int i = 0; i < rows(); i++) {
            max = Math.max(max, data[i][column - 1]);
        }
        return max;
    }

    public double sum() {
        return IntStream.range(0, data.length)
                .mapToDouble(i -> IntStream.range(0, data[i].length)
                        .mapToDouble(j -> data[i][j])
                        .sum())
                .sum();
    }

    public double[] toSortedArray() {
        double[] sorted = Arrays.stream(data)
                .flatMapToDouble(Arrays::stream)
                .sorted()
                .toArray();

        return IntStream.range(0, sorted.length)
                .mapToDouble(i -> sorted[sorted.length - 1 - i])
                .toArray();
    }

    @Override
    public String toString() {
        return Arrays.stream(data)
                .map(row -> Arrays.stream(row)
                        .mapToObj(x -> String.format("%.2f", x))
                        .collect(Collectors.joining("\t")))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleMatrix that = (DoubleMatrix) o;
        return Arrays.deepEquals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(data);
    }
}
