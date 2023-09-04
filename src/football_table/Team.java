package football_table;

public class Team {
    private String name;
    private int wins;
    private int loses;
    private int draws;
    private int played;
    private int goalsScored;
    private int goalsConceded;

    public Team(String name) {
        this.name = name;
        this.wins = 0;
        this.loses = 0;
        this.draws = 0;
        this.played = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public void setLoses(int loses) {
        this.loses += loses;
    }

    public void setDraws(int draws) {
        this.draws += draws;
    }

    public void setPlayed(int played) {
        this.played += played;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded += goalsConceded;
    }

    public int goalDifference() {
        return goalsScored - goalsConceded;
    }

    public int getPoints() {
        return wins * 3 + draws;
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d", name, played, wins, draws, loses, getPoints());
    }
}
