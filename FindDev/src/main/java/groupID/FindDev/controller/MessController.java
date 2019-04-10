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
import java.util.List;
import java.util.Optional;

@RestController
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

//    @GetMapping("/message/{userId1}/{userId2}")
//    public List<Mess> getMessById(@PathVariable Long messId){
//
//        return messRepository.findAll(new Example<Mess>() {
//            @Override
//            public Mess getProbe() {
//                return null;
//            }
//
//            @Override
//            public ExampleMatcher getMatcher() {
//                return null;
//            }
//        });
//    }


    @PostMapping("/message")
    public Mess createMessage(@Valid @RequestBody Mess mess){
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
