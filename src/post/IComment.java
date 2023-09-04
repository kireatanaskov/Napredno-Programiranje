package post;

public interface IComment {
    int totalLikes();

    void like(String commentId);

    String toStringIndented(int indent);

    void addComment(String commentId, IComment reply);
}
