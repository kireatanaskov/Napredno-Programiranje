package post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Comment extends CommentBase{
    List<IComment> replies;

    public Comment(String id, String username, String content) {
        super(id, username, content);
        this.replies = new ArrayList<IComment>();
    }

    @Override
    public int totalLikes() {
        return likes + replies.stream().mapToInt(IComment::totalLikes).sum();
    }

    @Override
    public void like(String commentId) {
        if (this.id.equals(commentId)) {
            ++likes;
        } else {
            replies.forEach(v -> v.like(commentId));
        }
    }

    @Override
    public String toStringIndented(int indent) {
        String result = super.toStringIndented(indent);
        if (replies.size() > 0) {
            return result + "\n" + replies.stream()
                    .sorted(Comparator.comparing(IComment::totalLikes).reversed())
                    .map(r -> r.toStringIndented(indent + 1))
                    .collect(Collectors.joining("\n"));
        }
        return result;
    }

    @Override
    public void addComment(String commentId, IComment reply) {
        if (this.id.equals(commentId)) {
            this.replies.add(reply);
        } else {
            this.replies.forEach(r -> r.addComment(commentId, reply));
        }
    }
}
