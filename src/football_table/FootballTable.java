package football_table;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FootballTable {
    Map<String, Team> teams;

    public FootballTable() {
        this.teams = new HashMap<String, Team>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        Team home = teams.computeIfAbsent(homeTeam, key -> new Team(homeTeam));
        Team away = teams.computeIfAbsent(awayTeam, key -> new Team(awayTeam));
        home.setGoalsScored(homeGoals);
        home.setGoalsConceded(awayGoals);
        away.setGoalsScored(awayGoals);
        away.setGoalsConceded(homeGoals);
        home.setPlayed(1);
        away.setPlayed(1);
        if (homeGoals > awayGoals) {
            home.setWins(1);
            away.setLoses(1);
        } else if (homeGoals < awayGoals) {
            home.setLoses(1);
            away.setWins(1);
        } else {
            home.setDraws(1);
            away.setDraws(1);
        }
    }

    public void printTable() {
        List<Team> result = teams.values().stream()
                .sorted(Comparator.comparing(Team::getPoints)
                        .thenComparing(Team::goalDifference).reversed()
                        .thenComparing(Team::getName))
                .collect(Collectors.toList());

        IntStream.range(0, result.size())
                .forEach(i -> System.out.printf("%2d. %s\n", i + 1, result.get(i)));
    }
}

