package groupID.FindDev.controller;

import groupID.FindDev.model.Group;
import groupID.FindDev.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public Page<Group> getMess(Pageable pageable){
        return groupRepository.findAll(pageable);
    }

    @GetMapping("/groups/{groupId}")
    public Optional<Group> getGroupById(@PathVariable Long groupId){
        return groupRepository.findById(groupId);
    }

    @PostMapping("/groups")
    public Group createGroup(@Valid @RequestBody Group group){
        return groupRepository.save(group);
    }

    @PutMapping("/groups/{groupId}/users/{userId}")
    public Group updateUser(@PathVariable Long groupId, @Valid @RequestBody Group groupRequest){
        return groupRepository.findById(groupId)
                .map(group -> {
                    group.setGroupName(group.getGroupName());
                    group.setAbout(group.getAbout());
                    group.setGroupNumber(group.getGroupNumber());
                    group.setCreatedAt(group.getCreatedAt());
                    group.setNumProject(group.getNumProject());
                    return groupRepository.save(group);
                }).orElseThrow(() -> new ResourceNotFoundException("group not found with id " + groupId));
    }


}
