package shapes1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas {
    private String id;
    private List<Square> squares;

    public Canvas(String id, List<Square> squares) {
        this.id = id;
        this.squares = squares;
    }

    public Canvas (String line) {
        String[] parts = line.split("\\s+");
        this.id = parts[0];
        this.squares = Arrays.stream(parts)
                .skip(1)
                .map(part -> new Square(Integer.parseInt(part)))
                .collect(Collectors.toList());
    }

    public int getTotalPerimeter() {
        return squares.stream()
                .mapToInt(Square::getPerimeter)
                .sum();
    }

    public int getSquaresNum() {
        return squares.size();
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.id, this.squares.size(), this.getTotalPerimeter());
    }
}
