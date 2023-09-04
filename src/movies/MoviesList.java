package movies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MoviesList {
    private List<Movie> movies;

    public MoviesList() {
        this.movies = new ArrayList<Movie>();
    }

    public void addMovie(String title, int[] ratings) {
        this.movies.add(new Movie(title, ratings));
    }

    public List<Movie> top10ByAvgRating() {
        return this.movies.stream()
                .sorted(Comparator.comparing(Movie::averageRating).reversed()
                        .thenComparing(Movie::getTitle))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Movie> top10ByRatingCoef() {
        int maxRatings = 0;
        for (Movie movie : this.movies) {
            maxRatings = Math.max(maxRatings, movie.getRatings().size());
        }
        Collections.sort(movies, new CoefRatingComparator(maxRatings));
        if (movies.size() >= 10) {
            return movies.subList(0, 10);
        } else return movies;
    }
}
