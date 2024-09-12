package org.WM.ToDoApplication.Repository.impl;

import org.WM.ToDoApplication.Models.Task;
import org.WM.ToDoApplication.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InDatabaseTaskRepository implements TaskRepository {
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseTaskRepository.class);
    private static InDatabaseTaskRepository inDatabaseTaskRepository;
    private Connection connection;
    private InDatabaseTaskRepository(){
        //Making private to prevent class cannot instantiatable
    }

    public static InDatabaseTaskRepository getInstance(){
        if(inDatabaseTaskRepository == null){
            inDatabaseTaskRepository = new InDatabaseTaskRepository();
        }
        return inDatabaseTaskRepository;
    }

    @Override
    public int addTask(Task task, int userId) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO TASKS (TASK_NAME, DEADLINE, PRIORITY_ID, USER_ID) VALUES('" +
                task.getTaskName() + "', '" +
                task.getDeadline() + "'," +
                task.getPriorityId() + "," +
                userId + ")";
        String query2 = "SELECT LAST_INSERT_ID()";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();

        statement.executeUpdate(query);
        ResultSet resultSet = statement.executeQuery(query2);
        if(resultSet.next()){
            logger.info("added task");
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public void deleteTask(int taskId, int userId) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM TASKS WHERE USER_ID =" + userId + " and TASK_ID="+ taskId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        logger.info("tasks deleted");
    }

    @Override
    public void updateTask(int taskId, Task task, int userId) throws ClassNotFoundException, SQLException {
        String query = "UPDATE TASKS SET TASK_NAME='" + task.getTaskName() +
                "', DEADLINE = '" + task.getDeadline() +
                "', PRIORITY_ID = " + task.getPriorityId() +
                ", IS_COMPLETED = " + task.isCompleted() +
                " WHERE USER_ID =" + userId +
                " and  TASK_ID=" + taskId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        logger.info(task.toString());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        logger.info("tasks updated");
    }

    @Override
    public Task getTaskById(int taskId) throws ClassNotFoundException, SQLException {
        String query = "SELECT TASK_ID, TASK_NAME, DEADLINE, PRIORITY_ID, IS_COMPLETED FROM TASKS WHERE TASK_ID="+ taskId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        Task task=null;
        if(resultset.next()){
            task = new Task(Integer.parseInt(resultset.getString("taskId")),
                    resultset.getString("task_name"),
                    resultset.getString("deadline"),
                    Integer.parseInt(resultset.getString("priority_id")));
            task.setCompleted(resultset.getString("is_completed").equals("1"));
        }
        return task;
    }

    @Override
    public List<Task> getAllTasksOfUser(int userId) throws ClassNotFoundException, SQLException {
        String query = "SELECT TASK_ID, TASK_NAME, DEADLINE, PRIORITY_ID, IS_COMPLETED FROM TASKS WHERE USER_ID="+ userId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        List<Task> tasks=new ArrayList<>();
        while(resultset.next()){
            Task task = new Task(Integer.parseInt(resultset.getString("task_Id")),
                    resultset.getString("task_name"),
                    resultset.getString("deadline"),
                    Integer.parseInt(resultset.getString("priority_id")));
            task.setCompleted(resultset.getString("is_completed").equals("1"));
            logger.info("task found");
            tasks.add(task);
        }
        logger.info("returned tasks");
        return tasks;
    }

    @Override
    public void setTaskDone(int taskId, int userId) throws ClassNotFoundException, SQLException {
        String query = "UPDATE TASKS SET IS_COMPLETED=TRUE WHERE USER_ID =" + userId + " and  TASK_ID="+ taskId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        logger.info("checked task");
    }

    @Override
    public void setTaskNotDone(int taskId, int userId) throws ClassNotFoundException, SQLException {
        String query = "UPDATE TASKS SET IS_COMPLETED= FALSE WHERE USER_ID =" + userId + " and  TASK_ID="+ taskId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        logger.info("unchecked task");
    }
}
