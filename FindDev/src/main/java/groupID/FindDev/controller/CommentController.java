package groupID.FindDev.controller;

import groupID.FindDev.model.Comment;
import groupID.FindDev.model.User;
import groupID.FindDev.repository.CommentRepository;
import groupID.FindDev.repository.PostRepository;
import groupID.FindDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:10080")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/comment")
    public Page<Comment> getComment(Pageable pageable){
        return commentRepository.findAll(pageable);
    }

    @GetMapping("/comment/{commandId}")
    public Optional<Comment> getCommandById(@PathVariable Long commandId){
        return commentRepository.findById(commandId);
    }

    @PostMapping("/post/{postId}/{userId}")
    public Comment addComment(@PathVariable Long postId,
                           @PathVariable Long userId,
                           @Valid @RequestBody Comment comment){
        return postRepository.findById(postId)
                .map(post ->{
                    userRepository.findById(userId)
                            .map(user -> {
                        comment.setPostId(post);
                        comment.setUserId(user);
                        return commentRepository.save(comment);
                    }).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + userId));
                    return commentRepository.save(comment);
                } ).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + postId));
    }

    @PutMapping("/comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @Valid @RequestBody Comment commentRequest){
        return commentRepository.findById(commentId)
                .map(comment -> {
                    comment.setContent(commentRequest.getContent());
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new ResourceNotFoundException("comment not found with id " + commentId));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentRepository.findById(commentId)
                .map(comment -> {
                    commentRepository.delete(comment);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("comment not found with id " + commentId));
    }



}
