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
@CrossOrigin
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
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        User tmp = userRepository.findUserByUsername(userRequest.getUsername());
        System.out.println(tmp.toString());
        if (tmp != null) {
            return null;
        } else {
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
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userRepository.findById(userId)    
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + userId));
    }


    @PostMapping("/users/login")
    @CrossOrigin
    public Object userLogin(@RequestBody LoginRequest request) {
        List<User> checkUser = userRepository.findByUsername(request.username);
        for (int i = 0; i < checkUser.size(); i++) {
            if (checkUser.get(i).getUsername().equals(request.username) && checkUser.get(i).getPassword().equals(request.password))  return checkUser.get(i);
        }return false;
    }
}

class LoginRequest {
    String username;
    String password;

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}