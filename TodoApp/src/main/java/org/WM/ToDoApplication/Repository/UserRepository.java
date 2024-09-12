package org.WM.ToDoApplication.Repository;

import org.WM.ToDoApplication.Models.User;

import java.sql.SQLException;

public interface UserRepository {
    void addUser(User user) throws SQLException, ClassNotFoundException;
}
