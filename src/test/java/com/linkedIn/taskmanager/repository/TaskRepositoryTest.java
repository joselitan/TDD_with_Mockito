package com.linkedIn.taskmanager.repository;

import com.linkedIn.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void testSaveTask(){
        // arrange
        Task task = new Task("Test Task", "To do");

        // act
        Task savedTask = taskRepository.save(task);

        // assert
        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
    }

    @Test
    void testFindAllTasks(){
        // arrange
        Task task1 = new Task("Task 1", "To do");
        taskRepository.save(task1);

        Task task2 = new Task("Task 2", "Done");
        taskRepository.save(task2);

        // act
        List<Task> tasks = taskRepository.findAll();

        // assert
        assertEquals(2, tasks.size());

    }

    @Test
    void testFindTaskById(){
        // Arrange
        Task task = new Task("Test task", "To do");
        taskRepository.save(task);

        // Act
        Optional<Task> optionalTask = taskRepository.findById(task.getId());

        // Assert
        assertFalse(optionalTask.isEmpty());
        assertEquals(task.getId(), optionalTask.get().getId());
    }

    @Test
    void testUpdateTaskStatus(){
        // Arrange
        Task task = new Task("Test task", "To do");
        taskRepository.save(task);

        // Act
        task.setStatus("Done");
        taskRepository.save(task);
        Optional<Task> optionalTask = taskRepository.findById(task.getId());

        // Assert
        assertFalse(optionalTask.isEmpty());
        assertEquals("Done", optionalTask.get().getStatus());
    }

    @Test
    void testDeleteTask(){
        // Arrange
        Task task = new Task("Test to delete", "Done");
        taskRepository.save(task);

        // Act
        taskRepository.delete(task);
        Optional<Task> deleteTask = taskRepository.findById(task.getId());

        // Assert
        assertTrue(deleteTask.isEmpty());
    }
}