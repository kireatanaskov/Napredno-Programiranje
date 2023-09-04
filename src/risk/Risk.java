package risk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Risk {
    public void processAttacksData(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        bufferedReader.lines()
                .forEach(line -> {
                    String[] parts = line.split(";");
                    String[] player1 = parts[0].split("\\s+");
                    Integer[] player1Nums = new Integer[3];
                    for (int i = 0; i < 3; i++) {
                        player1Nums[i] = Integer.parseInt(player1[i]);
                    }
                    Arrays.sort(player1Nums);
                    String[] player2 = parts[1].split("\\s+");
                    Integer[] player2Nums = new Integer[player2.length];
                    for (int i = 0; i < player2.length; i++) {
                        player2Nums[i] = Integer.parseInt(player2[i]);
                    }
                    Arrays.sort(player2Nums);
                    int counter1 = 0, counter2 = 0;
                    for (int i = player1Nums.length - 1; i>=0; i--) {
                        if (player1Nums[i] > player2Nums[i])
                            counter1++;
                        else counter2++;
                    }
                    System.out.printf("%d %d\n", counter1, counter2);
                });
    }
}
