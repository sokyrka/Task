package com.sokirka.task.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sokirka.task.shared.User;

import java.util.List;

/**
 * @author Eugine Sokirka.
 */
public interface UsersServiceAsync {
    void getUsers(AsyncCallback<List<User>> async);

    void addUser(User user, AsyncCallback<User> async);
}
