package com.api.todo.service;

import com.api.todo.dao.TaskRepository;
import com.api.todo.model.Task;
import com.api.todo.util.BasicLogger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    public static final String API_BASE_URL = "http://localhost:8080/auctions/";
    @Autowired
    private TaskRepository taskRepository;
    private String authToken = null;


    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskByStatus(boolean stat) {
        return taskRepository.findByStatus(stat);
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void updateTask(int Id, Task task) {
        Task withinDB = taskRepository.findById(Id);
        if (withinDB == null) {
            throw new IllegalStateException(
                "Task " + task.getId() + " Does Not Exist");
        }

        if (withinDB != null && task.getId() == Id) {
            withinDB.setDate(task.getDate());
            withinDB.setDescription(task.getDescription());
            withinDB.setStatus(task.isStatus());
        }

    }

    public void deleteTask(int Id) {
        Task task = taskRepository.findById(Id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID", null);
        } else {
            taskRepository.deleteById(Id);
        }
    }

}
