package com.imas.web.search.dataprovider;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.imas.model.Advertisement;
import com.imas.services.interfaces.AdvertisementService;
import com.imas.valueobjects.AdvertisementSearchFilter;
import com.imas.web.search.model.LoadableAdvertisementModel;


public class AdvertisementSearchDataProvider extends SortableDataProvider<Advertisement>{

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AdvertisementService advertisementService;
    
    private AdvertisementSearchFilter searchFilter;
    
    
    public AdvertisementSearchDataProvider(AdvertisementSearchFilter searchFilter) {
        super();
        InjectorHolder.getInjector().inject(this);
        this.searchFilter = searchFilter;
    }

    @Override
    public Iterator<? extends Advertisement> iterator(int first, int count) {
        if (!searchFilter.isPerformSearch()) {
            List<? extends Advertisement> emptyList = Collections.emptyList();
            return emptyList.iterator();
        }
        if (searchFilter.isPaginationSet()) {
            searchFilter.setPageNumber(first/searchFilter.getPageSize() + 1);
        } else {
            searchFilter.setPageNumber(0);
        }
        
        return advertisementService.findAdvertisement(searchFilter).iterator();
    }

    @Override
    public int size() {
        return searchFilter.isPerformSearch() ? advertisementService.countByFilter(searchFilter) : 0;
    }

    @Override
    public IModel<Advertisement> model(Advertisement advertisement) {
        return new LoadableAdvertisementModel(advertisement.getId());
    }

}
