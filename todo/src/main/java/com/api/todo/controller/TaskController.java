package com.api.todo.controller;

import com.api.todo.model.Task;
import com.api.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthorized()")
@RequestMapping(path = "/api/todo")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(path = "/get/all")
    @PreAuthorize("permitAll")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "/get/status/{stat}")
    @PreAuthorize("permitAll")
    public List<Task> getTaskByStatus(@PathVariable boolean stat) {
        return taskService.getTaskByStatus(stat);
    }

    @GetMapping(path = "/get/id/{id}")
    @PreAuthorize("permitAll")
    public Task getTaskByID(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @PostMapping(path = "/add")
    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@Valid @RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping(path = "/update/{Id}/")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    public void updateTask(@PathVariable int Id, @Valid @RequestBody Task task) {
        taskService.updateTask(Id, task);
    }

    @DeleteMapping(path = "/delete/{Id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteTask(@PathVariable int Id) {
        taskService.deleteTask(Id);
    }


}
