package shapes2;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShapesApplication {
    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
    }

    public void readCanvases(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        this.canvases = bufferedReader.lines()
                .map(line -> {
                    try {
                        return Canvas.createCanvas(line, maxArea);
                    } catch (IrregularCanvasException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public void printCanvases(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);

        canvases.stream()
                .sorted(Comparator.comparing(Canvas::getSum).reversed())
                .forEach(pw::println);

        pw.flush();
    }
}
