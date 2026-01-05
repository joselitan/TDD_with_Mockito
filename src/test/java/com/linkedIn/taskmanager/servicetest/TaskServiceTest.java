package com.linkedIn.taskmanager.servicetest;
import com.linkedIn.taskmanager.exception.TaskNotFoundException;
import com.linkedIn.taskmanager.model.Task;
import com.linkedIn.taskmanager.repository.TaskRepository;
import com.linkedIn.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;



    @Test
    void testGetTaskById(){
        //ARRANGE
        Task task = new Task(1L, "Task 1", "To do");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        //ACT
        Task retrievedTask = taskService.getTaskById(1L);

        //ASSERT
        assertNotNull(task);
        assertEquals(1L, retrievedTask.getId());
        assertEquals("Task 1", retrievedTask.getTitle());
        assertEquals("To do", retrievedTask.getStatus());
        verify(taskRepository, times(1)).findById(1L);

    }

    @Test
    void testGetTaskById_TaskNotFound(){
        //arrange
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        //act and assert
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
        verify(taskRepository, times(1)).findById(1L);

        //assert

    }

    @Test
    void testUpdateTaskStatus(){
        // Arrange
        Task task = new Task(1L, "Existing task", "To do");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task updatedTask = taskService.updateTaskStatus(1L, "In Progress");
        // Assert
        assertNotNull(updatedTask);
        assertEquals("In Progress", updatedTask.getStatus());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

}
