package com.api.todo.dao;

import com.api.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(boolean stat);

    Task findById(int id);
}
