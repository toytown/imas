package com.imas.dao.impl;

import java.io.Serializable;

/**
 * Base class for all search filters, encapsulating the pageNumber and pageSize properties.
 * The page number is 1-based, i.e. the first N results are on page 1.
 */
public abstract class SearchFilter implements Serializable {

    private static final long serialVersionUID = 8345119771784776314L;
    
    private int pageNumber = -1;
    private int pageSize = -1;
    private boolean performSearch;
    
    /**
     * method invoked by pressing the Reset button in search form. In implementation reset all search fields to their default values
     */
    public abstract void reset();
    
    /**
     * Returns true, if this filter requires the pagination, i.e. pageNumber and pageSize properties have been set 
     */
    public boolean isPaginationSet() {
        return pageNumber > 0 && pageSize > 0;
    }
    
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPerformSearch(boolean performSearch) {
        this.performSearch = performSearch;
    }

    public boolean isPerformSearch() {
        return performSearch;
    }

}
