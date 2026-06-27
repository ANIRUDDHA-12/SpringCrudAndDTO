package com.firstProjectDemo.first_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskService {
   private final TaskRepository taskRepository;

   public TaskService(TaskRepository taskRepository){
       this.taskRepository=taskRepository;
   }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public void addTask(Task newTask){
        taskRepository.save(newTask);
    }

}
