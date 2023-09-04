package binary_numbers_average;

import java.io.*;
import java.util.Random;

public class BinaryNumbers {
    public static final String FILE_NAME = "C:\\Users\\HP\\Desktop\\New folder\\finki\\III semestar\\Napredno\\src\\BinaryNumbersAverage\\numbers.dat";

    public static void generateFile(int n) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            Random random = new Random();

//            IntStream.range(0, n)
//                    .forEach(i -> {
//                        int nextRandom = random.nextInt(1000);
//                        try {
//                            objectOutputStream.writeInt(nextRandom);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });

            for (int i=0; i<n; i++) {
                int nextRandom = random.nextInt(1000);
                objectOutputStream.writeInt(nextRandom);
            }
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double average() {
        int count = 0;
        double sum = 0;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));

            try {
                while (true) {
                    int number = objectInputStream.readInt();
                    sum += number;
                    ++count;
                }
            } catch (EOFException e) {
                System.out.println("End of file was reached.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sum / count;
    }

    public static void main(String[] args) {
        generateFile(1000);
        System.out.println("Average: " + average());
    }
}
