package com.sokirka.task.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sokirka.task.client.event.AddUserEvent;
import com.sokirka.task.client.event.AddUserEventHandler;
import com.sokirka.task.client.presenter.Presenter;
import com.sokirka.task.client.presenter.UsersPresenter;
import com.sokirka.task.client.view.UsersView;

/**
 * @author Eugine Sokirka.
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private final UsersServiceAsync rpcService;
    private HasWidgets container;

    public AppController(HandlerManager eventBus, UsersServiceAsync rpcService) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    public void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(AddUserEvent.TYPE, new AddUserEventHandler() {
            @Override
            public void onAddUser(AddUserEvent event) {
                doAddNewUser();
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("add");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    private void doAddNewUser() {
        History.newItem("add");
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            if (token.equals("add")) {
                presenter = new UsersPresenter(rpcService, eventBus, new UsersView());
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
