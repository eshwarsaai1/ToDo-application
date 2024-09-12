package org.WM.ToDoApplication.Services;

import org.WM.ToDoApplication.Models.Task;
import org.WM.ToDoApplication.Repository.impl.InDatabaseTaskRepository;

import java.sql.SQLException;
import java.util.List;

public class InDatabaseTaskService implements TaskService{
    private static final InDatabaseTaskRepository IN_DATABASE_TASK_REPOSITORY = InDatabaseTaskRepository.getInstance();
    private static InDatabaseTaskService inDatabaseTaskService;

    private InDatabaseTaskService(){
        //Making unable to instantiate
    }

    public static InDatabaseTaskService getInstance() {
        if(inDatabaseTaskService == null) inDatabaseTaskService = new InDatabaseTaskService();
        return inDatabaseTaskService;
    }


    @Override
    public int addTask(Task task, int userId) throws ClassNotFoundException, SQLException {
        return IN_DATABASE_TASK_REPOSITORY.addTask(task, userId);
    }

    @Override
    public void deleteTask(int taskId, int userId) throws ClassNotFoundException, SQLException {
        IN_DATABASE_TASK_REPOSITORY.deleteTask(taskId, userId);
    }

    @Override
    public void updateTask(int taskId, Task task, int userId) throws ClassNotFoundException, SQLException {
        IN_DATABASE_TASK_REPOSITORY.updateTask(taskId, task, userId);
    }

    @Override
    public Task getTaskById(int taskId) throws ClassNotFoundException, SQLException {
        return IN_DATABASE_TASK_REPOSITORY.getTaskById(taskId);
    }

    @Override
    public List<Task> getAllTasksOfUser(int userId) throws ClassNotFoundException, SQLException {
        return IN_DATABASE_TASK_REPOSITORY.getAllTasksOfUser(userId);
    }

    @Override
    public void setTaskDone(int taskId, int userId) throws ClassNotFoundException, SQLException {
        IN_DATABASE_TASK_REPOSITORY.setTaskDone(taskId, userId );
    }

    @Override
    public void setTaskNotDone(int taskID, int userId) throws ClassNotFoundException, SQLException {
        IN_DATABASE_TASK_REPOSITORY.setTaskNotDone(taskID, userId);
    }
}
