package org.WM.ToDoApplication.Repository.impl;

import org.WM.ToDoApplication.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserValidationRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserValidationRepository.class);
    private static UserValidationRepository userValidationRepository;
    private UserValidationRepository(){
        //Making class unable to instantiate more than once
    }

    public static UserValidationRepository getInstance(){
        if(userValidationRepository ==null) userValidationRepository =new UserValidationRepository();
        return userValidationRepository;
    }

    public int validateUser(User user){
        String query = "SELECT USER_ID FROM USERS WHERE USERNAME = '" + user.getUserName() + "' AND PASSWORD ='" + user.getPassword() + "'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    Database.url, Database.userName, Database.password
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                logger.info("User validated successfully");
                logger.info(resultSet.getString("USER_ID"));
                return (Integer.parseInt(resultSet.getString("USER_ID")));
            }
            logger.info("Incorrect username or password");
        } catch (Exception e) {
            logger.error("exception", e);
        }
        return 0;
    }
}
