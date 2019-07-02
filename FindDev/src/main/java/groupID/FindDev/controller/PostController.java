package groupID.FindDev.controller;


import groupID.FindDev.model.Post;
import groupID.FindDev.repository.PostRepository;
import groupID.FindDev.repository.UserRepository;
import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    @GetMapping("/posts/page")
    public Page<Post> getPostsPage(Pageable pageable){
        return postRepository.findAll(pageable);
    }
    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }
    @GetMapping("/posts/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId){
        return postRepository.findById(postId);
    }

    @PostMapping("/posts/{userId}")
    public Post createPost(@PathVariable Integer userId,@RequestBody Post post){
        System.out.println(post.getPostName());
        template.convertAndSend(fanout.getName(),"", post.getPostName());
        return userRepository.findById(userId.longValue())
                .map(user ->{
                    post.setUserId(user);
                    return  postRepository.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + post));
    }

    @PutMapping("/posts/edit")
    public Post updatePost(@Valid @RequestBody Post postRequest) {
//        if(!userRepository.existsById(postRequest.getUserId())) {
//            throw new ResourceNotFoundException("user not found with id " + userId);
//        }

        return postRepository.findById(postRequest.getPostId())
                .map(post -> {
                    post.setContent(postRequest.getContent());
                    post.setPostName(postRequest.getPostName());
                    post.setTitle(postRequest.getTitle());
                    return postRepository.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("post not found with id " + postRequest.getPostId()));
    }

    @DeleteMapping("/posts/delete/{userId}/{postId}")
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
