package com.sokirka.task.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sokirka.task.client.UsersService;
import com.sokirka.task.shared.Role;
import com.sokirka.task.shared.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugine Sokirka.
 */
public class UsersServiceImpl extends RemoteServiceServlet implements UsersService {

    private final List<User> userList = new ArrayList<>();

    public UsersServiceImpl(){
        initUserList();
    }

    private void initUserList(){
        for(int i = 0; i < 5; i++){
            userList.add(new User(i, "Name " + i, "SurName " + i, "example@gmail.com", i%2 == 0 ? Role.ADMIN : Role.USER));
        }
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }

    @Override
    public User addUser(User user) {
        userList.add(user);
        return user;
    }
}
