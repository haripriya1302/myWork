package repositories;

import error.OrderingSystemException;
import models.ErrorCode;
import models.User;
import utils.ErrorUtil;

import java.util.HashMap;

public class UserRepository {
    public HashMap<String, User> admins = new HashMap<>();
    public HashMap<String, User> customers = new HashMap<>();

    private UserRepository userRepository = null;

    public UserRepository getInstance() {
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }


    public void addAdmin(String id, User user) {
        admins.put(id, user);
        //Admin is also a customer.
        customers.put(id, user);
    }

    public void removeAdmin(String id) {
        admins.remove(id);
    }

    public void addCustomer(String id, User user) {
        customers.put(id, user);
    }

    public void removeCustomer(String id) {
        customers.remove(id);
    }

    public boolean isAdmin(String id) {
        return admins.containsKey(id)? true : false;
    }
    public boolean isValidCustomer(String id) {
        return customers.containsKey(id)? true : false;
    }

    public User getUser(String id){
        if(customers.containsKey(id)) {
            return customers.get(id);
        }
        throw new OrderingSystemException(ErrorCode.USER_DOES_NOT_EXIST,
                ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.USER_DOES_NOT_EXIST));
    }

}
