package groupID.FindDev.controller;

import groupID.FindDev.model.Mess;
import groupID.FindDev.model.User;
import groupID.FindDev.repository.MessRepository;
import groupID.FindDev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class MessController {
    @Autowired
    private MessRepository messRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/message")
    public Page<Mess> getMess(Pageable pageable){
        return messRepository.findAll(pageable);
    }

    @GetMapping("/message/{messId}")
    public Optional<Mess> getMessById(@PathVariable Long messId){
        return messRepository.findById(messId);
    }

    @GetMapping("/message/{userId1}/{userId2}")
    public List<Mess> getConversation(@PathVariable Long userId1,@PathVariable Long userId2){
        Optional<User> user1 = userRepository.findById(userId1);
        if(!user1.isPresent()) {
            return null;
        }
        Optional<User> user2 = userRepository.findById(userId2);
        if(!user2.isPresent()) {
            return null;
        }
        List<Mess> listMessUserFrom = messRepository.findByUserFromAndUserTo(user1.get(),user2.get());
        List<Mess> listMessUserTo = messRepository.findByUserToAndUserFrom(user1.get(),user2.get());
        listMessUserFrom.addAll(listMessUserTo);
        //sort by created time

        listMessUserFrom.sort((o1, o2) -> o1.getCreatedAt().compareTo(o2.getUpdatedAt()));
        return listMessUserFrom;
    }


    @PostMapping("/message/{userId1}/{userId2}")
    public Mess createMessage(@PathVariable Long userId1,@PathVariable Long userId2 ,@Valid @RequestBody Mess mess){

        userRepository.findById(userId1)
                .map(user -> {
                    mess.setUserFrom(user);
                    return messRepository.save(mess);
                }).orElseThrow();
        userRepository.findById(userId2)
                .map(user -> {
                    mess.setUserTo(user);
                    return messRepository.save(mess);
                }).orElseThrow();
        return messRepository.save(mess);
    }

    @DeleteMapping("/message/{messId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messId){
        return messRepository.findById(messId)
                .map(mess -> {
                    messRepository.delete(mess);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("mess not found with id " + messId));
    }
}
