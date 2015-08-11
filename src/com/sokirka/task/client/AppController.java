package com.sokirka.task.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sokirka.task.client.presenter.Presenter;

/**
 * @author Eugine Sokirka.
 */
public class AppController implements Presenter {

    private final HandlerManager eventBus;
    private final UsersServiceAsync rpcService;
    private HasWidgets container;

    public AppController(HandlerManager eventBus, UsersServiceAsync rpcService) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    public void bind() {

    }

    @Override
    public void go(HasWidgets container) {
        this.container = container;
    }
}
