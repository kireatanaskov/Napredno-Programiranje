package movies;

import java.util.Comparator;

public class CoefRatingComparator implements Comparator<Movie> {
    int maxRatings;

    public CoefRatingComparator(int maxRatings) {
        this.maxRatings = maxRatings;
    }

    @Override
    public int compare(Movie o1, Movie o2) {
        int ar = Float.compare(o1.averageRating() * o1.getRatings().size() / maxRatings, o2.averageRating() * o2.getRatings().size() / maxRatings);
        if (ar == 0) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
        return -ar;
    }
}
