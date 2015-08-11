package com.sokirka.task.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Eugine Sokirka.
 */
public class AddUserEvent extends GwtEvent<AddUserEventHandler> {
    public static Type<AddUserEventHandler> TYPE = new Type<>();

    @Override
    public Type<AddUserEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AddUserEventHandler handler) {
        handler.onAddUser(this);
    }
}
