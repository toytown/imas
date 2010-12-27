package com.imas.services.interfaces;

import java.util.List;

import com.imas.model.CategoryType;
import com.imas.model.HeatingType;

public interface CommonService {

	public List<CategoryType> getCategoryTypes();
	
	public List<HeatingType> getHeatingTypes();
	
	
}
