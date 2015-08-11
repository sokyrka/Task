package com.sokirka.task.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sokirka.task.shared.User;

import java.util.ArrayList;

/**
 * @author Eugine Sokirka.
 */
@RemoteServiceRelativePath("usersService")
public interface UsersService extends RemoteService {
    ArrayList<User> getUsers();
}
