package com.sokirka.task.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Eugine Sokirka.
 */
public class Users implements EntryPoint {

    @Override
    public void onModuleLoad() {
        UsersServiceAsync rpcService = GWT.create(UsersService.class);
        HandlerManager eventBus = new HandlerManager(null);
        AppController appViewer = new AppController(eventBus, rpcService);
        appViewer.go(RootPanel.get());
    }
}
