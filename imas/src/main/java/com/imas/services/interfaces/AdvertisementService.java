package com.imas.services.interfaces;

import java.util.List;

import com.imas.model.Advertisement;
import com.imas.model.Images;
import com.imas.valueobjects.AdvertisementSearchFilter;

public interface AdvertisementService {

	public void save(Advertisement appartment);
	
	public Advertisement findById(Long id);
	
	public Advertisement update(Advertisement appartment);
	
	public void delete(Advertisement advert);
	
	public List<Advertisement> findAdvertByPostalCode(String zip);
	
	public List<Advertisement> findAdvertisement(AdvertisementSearchFilter searchRequest);
	
	public int countByFilter(AdvertisementSearchFilter searchRequest);
	
	public Images findRealStateImageById(long id);

}
