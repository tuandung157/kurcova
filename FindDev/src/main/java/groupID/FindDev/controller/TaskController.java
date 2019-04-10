package groupID.FindDev.controller;

import groupID.FindDev.model.Task;
import groupID.FindDev.repository.ProjectRepository;
import groupID.FindDev.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @GetMapping("/task")
    public Page<Task> getTask(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

    @GetMapping("/task/{taskId}")
    public Optional<Task> getMessById(@PathVariable Long taskId){
        return taskRepository.findById(taskId);
    }

    @PostMapping("/projects/{projectId}")
    public Task addTask(@PathVariable Long projectId,@Valid @RequestBody Task task){
        return projectRepository.findById(projectId)
                .map(project ->{
                    task.setProjectid(project);
                    return  taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException("project not found with id " + projectId));
    }

    @PutMapping("/projects/{projectId}/task/{taskId}")
    public Task updateTask(@PathVariable Long projectId,
                           @PathVariable Long taskId,
                           @Valid @RequestBody Task taskRequest) {
        if(!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("project not found with id " + projectId);
        }

        return taskRepository.findById(taskId)
                .map(task -> {
                    task.setContent(taskRequest.getContent());
                    task.setTaskName(taskRequest.getTaskName());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new ResourceNotFoundException("task not found with id " + taskId));
    }

    @DeleteMapping("/projects/{projectId}/task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long projectId,
                                             @PathVariable Long taskId){
        if(!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("project not found with id " + projectId);
        }
        return taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("task not found with id " + taskId));
    }

}
