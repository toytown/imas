package com.imas.web.components.html.pagination;

import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;



/**
 * Search result table component encapsulating a {@link DataTable} as well as {@link ResultTablePagingNavigator} in one easy-to-use class.
 */
public class SearchResultTable<T> extends Panel {
    private static final long serialVersionUID = 1L;
    private DataTable<T> resultTable;
    private DataView<T> resultView;
    private ResultTablePagingNavigator<T> pagingNavigator;

    
    public DataTable<T> getResultTable() {
        return resultTable;
    }

    public void setResultTable(DataTable<T> resultTable) {
        this.resultTable = resultTable;
    }

    
    public DataView<T> getResultView() {
        return resultView;
    }

    public void setResultView(DataView<T> resultView) {
        this.resultView = resultView;
    }

    @SuppressWarnings("unchecked")
    public SearchResultTable(String id, List<IColumn<?>> columns, IDataProvider<T> dataProvider, int rowsPerPage) {
        super(id);
        
        resultTable = new DataTable<T>("resultTable", columns.toArray(new IColumn[0]), dataProvider, rowsPerPage){
            private static final long serialVersionUID = 1L;

            @Override
            protected Item<T> newRowItem(String id, int index, IModel<T> model) {
                Item<T> row = super.newRowItem(id, index, model);
                row.add(new SimpleAttributeModifier("class", index % 2 != 0 ? "even" : "odd"));
                return row;
            }
        };
        
        resultTable.addTopToolbar(new HeadersToolbar(resultTable, null));
        resultTable.setVisible(false);
        
        pagingNavigator = new ResultTablePagingNavigator<T>("navigator", resultTable, dataProvider, rowsPerPage);
        pagingNavigator.setVisible(false);
        
        add(pagingNavigator.setOutputMarkupId(true));
        add(resultTable.setOutputMarkupId(true));
    }

    /**
     * For DataView
     * 
     * @param id
     * @param dataProvider
     * @param rowsPerPage
     */
    public SearchResultTable(String id, IDataProvider<T> dataProvider, int rowsPerPage) {
        super(id);
        
        resultView.setVisible(false);
        
        pagingNavigator = new ResultTablePagingNavigator<T>("navigator", resultView, dataProvider, rowsPerPage);
        pagingNavigator.setVisible(false);
        
        add(pagingNavigator.setOutputMarkupId(true));
        add(resultView.setOutputMarkupId(true));
    }
    
    /**
     * the method to be triggered by a Search button
     */
    public void onSearchNew() {
        resultView.modelChanged();
        resultView.setVisible(true);
        
        pagingNavigator.getPageable().setCurrentPage(0);
        pagingNavigator.modelChanged();
        pagingNavigator.setVisible(true);
    }
    
    /**
     * the method to be triggered by a Search button
     */
    public void onSearch() {
        resultTable.modelChanged();
        resultTable.setVisible(true);
        
        pagingNavigator.getPageable().setCurrentPage(0);
        pagingNavigator.modelChanged();
        pagingNavigator.setVisible(true);
    }

}
