package double_matrix;

import java.io.InputStream;
import java.util.Scanner;

public class MatrixReader {
    public static DoubleMatrix read(InputStream is) throws InsufficientElementsException {
        Scanner scanner = new Scanner(is);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        double[] data = new double[m * n];
        for (int i = 0; i < data.length; ++i) {
            data[i] = scanner.nextDouble();
        }
        return new DoubleMatrix(data, m, n);
    }
}
