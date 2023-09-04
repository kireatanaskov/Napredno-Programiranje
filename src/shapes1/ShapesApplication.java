package shapes1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShapesApplication {
    private List<Canvas> canvases;

    public ShapesApplication() {
        canvases = new ArrayList<>();
    }

    public int readCanvases (InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        this.canvases = bufferedReader.lines()
                .map(Canvas::new)
                .collect(Collectors.toList());

        return canvases.stream().mapToInt(Canvas::getSquaresNum).sum();
    }

    public void printLargestCanvasTo (OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);

        canvases.stream()
                .max(Comparator.comparing(Canvas::getTotalPerimeter))
                .ifPresent(pw::println);

        pw.flush();
    }
}
