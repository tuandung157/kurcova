package groupID.FindDev.repository;

import groupID.FindDev.model.Comment;
import groupID.FindDev.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Post postId);
}
