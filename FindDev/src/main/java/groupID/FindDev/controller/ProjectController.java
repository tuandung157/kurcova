package groupID.FindDev.controller;

import groupID.FindDev.model.Project;
import groupID.FindDev.repository.GroupRepository;
import groupID.FindDev.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GroupRepository groupRepository;
    @GetMapping("/projects")
    public Page<Project> getProject(Pageable pageable){
        return projectRepository.findAll(pageable);
    }

    @GetMapping("/projects/{projectId}")
    public Optional<Project> getProjectById(@PathVariable Long ProjectId){
        return projectRepository.findById(ProjectId);
    }

    @PostMapping("/projects/{groupId}/{userId}")
    public Project addproject(@PathVariable Long groupId,@PathVariable Long userId,@Valid @RequestBody Project project){
        return groupRepository.findById(groupId)
                .map(group ->{
                    project.setGroupId(group);
                    return  projectRepository.save(project);
                }).orElseThrow(() -> new ResourceNotFoundException("group not found with id " + groupId));
    }

    @PutMapping("/groups/{groupId}/projects/{projectId}")
    public Project updateproject(@PathVariable Long groupId,
                                 @PathVariable Long projectId,
                                 @Valid @RequestBody Project projectRequest) {
        if(!groupRepository.existsById(groupId)) {
            throw new ResourceNotFoundException("group not found with id " + groupId);
        }

        return projectRepository.findById(projectId)
                .map(project -> {
                    project.setProjectName(projectRequest.getProjectName());
                    project.setContent(projectRequest.getContent());
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ResourceNotFoundException("project not found with id " + projectId));
    }

    @DeleteMapping("/projects/{groupId}/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId){
        return projectRepository.findById(projectId)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("project not found with id " + projectId));
    }



}
