package com.sokirka.task.client.view.custom;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.sokirka.task.model.User;

import java.util.Set;

/**
 * @author Eugine Sokirka.
 */
public class SelectedUsersPopup extends DialogBox {

    public SelectedUsersPopup(Set<User> selectedUsers) {
        setText("Selected Users");
        setAnimationEnabled(true);
        setGlassEnabled(true);

        Button buttonClose = new Button("Close");
        buttonClose.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SelectedUsersPopup.this.hide();
            }
        });

        VerticalPanel panel = new VerticalPanel();
        panel.setHeight("100");
        panel.setWidth("300");
        panel.setSpacing(10);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        for(User user : selectedUsers){
            Label label = new Label("ID - " + String.valueOf(user.getId()));
            panel.add(label);
        }
        panel.add(buttonClose);

        setWidget(panel);
    }
}
