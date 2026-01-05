package com.linkedIn.taskmanager.service;

import com.linkedIn.taskmanager.exception.TaskNotFoundException;
import com.linkedIn.taskmanager.model.Task;
import org.springframework.stereotype.Service;
import com.linkedIn.taskmanager.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task updateTaskStatus(Long id, String Status) {
        Task taskToUpdate = getTaskById(id);
        taskToUpdate.setStatus(Status);
        return taskRepository.save(taskToUpdate);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task not found, id: "
                        + id));
    }
}
