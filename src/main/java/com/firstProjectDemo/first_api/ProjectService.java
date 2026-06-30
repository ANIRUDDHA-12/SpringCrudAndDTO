package com.firstProjectDemo.first_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskForProjectRepository taskForProjectRepository;
    private final NotificationService notificationService;

    public ProjectService(ProjectRepository projectRepository,TaskForProjectRepository taskForProjectRepository,NotificationService notificationService){
        this.projectRepository=projectRepository;
        this.taskForProjectRepository=taskForProjectRepository;
        this.notificationService=notificationService;
    }

    public Project addProject(Project project)
    { Project savedProject= projectRepository.save(project);
        notificationService.sendProjectCreationEmail(project.getProjectName());
        return savedProject;
    }

    public TaskForProject addTask(TaskRequestDTO taskRequestDTO){
        Project project= projectRepository.findById(taskRequestDTO.projectId())
                .orElseThrow(()-> new ProjectNotFoundException("The project not found for "+taskRequestDTO.projectId()));

        double currentCost= project.getTaskList().stream()
                .mapToDouble(TaskForProject::getCost)
                .sum();

        if(currentCost+taskRequestDTO.cost()> project.getAllocatedBudget()){
            throw new BudgetExceededException("this desired cost for this project crosses the allocated budget");
        }
            TaskForProject newTask=new TaskForProject();
            newTask.setTaskName(taskRequestDTO.taskName());
            newTask.setCost(taskRequestDTO.cost());
            newTask.setStatus(taskRequestDTO.status());
            newTask.setProject(project);
            return taskForProjectRepository.save(newTask);
    }

    public Page<ProjectSummaryDTO> getProjectSummaries(Pageable pageable){
        Page<Project> projectPage=projectRepository.findAll(pageable);

        return projectPage.map(project -> {
            double totalCostOfTasks= project.getTaskList().stream()
                    .mapToDouble(TaskForProject::getCost)
                    .sum();

            double remainingBudget = project.getAllocatedBudget()-totalCostOfTasks;

            return new ProjectSummaryDTO(
                    project.getId(),
                    project.getProjectName(),
                    project.getAllocatedBudget(),
                    totalCostOfTasks,
                    remainingBudget
            );
        });
    }
}
