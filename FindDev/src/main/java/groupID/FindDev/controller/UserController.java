package groupID.FindDev.controller;

import groupID.FindDev.model.User;
import groupID.FindDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:10080")
public class UserController {
    private Date date = new Date();
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/page")
    public Page<User> getUsersPage(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId){
        return userRepository.findById(userId);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest){
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(userRequest.getUsername());
                    user.setEmail(userRequest.getEmail());
                    user.setPassword(userRequest.getPassword());
                    user.setTelephone(userRequest.getTelephone());
                    user.setText(userRequest.getText());
                    user.setLastLog(new Timestamp(date.getTime()));
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + userId));
    }
}
