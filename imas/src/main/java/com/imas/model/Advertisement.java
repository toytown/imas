package com.imas.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.imas.common.dao.interfaces.IDomainObject;

@Entity(name = "OAS_APPARTMENT_ADVERTISEMENT")
public class Advertisement implements IDomainObject {

	private static final long serialVersionUID = -553582825643017717L;

	public Advertisement() {
		super();
	}

	@Id
	@Column(name = "advert_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title_desc", length = 255)
	private String titleDescription;

	@Column(name = "title_image", length = 32)
	private String titleImage;

	@Column(name = "activation_date")
	private Date activationDate;

	@Column(name = "advert_status")
	private Short status;

	@Column(name = "insert_date")
	private Timestamp insertDate;

	@Column(name = "update_date")
	@Version
	private Timestamp updateDate;

	@Column(name = "inserted_by", length = 100)
	private String insertedBy;

	@Column(name = "updated_by", length = 100)
	private String updatedBy;

	@Column(name = "advert_type", length = 5)
	private String type;

	@Column(name = "area_code", length = 10)
	private String areaCode;

	@Column(name = "city", length = 100)
	private String city;

	@Column(name = "street", length = 100)
	private String street;

	@Column(name = "houseNo", length = 100)
	private String houseNo;

	@Column(name = "size", precision = 10, scale = 2)
	private Double size;

	@Column(name = "cost", precision = 10, scale = 2)
	private Double cost;

	@Column(name = "floor", precision = 10, scale = 2)
	private Double floor;

	@Column(name = "toal_floors", precision = 10, scale = 2)
	private Double totalFloors;

	@Column(name = "total_rooms", precision = 10, scale = 2, nullable = false)
	private Double totalRooms;

	@Column(name = "bedrooms", length = 5)
	private Short bedRooms;

	@Column(name = "bathrooms", length = 5)
	private Short bathRooms;

	@Column(name = "toilet_bathroom")
	private Boolean toiletWithBathRoom;

	@Column(name = "cellar_available")
	private Boolean cellarAvailable;

	@Column(name = "balcony_available")
	private Boolean balconyAvailable;

	@Column(name = "liftAvailable", length = 20)
	private Boolean liftAvailable;

	@Column(name = "gardenAvailable", length = 20)
	private Boolean gardenAvailable;

	@Column(name = "appartment_condition", length = 5)
	private Short condition;

	@ManyToOne
	@JoinColumn(name = "appartment_type_id")
	private CategoryType categoryType;

	@ManyToOne
	@JoinColumn(name = "heating_type_id")
	private HeatingType heatingType;

	@Column(name = "additional_cost", precision = 10, scale = 2)
	private Double additionalCost;

	@Column(name = "heating_cost_included")
	private Boolean heatingCostIncluded;

	@Column(name = "deposit_period", precision = 10, scale = 2)
	private Double depositPeriod;

	@Column(name = "available_from")
	private Date availableFrom;

	@Column(name = "garage_available")
	private Boolean garageAvailable;

	@Column(name = "garage_cost", precision = 10, scale = 2)
	private Double garageCost;

	@Column(name = "appartment_desc", length = 255)
	private String description;

	@Column(name = "area_desc", length = 500)
	private String areaDescription;

	@Column(name = "fittings", length = 500)
	private String fittings;

	@Column(name = "energy_pass_available")
	private Boolean energyPassAvailable;

	@Column(name = "kitchen_available")
	private Boolean kitchenAvailable;

	@Column(name = "furnished")
	private Boolean furnished;

	@Column(name = "animals_allowed")
	private Boolean animalsAllowed;

	@Column(name = "built_year", length = 4)
	private Integer builtYear;

	@Column(name = "last_renovated_year", length = 4)
	private String lastRenovatedYear;

	@Column(name = "other_information", length = 500)
	private String otherInformation;

	@Column(name = "provision_free")
	private Boolean provisionFree;

	@Column(name = "provisionCondition", precision = 10, scale = 2)
	private String provisionCondition;

	@Column(name = "image_dir", length = 64)
	private String imageDir;

	@Column(name = "barrier_free")
	private Boolean barrierFree;

	@Column(name = "seniorAppartment")
	private Boolean seniorAppartment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "advertisement")
	private List<Images> images = new ArrayList<Images>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long advertId) {
		this.id = advertId;
	}

	public String getTitleDescription() {
		return titleDescription;
	}

	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getFloor() {
		return floor;
	}

	public void setFloor(Double floor) {
		this.floor = floor;
	}

	public Double getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(Double totalFloors) {
		this.totalFloors = totalFloors;
	}

	public Double getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Double totalRooms) {
		this.totalRooms = totalRooms;
	}

	public Short getBedRooms() {
		return bedRooms;
	}

	public void setBedRooms(Short bedRooms) {
		this.bedRooms = bedRooms;
	}

	public Short getBathRooms() {
		return bathRooms;
	}

	public void setBathRooms(Short bathrooms) {
		this.bathRooms = bathrooms;
	}

	public Boolean isToiletWithBathRoom() {
		return toiletWithBathRoom;
	}

	public void setToiletWithBathRoom(Boolean toiletWithBathRoom) {
		this.toiletWithBathRoom = toiletWithBathRoom;
	}

	public Boolean isCellarAvailable() {
		return cellarAvailable;
	}

	public void setCellarAvailable(Boolean cellarAvailable) {
		this.cellarAvailable = cellarAvailable;
	}

	public Boolean isBalconyAvailable() {
		return balconyAvailable;
	}

	public void setBalconyAvailable(Boolean balconyAvailable) {
		this.balconyAvailable = balconyAvailable;
	}

	public Boolean isLiftAvailable() {
		return liftAvailable;
	}

	public void setLiftAvailable(Boolean liftAvailable) {
		this.liftAvailable = liftAvailable;
	}

	public Boolean isGardenAvailable() {
		return gardenAvailable;
	}

	public void setGardenAvailable(Boolean gardenAvailable) {
		this.gardenAvailable = gardenAvailable;
	}

	public Short getCondition() {
		return condition;
	}

	public void setCondition(Short apartmentCondition) {
		this.condition = apartmentCondition;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType realStateType) {
		this.categoryType = realStateType;
	}

	public HeatingType getHeatingType() {
		return heatingType;
	}

	public void setHeatingType(HeatingType realStateHeatingType) {
		this.heatingType = realStateHeatingType;
	}

	public Double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(Double additionalCost) {
		this.additionalCost = additionalCost;
	}

	public Boolean getHeatingCostIncluded() {
		return heatingCostIncluded;
	}

	public void setHeatingCostIncluded(Boolean heatingCostIncluded) {
		this.heatingCostIncluded = heatingCostIncluded;
	}

	public Double getDepositPeriod() {
		return depositPeriod;
	}

	public void setDepositPeriod(Double depositPeriod) {
		this.depositPeriod = depositPeriod;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Boolean isGarageAvailable() {
		return garageAvailable;
	}

	public void setGarageAvailable(Boolean garageAvailable) {
		this.garageAvailable = garageAvailable;
	}

	public Double isGarageCost() {
		return garageCost;
	}

	public void setGarageCost(Double garageCost) {
		this.garageCost = garageCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String apartmentDescription) {
		this.description = apartmentDescription;
	}

	public String getAreaDescription() {
		return areaDescription;
	}

	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}

	public String getFittings() {
		return fittings;
	}

	public void setFittings(String fittings) {
		this.fittings = fittings;
	}

	public Boolean isEnergyPassAvailable() {
		return energyPassAvailable;
	}

	public void setEnergyPassAvailable(Boolean energyPass) {
		this.energyPassAvailable = energyPass;
	}

	public Boolean isKitchenAvailable() {
		return kitchenAvailable;
	}

	public void setKitchenAvailable(Boolean kitchenAvailable) {
		this.kitchenAvailable = kitchenAvailable;
	}

	public Boolean isFurnished() {
		return furnished;
	}

	public void setFurnished(Boolean furnished) {
		this.furnished = furnished;
	}

	public Boolean getAnimalsAllowed() {
		return animalsAllowed;
	}

	public void setAnimalsAllowed(Boolean animalsAllowed) {
		this.animalsAllowed = animalsAllowed;
	}

	public Integer getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(Integer builtYear) {
		this.builtYear = builtYear;
	}

	public String getLastRenovatedYear() {
		return lastRenovatedYear;
	}

	public void setLastRenovatedYear(String lastRenovatedYear) {
		this.lastRenovatedYear = lastRenovatedYear;
	}

	public String getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

	public Boolean isProvisionFree() {
		return provisionFree;
	}

	public void setProvisionFree(Boolean provisionFree) {
		this.provisionFree = provisionFree;
	}

	public String getProvisionCondition() {
		return provisionCondition;
	}

	public void setProvisionCondition(String provisionCondition) {
		this.provisionCondition = provisionCondition;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public Customer getUser() {
		return customer;
	}

	public void setUser(Customer realStateCustomer) {
		this.customer = realStateCustomer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Images> getImages() {
		return images;
	}

	public Boolean getBarrierFree() {
		return barrierFree;
	}

	public void setBarrierFree(Boolean barrierFree) {
		this.barrierFree = barrierFree;
	}

	public Boolean getSeniorAppartment() {
		return seniorAppartment;
	}

	public void setSeniorAppartment(Boolean seniorAppartment) {
		this.seniorAppartment = seniorAppartment;
	}

	public void setImages(List<Images> realStateImages) {
		this.images = realStateImages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activationDate == null) ? 0 : activationDate.hashCode());
		result = prime * result + ((additionalCost == null) ? 0 : additionalCost.hashCode());
		result = prime * result + ((animalsAllowed == null) ? 0 : animalsAllowed.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((categoryType == null) ? 0 : categoryType.hashCode());
		result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
		result = prime * result + ((areaDescription == null) ? 0 : areaDescription.hashCode());
		result = prime * result + ((availableFrom == null) ? 0 : availableFrom.hashCode());
		result = prime * result + ((balconyAvailable == null) ? 0 : balconyAvailable.hashCode());
		result = prime * result + ((barrierFree == null) ? 0 : barrierFree.hashCode());
		result = prime * result + ((bathRooms == null) ? 0 : bathRooms.hashCode());
		result = prime * result + ((bedRooms == null) ? 0 : bedRooms.hashCode());
		result = prime * result + ((builtYear == null) ? 0 : builtYear.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((cellarAvailable == null) ? 0 : cellarAvailable.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((depositPeriod == null) ? 0 : depositPeriod.hashCode());
		result = prime * result + ((energyPassAvailable == null) ? 0 : energyPassAvailable.hashCode());
		result = prime * result + ((fittings == null) ? 0 : fittings.hashCode());
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((furnished == null) ? 0 : furnished.hashCode());
		result = prime * result + ((garageAvailable == null) ? 0 : garageAvailable.hashCode());
		result = prime * result + ((garageCost == null) ? 0 : garageCost.hashCode());
		result = prime * result + ((gardenAvailable == null) ? 0 : gardenAvailable.hashCode());
		result = prime * result + ((heatingCostIncluded == null) ? 0 : heatingCostIncluded.hashCode());
		result = prime * result + ((heatingType == null) ? 0 : heatingType.hashCode());
		result = prime * result + ((houseNo == null) ? 0 : houseNo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageDir == null) ? 0 : imageDir.hashCode());
		result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
		result = prime * result + ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result + ((kitchenAvailable == null) ? 0 : kitchenAvailable.hashCode());
		result = prime * result + ((lastRenovatedYear == null) ? 0 : lastRenovatedYear.hashCode());
		result = prime * result + ((liftAvailable == null) ? 0 : liftAvailable.hashCode());
		result = prime * result + ((otherInformation == null) ? 0 : otherInformation.hashCode());
		result = prime * result + ((provisionCondition == null) ? 0 : provisionCondition.hashCode());
		result = prime * result + ((provisionFree == null) ? 0 : provisionFree.hashCode());
		result = prime * result + ((seniorAppartment == null) ? 0 : seniorAppartment.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((titleDescription == null) ? 0 : titleDescription.hashCode());
		result = prime * result + ((titleImage == null) ? 0 : titleImage.hashCode());
		result = prime * result + ((toiletWithBathRoom == null) ? 0 : toiletWithBathRoom.hashCode());
		result = prime * result + ((totalFloors == null) ? 0 : totalFloors.hashCode());
		result = prime * result + ((totalRooms == null) ? 0 : totalRooms.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		Advertisement other = (Advertisement) obj;
		if (activationDate == null) {
			if (other.activationDate != null)
				return false;
		} else if (!activationDate.equals(other.activationDate))
			return false;
		if (additionalCost == null) {
			if (other.additionalCost != null)
				return false;
		} else if (!additionalCost.equals(other.additionalCost))
			return false;
		if (animalsAllowed == null) {
			if (other.animalsAllowed != null)
				return false;
		} else if (!animalsAllowed.equals(other.animalsAllowed))
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (categoryType == null) {
			if (other.categoryType != null)
				return false;
		} else if (!categoryType.equals(other.categoryType))
			return false;
		if (areaCode == null) {
			if (other.areaCode != null)
				return false;
		} else if (!areaCode.equals(other.areaCode))
			return false;
		if (areaDescription == null) {
			if (other.areaDescription != null)
				return false;
		} else if (!areaDescription.equals(other.areaDescription))
			return false;
		if (availableFrom == null) {
			if (other.availableFrom != null)
				return false;
		} else if (!availableFrom.equals(other.availableFrom))
			return false;
		if (balconyAvailable == null) {
			if (other.balconyAvailable != null)
				return false;
		} else if (!balconyAvailable.equals(other.balconyAvailable))
			return false;
		if (barrierFree == null) {
			if (other.barrierFree != null)
				return false;
		} else if (!barrierFree.equals(other.barrierFree))
			return false;
		if (bathRooms == null) {
			if (other.bathRooms != null)
				return false;
		} else if (!bathRooms.equals(other.bathRooms))
			return false;
		if (bedRooms == null) {
			if (other.bedRooms != null)
				return false;
		} else if (!bedRooms.equals(other.bedRooms))
			return false;
		if (builtYear == null) {
			if (other.builtYear != null)
				return false;
		} else if (!builtYear.equals(other.builtYear))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (cellarAvailable == null) {
			if (other.cellarAvailable != null)
				return false;
		} else if (!cellarAvailable.equals(other.cellarAvailable))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (depositPeriod == null) {
			if (other.depositPeriod != null)
				return false;
		} else if (!depositPeriod.equals(other.depositPeriod))
			return false;
		if (energyPassAvailable == null) {
			if (other.energyPassAvailable != null)
				return false;
		} else if (!energyPassAvailable.equals(other.energyPassAvailable))
			return false;
		if (fittings == null) {
			if (other.fittings != null)
				return false;
		} else if (!fittings.equals(other.fittings))
			return false;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (furnished == null) {
			if (other.furnished != null)
				return false;
		} else if (!furnished.equals(other.furnished))
			return false;
		if (garageAvailable == null) {
			if (other.garageAvailable != null)
				return false;
		} else if (!garageAvailable.equals(other.garageAvailable))
			return false;
		if (garageCost == null) {
			if (other.garageCost != null)
				return false;
		} else if (!garageCost.equals(other.garageCost))
			return false;
		if (gardenAvailable == null) {
			if (other.gardenAvailable != null)
				return false;
		} else if (!gardenAvailable.equals(other.gardenAvailable))
			return false;
		if (heatingCostIncluded == null) {
			if (other.heatingCostIncluded != null)
				return false;
		} else if (!heatingCostIncluded.equals(other.heatingCostIncluded))
			return false;
		if (heatingType == null) {
			if (other.heatingType != null)
				return false;
		} else if (!heatingType.equals(other.heatingType))
			return false;
		if (houseNo == null) {
			if (other.houseNo != null)
				return false;
		} else if (!houseNo.equals(other.houseNo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageDir == null) {
			if (other.imageDir != null)
				return false;
		} else if (!imageDir.equals(other.imageDir))
			return false;
		if (insertDate == null) {
			if (other.insertDate != null)
				return false;
		} else if (!insertDate.equals(other.insertDate))
			return false;
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		if (kitchenAvailable == null) {
			if (other.kitchenAvailable != null)
				return false;
		} else if (!kitchenAvailable.equals(other.kitchenAvailable))
			return false;
		if (lastRenovatedYear == null) {
			if (other.lastRenovatedYear != null)
				return false;
		} else if (!lastRenovatedYear.equals(other.lastRenovatedYear))
			return false;
		if (liftAvailable == null) {
			if (other.liftAvailable != null)
				return false;
		} else if (!liftAvailable.equals(other.liftAvailable))
			return false;
		if (otherInformation == null) {
			if (other.otherInformation != null)
				return false;
		} else if (!otherInformation.equals(other.otherInformation))
			return false;
		if (provisionCondition == null) {
			if (other.provisionCondition != null)
				return false;
		} else if (!provisionCondition.equals(other.provisionCondition))
			return false;
		if (provisionFree == null) {
			if (other.provisionFree != null)
				return false;
		} else if (!provisionFree.equals(other.provisionFree))
			return false;
		if (seniorAppartment == null) {
			if (other.seniorAppartment != null)
				return false;
		} else if (!seniorAppartment.equals(other.seniorAppartment))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (titleDescription == null) {
			if (other.titleDescription != null)
				return false;
		} else if (!titleDescription.equals(other.titleDescription))
			return false;
		if (titleImage == null) {
			if (other.titleImage != null)
				return false;
		} else if (!titleImage.equals(other.titleImage))
			return false;
		if (toiletWithBathRoom == null) {
			if (other.toiletWithBathRoom != null)
				return false;
		} else if (!toiletWithBathRoom.equals(other.toiletWithBathRoom))
			return false;
		if (totalFloors == null) {
			if (other.totalFloors != null)
				return false;
		} else if (!totalFloors.equals(other.totalFloors))
			return false;
		if (totalRooms == null) {
			if (other.totalRooms != null)
				return false;
		} else if (!totalRooms.equals(other.totalRooms))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}
}
