package service.impl;

import models.User;
import models.UserType;
import repositories.UserRepository;
import service.UserService;
import utils.RestaurantUtil;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RestaurantUtil restaurantUtil;

    private static UserServiceImpl userService = null;

    public static UserServiceImpl getInstance() {
        if(userService == null) {
            return new UserServiceImpl();
        } else {
            return userService;
        }
    }

    public UserServiceImpl(){
        this.userRepository = UserRepository.getInstance();
        this.restaurantUtil = RestaurantUtil.getInstance();
    }

    @Override
    public boolean isUserAdmin(int userId) {
        User user = userRepository.getUser(userId);
        if(user.getUserType() == UserType.ADMIN) {
            return true;
        }
        return false;
    }

    @Override
    public int createUser(String name, UserType userType) {
        User user = new User(name, userType);
        int id = user.getId();
        userRepository.addUser(id, user);
        return id;
    }
}
