package com.imas.web.search.model;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.imas.model.Advertisement;
import com.imas.services.interfaces.AdvertisementService;

public class LoadableAdvertisementModel extends LoadableDetachableModel<Advertisement> {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "advertisementSearchService")
	private AdvertisementService advertisementService;

	private Long advertId;

	public LoadableAdvertisementModel(Long id) {
		InjectorHolder.getInjector().inject(this);
		this.advertId = id;
	}

	@Override
	protected Advertisement load() {
		if (advertId == null)
			return new Advertisement();
		else
			return advertisementService.findById(advertId);
	}
}
