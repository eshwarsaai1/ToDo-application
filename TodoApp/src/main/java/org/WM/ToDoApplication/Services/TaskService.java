package org.WM.ToDoApplication.Services;

import org.WM.ToDoApplication.Models.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {
    int addTask(Task task, int userId) throws ClassNotFoundException, SQLException;
    void deleteTask(int taskId, int userId) throws ClassNotFoundException, SQLException;
    void updateTask(int taskId, Task task, int userId) throws ClassNotFoundException, SQLException;
    Task getTaskById(int taskId) throws ClassNotFoundException, SQLException;
    List<Task> getAllTasksOfUser(int userId) throws ClassNotFoundException, SQLException;
    void setTaskDone(int taskId, int userId) throws ClassNotFoundException, SQLException;
    void setTaskNotDone(int taskID, int userId) throws ClassNotFoundException, SQLException;
}
