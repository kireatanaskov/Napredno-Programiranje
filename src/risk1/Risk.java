package risk1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Risk {
    public int processAttacksData(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        return (int) bufferedReader.lines()
                .mapToInt(line -> {
                    String[] parts = line.split(";");
                    String[] player1 = parts[0].split("\\s+");
                    Integer[] player1nums = new Integer[3];
                    for (int i = 0; i < 3; i++) {
                        player1nums[i] = Integer.parseInt(player1[i]);
                    }
                    Arrays.sort(player1nums);
                    String[] player2 = parts[1].split("\\s+");
                    Integer[] player2nums = new Integer[3];
                    for (int i = 0; i < 3; i++) {
                        player2nums[i] = Integer.parseInt(player2[i]);
                    }
                    Arrays.sort(player2nums);
                    int count = 0;
                    for (int i = 2; i >= 0; i--) {
                        if (player1nums[i] > player2nums[i])
                            count++;
                    }

                    if (count == 3)
                        return 1;
                    else return 0;
                })
                .filter(i -> i == 1)
                .count();
    }
}
