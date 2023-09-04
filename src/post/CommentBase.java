package post;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class CommentBase implements IComment{
    String id;
    String username;
    String content;
    int likes = 0;

    public CommentBase(String id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }

    String indentString(int indent) {
        return IntStream.rangeClosed(0, indent)
                .mapToObj(i -> "    ")
                .collect(Collectors.joining(""));
    }

    @Override
    public String toStringIndented(int indent) {
        String ind = indentString(indent);
        return String.format("%sComment: %s\n%sWritten by: %s\n%sLikes: %d",
                ind,
                this.content,
                ind,
                this.username,
                ind,
                this.likes);
    }
}
