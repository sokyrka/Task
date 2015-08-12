package com.sokirka.task.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Eugine Sokirka.
 */
public class UsersPresenter implements Presenter {

    public interface Display {
        Widget asWidget();
    }

    private final Display display;

    public UsersPresenter(Display display) {
        this.display = display;
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
