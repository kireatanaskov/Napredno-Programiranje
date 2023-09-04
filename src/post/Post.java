package post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Post {
    private String username;
    private String postContent;

    List<IComment> comments;

    public Post(String username, String postContent) {
        this.username = username;
        this.postContent = postContent;
        this.comments = new ArrayList<IComment>();
    }

    public void addComment(String username, String commentId, String content, String replyToId) {
        if (replyToId == null) {
            comments.add(new Comment(commentId, username, content));
        } else {
            comments.forEach(c -> c.addComment(replyToId, new Comment(commentId, username, content)));
        }
    }

    public void likeComment(String commentId) {
        comments.forEach(c -> c.like(commentId));
    }

    @Override
    public String toString() {
        comments.sort(Comparator.comparing(IComment::totalLikes).reversed());
        return String.format("Post: %s\nWritten by: %s\nComments:\n%s",
                this.postContent,
                this.username,
                this.comments.stream().map(c -> c.toStringIndented(1)).collect(Collectors.joining("\n")));
    }
}
