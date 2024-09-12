package org.WM.ToDoApplication.Repository.impl;

import org.WM.ToDoApplication.Models.User;
import org.WM.ToDoApplication.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InDatabaseUserRepository implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseTaskRepository.class);
    private static InDatabaseUserRepository inDatabaseUserRepository;
    private Connection connection;
    private InDatabaseUserRepository(){
        //Making private to prevent class cannot instantiatable
    }

    public static InDatabaseUserRepository getInstance(){
        if(inDatabaseUserRepository == null){
            inDatabaseUserRepository = new InDatabaseUserRepository();
        }
        return inDatabaseUserRepository;
    }

    @Override
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO USERS (USERNAME , PASSWORD) VALUES('" + user.getUserName() + "', '" + user.getPassword() + "')";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
