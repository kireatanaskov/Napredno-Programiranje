package word_count;

public class LineCounter {
    private int lines;
    private int words;
    private int chars;

    public LineCounter(int lines, int words, int chars) {
        this.lines = lines;
        this.words = words;
        this.chars = chars;
    }

    public LineCounter(String line) {
        ++lines;
        words = line.split("\\s+").length;
        chars = line.length();
    }

    public LineCounter sum(LineCounter lineCounter) {
        return new LineCounter(this.lines + lineCounter.lines, this.words + lineCounter.words, this.chars + lineCounter.chars);
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Chars: %d", lines, words, chars);
    }
}
