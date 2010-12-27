package com.imas.dao.interfaces;

import java.util.List;

import com.imas.model.Category;
import com.imas.model.CategoryType;
import com.imas.model.HeatingType;

public interface CommonDao {
	
	public List<CategoryType> getTypes();

	public List<HeatingType> getHeatingTypes();
	
	public List<Category> getCategories();
	
	public List<String> findCityByZipCode(String zipCode);	
}
