package com.reigasm.simplerestapi.service;

import com.reigasm.simplerestapi.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static List<UserModel> users= new ArrayList<>();

    static {
        UserModel user1 = new UserModel("1","usr1", "ABC19912710USER001", "19912710");
        UserModel user2 = new UserModel("2","usr2", "ABC19912710USER002", "19912711");
        UserModel user3 = new UserModel("3","usr3", "ABC19912710USER003", "19912712");
        UserModel user4 = new UserModel("4","usr4", "ABC19912710USER004", "19912713");
        UserModel user5 = new UserModel("5","usr5", "ABC19912710USER005", "19912714");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    @Override
    public List<UserModel> getUserList(){
        return this.users;
    }
}
