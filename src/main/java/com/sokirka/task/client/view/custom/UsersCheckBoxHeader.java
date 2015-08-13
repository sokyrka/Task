package com.sokirka.task.client.view.custom;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.ListDataProvider;
import java.util.Set;

/**
 * @author Eugine Sokirka.
 */
public class UsersCheckBoxHeader<T> extends Header {

    private final Set<T> selectionModel;
    private final ListDataProvider<T> dataProvider;
    private DataGrid<T> dataGrid;
    private Button button;

    @SuppressWarnings("unchecked")
    public UsersCheckBoxHeader(Set selectionModel, ListDataProvider dataProvider, DataGrid dataGrid, Button button) {
        super(new CheckboxCell());
        this.selectionModel = selectionModel;
        this.dataProvider = dataProvider;
        this.dataGrid = dataGrid;
        this.button = button;
    }

    @Override
    public Boolean getValue() {
        return selectionModel.size() == dataProvider.getList().size();
    }

    @Override
    public void onBrowserEvent(Cell.Context context, Element elem, NativeEvent event) {
        InputElement input = elem.getFirstChild().cast();
        Boolean isChecked = input.isChecked();
        for (T element : dataProvider.getList()) {
            if(isChecked)
                selectionModel.add(element);
            else
                selectionModel.remove(element);
        }
        button.setEnabled(isChecked);
        dataGrid.redraw();
    }
}
