package com.sokirka.task.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sokirka.task.client.UsersServiceAsync;

/**
 * @author Eugine Sokirka.
 */
public class UsersPresenter implements Presenter {


    public interface Display {
        Widget asWidget();
    }

    private final UsersServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public UsersPresenter(UsersServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
    }

    @Override
    public void go(HasWidgets container) {

    }
}
