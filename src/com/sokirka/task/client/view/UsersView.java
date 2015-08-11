package com.sokirka.task.client.view;

import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
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

    public UsersView() {
        List<User> userList = Arrays.asList(new User(1, "Q", "W", "E", Role.ADMIN),
                                            new User(2, "A", "S", "D", Role.USER),
                                            new User(3, "X", "Y", "Z", Role.ADMIN));

        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);

        SplitLayoutPanel panel = new SplitLayoutPanel();
        panel.setSize("500", "200");
        contentTableDecorator.add(panel);

        DataGrid<User> dataGrid = new DataGrid<>();
        dataGrid.setWidth("500");
        dataGrid.setHeight("200");
        dataGrid.setRowData(0, userList);

        final MultiSelectionModel<User> selectionModel = new MultiSelectionModel<User>(new ProvidesKey<User>() {
            @Override
            public Object getKey(User item) {
                return item.getId();
            }
        });

        dataGrid.setSelectionModel(selectionModel);

        /*Column<User, Boolean> checkColumn =
                new Column<User, Boolean>(new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(User object) {
                        return selectionModel.isSelected(object);
                    }
                };

        dataGrid.addColumn(checkColumn);
        dataGrid.setColumnWidth(checkColumn, 40, Style.Unit.PX);*/

        TextColumn<User> textColumnID = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return String.valueOf(object.getId());
            }
        };
        dataGrid.addColumn(textColumnID, "ID");

        TextColumn<User> textColumnName = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getName();
            }
        };
        dataGrid.addColumn(textColumnName, "Name");

        List<String> roles = Arrays.asList(Role.ADMIN.name(), Role.USER.name());

        SelectionCell categoryCell = new SelectionCell(roles);
        Column<User, String> categoryColumn = new Column<User, String>(categoryCell) {
            @Override
            public String getValue(User object) {
                return object.getRole().name();
            }
        };
        dataGrid.addColumn(categoryColumn, "Role");

        Label labelEmail = new Label("Email");
        Label labelSurName = new Label("Surname");
        VerticalPanel verticalWestPanel = new VerticalPanel();
        verticalWestPanel.add(labelEmail);
        verticalWestPanel.add(labelSurName);

        CheckBox disableCheckbox = new CheckBox("Disable Checkbox");
        CheckBox disableSelection = new CheckBox("Disable Selection");

        Button buttonGo = new Button("Go");
        buttonGo.setStyleName("btn-go");

        VerticalPanel verticalEastPanel = new VerticalPanel();
        verticalEastPanel.add(disableCheckbox);
        verticalEastPanel.add(disableSelection);
        verticalEastPanel.add(buttonGo);

        addButton = new Button("Click");
        panel.addNorth(dataGrid, 120);
        panel.addWest(verticalWestPanel, 242);
        panel.addEast(verticalEastPanel, 242);
    }

    @Override
    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public Widget asWidget() {
        return this;
    }
}
