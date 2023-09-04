package bithdays_paradox;

import java.util.HashSet;
import java.util.Random;

public class BirthdayParadox {
    private final int maxPersons;
    static int TRIALS = 5000;

    public BirthdayParadox(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public void conductExperiment() {
        for (int i=2;i<=maxPersons;i++) {
            System.out.printf("%d --> %.10f\n", i, runSimulation(i));
        }
    }

    private float runSimulation(int people) {
        Random random = new Random();
        int counter = 0;
        for (int i = 0; i < TRIALS; i++) {
            if (runTrial(people, random)) {
                ++counter;
            }
        }
        return counter*1.0f/TRIALS;
    }

    private boolean runTrial(int people, Random random) {
        HashSet<Integer> birthdays = new HashSet<Integer>();

        for (int i=0; i<people; i++) {
            int birthday = random.nextInt(365) + 1;
            if (birthdays.contains(birthday)) {
                return true;
            } else
                birthdays.add(birthday);
        }

        return false;
    }
}
