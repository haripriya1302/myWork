package service;

import models.UserType;

public interface UserService {
    boolean isUserAdmin(int userId);
    int createUser(String name, UserType userType);
}
