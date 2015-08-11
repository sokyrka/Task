package com.sokirka.task.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sokirka.task.shared.User;

import java.util.List;

/**
 * @author Eugine Sokirka.
 */
@RemoteServiceRelativePath("usersService")
public interface UsersService extends RemoteService {
    List<User> getUsers();
    User addUser(User user);
}
