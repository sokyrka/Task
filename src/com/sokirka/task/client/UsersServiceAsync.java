package com.sokirka.task.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sokirka.task.shared.User;

import java.util.ArrayList;

/**
 * @author Eugine Sokirka.
 */
public interface UsersServiceAsync {
    void getUsers(AsyncCallback<ArrayList<User>> async);
}
