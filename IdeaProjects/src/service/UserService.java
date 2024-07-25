package service;

import models.UserType;

public interface UserService {
    String createUser(String name, UserType userType);
}
