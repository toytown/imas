package com.imas.services.interfaces;

import java.util.List;

import com.imas.model.Advertisement;
import com.imas.model.Images;
import com.imas.valueobjects.SearchRequest;

public interface AdvertisementService {

	public void save(Advertisement appartment);
	
	public Advertisement findById(Long id);
	
	public List<String> findCities(String searchStr);	
	
	public Advertisement update(Advertisement appartment);
	
	public void delete(Advertisement advert);
	
	public List<Advertisement> findRealStatetByZip(String zip);
	
	public List<Advertisement> findAdvertisement(SearchRequest searchRequest);
	
	public Images findRealStateImageById(long id);
	
	public void deleteImage(Images appartmentImage);
}
