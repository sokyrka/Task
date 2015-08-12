package com.sokirka.task.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sokirka.task.client.presenter.Presenter;
import com.sokirka.task.client.presenter.UsersPresenter;
import com.sokirka.task.client.view.UsersView;

/**
 * @author Eugine Sokirka.
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

    private HasWidgets container;

    public AppController() {
        bind();
    }

    public void bind() {
        History.addValueChangeHandler(this);
    }

    @Override
    public void go(HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("show");
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

            if (token.equals("show")) {
                presenter = new UsersPresenter(new UsersView());
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
