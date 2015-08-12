package com.sokirka.task.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
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
