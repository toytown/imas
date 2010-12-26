package com.imas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imas.common.dao.interfaces.IDomainObject;

@Entity(name = "OAS_APPARTMENT_IMAGES")
public class Images implements IDomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 385699473568795335L;
	@ManyToOne
	@JoinColumn(name = "advert_id")
	public Advertisement advertisement;
	
	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement appartment) {
		this.advertisement = appartment;
	}

	public String imageName;
	public String imageDescription;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void  setId(Long id) {
		this.id = id;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageDescription() {
		return imageDescription;
	}

	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advertisement == null) ? 0 : advertisement.hashCode());
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Images other = (Images) obj;
		if (advertisement == null) {
			if (other.advertisement != null)
				return false;
		} else if (!advertisement.equals(other.advertisement))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		return true;
	}




	
	
}
