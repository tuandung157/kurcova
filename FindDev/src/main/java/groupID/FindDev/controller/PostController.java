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
import java.util.ArrayList;
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

    @GetMapping("/posts/search")
    @ResponseBody
    public List<Post> getPostSearch(@RequestParam(required =false) String search){
        List<Post> posts = getPosts();// all of post in database
        if (search==null) return posts;
        String[] skills = search.split(" ");

        int n = posts.size();
        boolean[] choose = new boolean[1000];for(int i=0;i<choose.length;i++) choose[i] = true;
        boolean[] tmp_choose = new boolean[1000];

        for(String skill : skills){
            System.out.println(skill);

            List<Post> searchPostList = new ArrayList<>();
            for(int i=0;i<tmp_choose.length;i++) tmp_choose[i] = false;
            for(int i=0;i<posts.size();i++) {
                if (posts.get(i).getTitle().contains(skill)) {
                    searchPostList.add(posts.get(i));
                }
            }
            for(int i=0;i<searchPostList.size();i++) {
                //System.out.println(searchPostList.get(i).getPostId());
                tmp_choose[searchPostList.get(i).getPostId().intValue()] = true;
            }
            for(int i=0;i<1000;i++) {
                choose[i] = (choose[i] & tmp_choose[i]);
                if (choose[i]) System.out.println(i);
            }
        }
        List<Post> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            if (choose[posts.get(i).getPostId().intValue()]){
                //System.out.println(posts.get(i).getPostId());
                result.add(posts.get(i));
            }
        }
        return result;
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
