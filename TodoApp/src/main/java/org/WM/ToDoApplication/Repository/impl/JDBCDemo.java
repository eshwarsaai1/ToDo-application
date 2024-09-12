package org.WM.ToDoApplication.Repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseTaskRepository.class);

    public static void main(String[] args){
        String query = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('ANIL' , 'ANIL@21')";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    Database.url, Database.userName, Database.password
            );
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            logger.info("Task added successfully");
        } catch (Exception e) {
            logger.error("exception", e);
        }
    }
}