//package com.firstProjectDemo.first_api;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api/tasks")
//public class TaskController {
//
//    private final TaskService taskService;
//    public TaskController(TaskService taskService){
//        this.taskService=taskService;
//    }
//
//    @GetMapping
//    public List<Task> getAllTasks(){
//        return taskService.getAllTasks();
//    }
//
//    @PostMapping
//    public void addTask(@RequestBody Task newTask){
//        taskService.addTask(newTask);
//    }
//}
