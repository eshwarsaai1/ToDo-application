package org.WM.ToDoApplication.Services;

import org.WM.ToDoApplication.Models.User;
import org.WM.ToDoApplication.Repository.impl.UserValidationRepository;

public class UserValidationService {
    private static UserValidationService userValidationService;
    private static final UserValidationRepository USER_VALIDATION_REPOSITORY = UserValidationRepository.getInstance();

    private UserValidationService(){
        //
    }

    public static UserValidationService getInstance(){
        if(userValidationService == null) userValidationService=new UserValidationService();
        return userValidationService;
    }

    public int validateUser(User user){
        return USER_VALIDATION_REPOSITORY.validateUser(user);
    }
}
