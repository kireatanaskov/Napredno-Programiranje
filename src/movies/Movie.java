package movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private List<Integer> ratings;

    public Movie(String title, int[] ratings) {
        this.title = title;
        this.ratings = new ArrayList<Integer>(ratings.length);
        for (int r : ratings) {
            this.ratings.add(r);
        }
    }

    public float averageRating() {
        return (float) this.ratings.stream().mapToInt(rating -> rating).sum() / ratings.size();
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings", title, averageRating(), ratings.size());
    }
}
