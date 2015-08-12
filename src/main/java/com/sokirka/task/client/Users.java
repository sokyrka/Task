package com.sokirka.task.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Eugine Sokirka.
 */
public class Users implements EntryPoint {

    @Override
    public void onModuleLoad() {
        AppController appViewer = new AppController();
        appViewer.go(RootPanel.get());
    }
}
