package groupID.FindDev.controller;


import groupID.FindDev.model.Post;
import groupID.FindDev.repository.PostRepository;
import groupID.FindDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId){
        return postRepository.findById(postId);
    }

    @PostMapping("/posts?userId={userId}")
    public Post addPost(@PathVariable Long userId,Post post){
        return userRepository.findById(userId)
                .map(user ->{
                    post.setUserId(user);
                    return  postRepository.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + post));
    }

    @PutMapping("/posts/users/{userId}&&{postId}")
    public Post updatePost(@PathVariable Long userId,
                           @PathVariable Long postId,
                           @Valid @RequestBody Post postRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("user not found with id " + userId);
        }

        return postRepository.findById(postId)
                .map(post -> {
                    post.setContent(postRequest.getContent());
                    post.setPostName(postRequest.getPostName());
                    post.setTitle(postRequest.getTitle());
                    return postRepository.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + postId));
    }

    @DeleteMapping("/posts/users/{userId}/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long userId,
                                        @PathVariable Long postId){
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("user not found with id " + userId);
        }
        return postRepository.findById(postId)
                .map(post -> {
                    postRepository.delete(post);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + postId));
    }
}
