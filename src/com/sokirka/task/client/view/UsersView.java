package com.sokirka.task.client.view;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;
import com.sokirka.task.client.presenter.UsersPresenter;
import com.sokirka.task.client.view.custom.StyleUtils;
import com.sokirka.task.client.view.custom.UsersCheckBoxHeader;
import com.sokirka.task.shared.Role;
import com.sokirka.task.shared.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Eugine Sokirka.
 */
public class UsersView extends Composite implements UsersPresenter.Display {

    ListDataProvider<User> userDataProvider = new ListDataProvider<User>();
    private Set<User> selectedRows = new HashSet<User>();
    MultiSelectionModel<User> selectionModel = new MultiSelectionModel<User>(KEY_PROVIDER);
    public static ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>() {
        @Override
        public Object getKey(User item) {
            return item.getId();
        }
    };

    private final Button addButton;
    final DataGrid<User> dataGrid;
    final CheckBox disableSelection;
    final CheckBox cbCheckboxes;
    final Button buttonGo;

    public UsersView() {
        List<User> userList = Arrays.asList(new User(1, "Eugene", "Sokirka", "esokirka@gmail.com", Role.ADMIN),
                new User(2, "John", "Dou", "jdou@yahoo.com", Role.USER),
                new User(3, "Tom", "Smith", "tsmith@hotmail.com", Role.ADMIN));

        DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);

        SplitLayoutPanel panel = new SplitLayoutPanel();
        panel.setSize("500", "200");
        contentTableDecorator.add(panel);

        disableSelection = new CheckBox("Disable Selection");
        cbCheckboxes = new CheckBox();
        buttonGo = new Button("Go");
        buttonGo.setStyleName("btn-go");
        buttonGo.setEnabled(false);

        dataGrid = new DataGrid<User>();
        dataGrid.setWidth("500");
        dataGrid.setHeight("200");
        dataGrid.setRowData(0, userList);
        dataGrid.setSelectionModel(selectionModel);

        final Column<User, Boolean> checkColumn =
                new Column<User, Boolean>(new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(User object) {
                        return selectedRows.contains(object);
                    }

                    @Override
                    public void render(Cell.Context context, User object, SafeHtmlBuilder sb) {
                        if (cbCheckboxes.getValue()) {
                            if (selectedRows.contains(object))
                                sb.appendHtmlConstant(StyleUtils.DISABLED_CHECKED_CHECKBOX);
                            else
                                sb.appendHtmlConstant(StyleUtils.DISABLED_CHECKBOX);
                        } else
                            super.render(context, object, sb);
                    }
                };
        checkColumn.setFieldUpdater(new FieldUpdater<User, Boolean>() {
            @Override
            public void update(int index, User object, Boolean value) {
                if (value)
                    selectedRows.add(object);
                else
                    selectedRows.remove(object);
                buttonGo.setEnabled(!selectedRows.isEmpty());
                dataGrid.redraw();
            }
        });

        TextColumn<User> textColumnID = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return String.valueOf(object.getId());
            }
        };

        TextColumn<User> textColumnName = new TextColumn<User>() {
            @Override
            public String getValue(User object) {
                return object.getName();
            }
        };

        final List<String> roles = Arrays.asList(Role.ADMIN.name(), Role.USER.name());
        SelectionCell categoryCell = new SelectionCell(roles);
        Column<User, String> categoryColumn = new Column<User, String>(categoryCell) {
            @Override
            public String getValue(User object) {
                return object.getRole().name();
            }
        };
        categoryColumn.setFieldUpdater(new FieldUpdater<User, String>() {
            @Override
            public void update(int index, User object, String value) {
                for (String role : roles) {
                    if (role.equals(value)) {
                        object.setRole(Role.valueOf(role));
                    }
                }
            }
        });

        UsersCheckBoxHeader usersCheckBoxHeader = new UsersCheckBoxHeader(selectedRows, userDataProvider, dataGrid, buttonGo) {
            @Override
            public void render(Cell.Context context, SafeHtmlBuilder sb) {
                if (cbCheckboxes.getValue()) {
                    if (selectedRows.size() == userDataProvider.getList().size())
                        sb.appendHtmlConstant(StyleUtils.DISABLED_CHECKED_CHECKBOX);
                    else
                        sb.appendHtmlConstant(StyleUtils.DISABLED_CHECKBOX);
                } else
                    super.render(context, sb);
            }
        };

        dataGrid.addColumn(checkColumn, usersCheckBoxHeader);
        dataGrid.setColumnWidth(checkColumn, 40, Style.Unit.PX);
        dataGrid.addColumn(textColumnID, "ID");
        dataGrid.addColumn(textColumnName, "Name");
        dataGrid.addColumn(categoryColumn, "Role");



        final Label labelEmail = new Label("Email : ");
        final Label labelSurName = new Label("Surname : ");
        final Label labelEmailData = new Label();
        final Label labelSurNameData = new Label();

        VerticalPanel verticalLeftPanel = new VerticalPanel();
        verticalLeftPanel.add(labelEmail);
        verticalLeftPanel.add(labelSurName);

        VerticalPanel verticalRightPanel = new VerticalPanel();
        verticalRightPanel.setStyleName("vertical-panel");
        verticalRightPanel.add(labelEmailData);
        verticalRightPanel.add(labelSurNameData);

        HorizontalPanel horizontalWestPanel = new HorizontalPanel();
        horizontalWestPanel.add(verticalLeftPanel);
        horizontalWestPanel.add(verticalRightPanel);

        CheckBox disableCheckbox = new CheckBox("Disable Checkbox");

        VerticalPanel verticalEastPanel = new VerticalPanel();
        verticalEastPanel.add(disableCheckbox);
        verticalEastPanel.add(this.disableSelection);
        verticalEastPanel.add(buttonGo);

        addButton = new Button("Click");
        panel.addNorth(dataGrid, 120);
        panel.addWest(horizontalWestPanel, 242);
        panel.addEast(verticalEastPanel, 242);


        buttonGo.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

            }
        });

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                String usersEmail = selectionModel.getSelectedSet().isEmpty() ? " " :
                        " " + selectionModel.getSelectedSet().iterator().next().getEmail();
                String usersSurname = selectionModel.getSelectedSet().isEmpty() ? " " :
                        " " + selectionModel.getSelectedSet().iterator().next().getSurName();
                labelSurNameData.setText(usersSurname);
                labelEmailData.setText(usersEmail);
            }
        });

        cbCheckboxes.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                dataGrid.redraw();
            }
        });

        disableSelection.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (event.getValue()) {
                    dataGrid.setSelectionModel(selectionModel,
                            DefaultSelectionEventManager.<User>createWhitelistManager());
                } else
                    dataGrid.setSelectionModel(selectionModel,
                            DefaultSelectionEventManager.<User>createDefaultManager());
                dataGrid.redraw();
            }
        });

    }


    @Override
    public HasClickHandlers getAddButton() {
        return addButton;
    }

    public Widget asWidget() {
        return this;
    }
}
