package com.api.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id", updatable = false, insertable = false)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(name = "task_date", columnDefinition = "character varying(10) not null")
    @Temporal(TemporalType.DATE)
    @OrderBy("task_date asc")
    private LocalDate date;
    @Column(name = "task_description")
    @NotNull
    private String description;
    @Column(name = "task_status")
    private boolean status;


    public Task() {}

    public Task(LocalDate date, String description, boolean status) {
        this.date = date;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
