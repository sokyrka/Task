package com.sokirka.task.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Eugine Sokirka.
 */
public interface AddUserEventHandler extends EventHandler {
    void onAddUser(AddUserEvent event);
}
