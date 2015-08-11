package com.sokirka.task.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sokirka.task.client.UsersServiceAsync;
import com.sokirka.task.client.event.AddUserEvent;

/**
 * @author Eugine Sokirka.
 */
public class UsersPresenter implements Presenter {


    public interface Display {
        HasClickHandlers getAddButton();
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

    public void bind(){
        display.getAddButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new AddUserEvent());
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
    }
}
