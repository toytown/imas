package com.imas.dao.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.imas.dao.interfaces.AdvertisementDao;
import com.imas.dao.interfaces.CommonDao;
import com.imas.model.Advertisement;
import com.imas.model.User;
import com.imas.test.utils.IMASTestCase;

public class AdvertisementDaoImplTest extends IMASTestCase {

	@Resource
	private AdvertisementDao advertisementDao;

	@Resource
	private CommonDao commonDao;

	@Test
	@Transactional
	@Rollback(true)
	public void testSave() throws IOException {
		Advertisement appartment = new Advertisement();
		appartment.setTitleDescription("2 room spacious, central, peaceful");
		appartment.setTotalRooms(2.0d);
		appartment.setAreaCode("10337");
		appartment.setBuiltYear(1940);
		appartment.setBedRooms(Short.valueOf("2"));
		appartment.setSize(55.50);
		appartment.setCategoryType(commonDao.getTypes().get(0));
		appartment.setHeatingType(commonDao.getHeatingTypes().get(0));
		appartment.setCost(400.50d);
		User user = super.createTestUser("test-save-username");
		appartment.setUser(user);
		appartment.setTitleImage("test_image_1.jpg");
		user.getAdvertisement().add(appartment);
		advertisementDao.save(appartment);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSave2() {
		Advertisement appartment = new Advertisement();
		appartment.setTitleDescription("3 room with balcony");
		appartment.setTotalRooms(3.0d);
		appartment.setAreaCode("81669");
		appartment.setBuiltYear(1990);
		appartment.setBedRooms(Short.valueOf("2"));
		appartment.setSize(65.50);
		appartment.setCost(850.0d);
		appartment.setBalconyAvailable(true);
		User user = super.createTestUser("test-save2-username");
		appartment.setUser(user);
		appartment.setHeatingType(commonDao.getHeatingTypes().get(0));

		advertisementDao.save(appartment);
	}


}
