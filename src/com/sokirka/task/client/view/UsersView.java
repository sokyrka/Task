package com.sokirka.task.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.sokirka.task.client.presenter.UsersPresenter;
import com.sokirka.task.shared.Role;
import com.sokirka.task.shared.User;

import java.util.Arrays;
import java.util.List;

/**
 * @author Eugine Sokirka.
 */
public class UsersView extends Composite implements UsersPresenter.Display {
    private final Button addButton;
    private final FlexTable contentTable;

    public UsersView() {
        List<User> userList = Arrays.asList(new User(1, "Q", "W", "E", Role.ADMIN), new User(2, "A", "S", "D", Role.USER), new User(3, "X", "Y", "Z", Role.ADMIN));

        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);

        contentTable = new FlexTable();
        contentTable.setWidth("100%");
        contentTable.getCellFormatter().setWidth(0, 0, "100%");
        contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);

        DataGrid<User> dataGrid = new DataGrid<>();
        dataGrid.setWidth("300");
        dataGrid.setHeight("200");
        dataGrid.setRowData(0, userList);

        TextColumn<User> textColumnName = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getName();
            }
        };

        TextColumn<User> textColumnSurName = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getSurName();
            }
        };

        TextColumn<User> textColumnEmail = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getEmail();
            }
        };

        dataGrid.addColumn(textColumnName, "Name");
        dataGrid.addColumn(textColumnSurName, "SurName");
        dataGrid.addColumn(textColumnEmail, "Email");

        addButton = new Button("Click");
        contentTableDecorator.add(dataGrid);
    }

    @Override
    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public Widget asWidget() {
        return this;
    }
}
