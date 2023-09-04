package quiz_processor;

public class QuizException extends Exception{
    public QuizException() {
        super("A quiz must have same number of correct and selected answers");
    }
}
