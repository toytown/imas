package com.imas.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imas.dao.interfaces.CommonDao;
import com.imas.model.CategoryType;
import com.imas.model.HeatingType;
import com.imas.services.interfaces.CommonService;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

    @Autowired
	private CommonDao commonDao;

	public List<HeatingType> getHeatingTypes() {
		return commonDao.getHeatingTypes();
	}

	public List<CategoryType> getCategoryTypes() {
		return commonDao.getTypes();
	}

}
