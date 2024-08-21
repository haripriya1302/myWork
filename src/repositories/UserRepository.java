package repositories;

import exceptions.RestaurantServiceException;
import models.ErrorCode;
import models.User;
import utils.ErrorUtil;

import java.util.HashMap;

public class UserRepository {

    private HashMap<Integer, User> users = new HashMap<>();

    private static UserRepository userRepository = null;
    public static UserRepository getInstance() {
        if(userRepository == null) {
            return new UserRepository();
        }
        return userRepository;
    }
    public void addUser(int id, User user){
        users.put(id, user);
    }

    public void updateUser(int id, User user) {
        if(doesUserExist(id)) {
            users.put(id, user);
        } else {
            new RestaurantServiceException(ErrorCode.USER_DOESNT_EXIST,
                    ErrorUtil.errorCodeHashMap.get(ErrorCode.USER_DOESNT_EXIST));
        }
    }

    private boolean doesUserExist(int id) {
        return users.keySet().contains(id);
    }


    public User getUser(int userId) {
        return users.get(userId);
    }
}
