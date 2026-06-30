package com.firstProjectDemo.first_api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService=projectService;
    }
    @PostMapping("/projects")
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project){
        Project savedProject=projectService.addProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskForProject> addTask(@Valid @RequestBody TaskRequestDTO dto){
        TaskForProject task= projectService.addTask(dto);
        return new ResponseEntity<>(task,HttpStatus.CREATED);
    }

    @GetMapping("/projects/summary")
    public ResponseEntity<Page<ProjectSummaryDTO>> getProjectSummaries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy).ascending());
        Page<ProjectSummaryDTO> summaries= projectService.getProjectSummaries(pageable);
        return  ResponseEntity.ok(summaries);
    }
}
