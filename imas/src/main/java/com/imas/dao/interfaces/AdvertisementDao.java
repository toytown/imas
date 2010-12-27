package com.imas.dao.interfaces;

import java.util.List;

import com.imas.model.Advertisement;
import com.imas.model.Images;
import com.imas.valueobjects.SearchRequest;

public interface AdvertisementDao extends GenericDao<Advertisement, Long> {

	public Advertisement findRealStateAdvertById(Long appartmentId);
	
	public List<Advertisement> findAllAppartments(String zipCode, String city);
	
	public List<String> findCities(String searchStr);
	
	public Advertisement save(Advertisement advert);
	
	public Advertisement update(Advertisement advert);
	
	public void delete(Advertisement advert);
	
	public List<Advertisement> findRealStateBySearchCriteria(SearchRequest searchRequest);	
	
	public Images findImageById(long id);
	
	public void deleteImage(Images appartmentImage);
}
