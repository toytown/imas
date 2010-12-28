package com.imas.services.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.util.file.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imas.dao.interfaces.AdvertisementDao;
import com.imas.model.Advertisement;
import com.imas.model.Images;
import com.imas.services.interfaces.AdvertisementService;
import com.imas.valueobjects.AdvertisementSearchFilter;
import com.imas.web.main.AppConfig;

@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
	private AdvertisementDao advertisementDao;

    @Autowired
    private AppConfig appConfig;
    
	private Logger logger = Logger.getLogger(AdvertisementServiceImpl.class);
	
	@Override
	public List<Advertisement> findAdvertisement(AdvertisementSearchFilter searchRequest) {
		return advertisementDao.findAdvertBySearchCriteria(searchRequest);
	}

	@Override
	@Transactional
	public void save(Advertisement appartment) {
		advertisementDao.save(appartment);
	}

	@Override
	@Transactional
	public Advertisement update(Advertisement appartment) {
		return advertisementDao.update(appartment);
	}

	@Override
	public Images findRealStateImageById(long id) {
		return advertisementDao.findImageById(1);
	}

	@Override
	@Transactional
	public void delete(Advertisement advert) {
		advertisementDao.delete(advert);
		advert.getImageDir();
		removeImageDir(advert);
		
	}

	private void removeImageDir(Advertisement advert) {

		if (advert.getImageDir() != null) {
			String imageLocation = appConfig.getImageStore();
			Folder uploadFolder =  new Folder(imageLocation + File.separatorChar + advert.getImageDir());
			
			if (uploadFolder.exists()) {
				List<File> fileList = Arrays.asList(uploadFolder.listFiles());
				for (File fileToDelete : fileList) {
					boolean deletionOpt = fileToDelete.delete();
					logger.info("deleting of file : " + fileToDelete.getAbsolutePath() + deletionOpt);
				}
				boolean deleted = uploadFolder.delete();
				if (deleted) {
					logger.info("deleting folder : " + uploadFolder.getAbsolutePath());
				} else {
					logger.info("could not delete folder : " + uploadFolder.getAbsolutePath());
				}
				
			} else {
				logger.warn(" folder : " + uploadFolder.getName() + " does not exists.");
			}			
		} else {
			throw new IllegalArgumentException("Image Dir folder is null for advert id =" + advert.getId());
		}
	}

    @Override
    public Advertisement findById(Long id) {
        return advertisementDao.findById(id);
    }

    @Override
    public List<Advertisement> findAdvertByZip(String zip) {
        return null;
    }

}
