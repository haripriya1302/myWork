package service.impl;

import models.User;
import models.UserType;
import repositories.UserRepository;
import service.UserService;
import utils.CommonUtil;

import java.util.HashSet;

public class UserServiceImpl implements UserService {

    private CommonUtil commonUtil;

    private UserRepository userRepository;

    public UserServiceImpl(CommonUtil commonUtil, UserRepository userRepository) {
        this.commonUtil = commonUtil;
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(String name, UserType userType) {
        User user = new User();
        String id = commonUtil.generateUUID();
        user.setId(id);
        user.setUserType(userType);
        user.setName(name);
        user.setOrderIds(new HashSet<>());
        if(userType == UserType.ADMIN) {
            userRepository.addAdmin(id, user);
        } else {
            userRepository.addCustomer(id, user);
        }
        return id;
    }
}
