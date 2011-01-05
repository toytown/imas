package com.imas.dao.interfaces;

import java.util.List;

import com.imas.model.Advertisement;
import com.imas.model.Images;
import com.imas.valueobjects.AdvertisementSearchFilter;

public interface AdvertisementDao extends GenericDao<Advertisement, Long> {

	public List<Advertisement> findAdvertBySearchCriteria(AdvertisementSearchFilter searchRequest);	
	
	public int countByFilter(AdvertisementSearchFilter searchRequest);
	
	public Images findImageById(long id);
	
	public List<Advertisement> findAdvertByPostalCode(String zip);

}
