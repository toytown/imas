package com.imas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "OAS_APPARTMENT_CATEGORY")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Category implements Serializable {

	private static final long serialVersionUID = 6330792452715523916L;

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CategoryType> categoryTypes = new ArrayList<CategoryType>(0);;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CategoryType> getCategoryTypes() {
		return categoryTypes;
	}
	public void setCategoryTypes(List<CategoryType> categoryTypes) {
		this.categoryTypes = categoryTypes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((categoryTypes == null) ? 0 : categoryTypes.hashCode());
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
		Category other = (Category) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (categoryTypes == null) {
			if (other.categoryTypes != null)
				return false;
		} else if (!categoryTypes.equals(other.categoryTypes))
			return false;
		return true;
	}
}
