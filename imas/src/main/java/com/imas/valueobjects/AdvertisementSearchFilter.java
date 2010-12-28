package com.imas.valueobjects;

import java.io.Serializable;
import java.util.List;

import com.imas.model.CategoryType;

public class AdvertisementSearchFilter extends SearchFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
	private String areaCode;

	private String sizeFrom;
	private String sizeTo;
	private String roomsFrom;
	private String roomsTo;
	private String costFrom;
	private String costTo;
	private String userName;
	private SortingType sortType; 
	List<CategoryType> categoryTypes;
	
	//fixtures
	private Boolean garageAvailable;
	private Boolean kitchenAvailable;
	private Boolean balconyAvailable;
	private Boolean liftAvailable;
	private Boolean toiletWithBathRoom;
	private Boolean cellarAvailable;
	private Boolean gardenAvailable;
	private Boolean energyPassAvailable;
	private Boolean furnished;
	private Boolean provisionFree;
	private Boolean barrierFree;
	private Boolean seniorAppartment;
	
	private Integer builtYearFrom;
	private Integer builtYearTo;
	
	public Boolean isProvisionFree() {
		return provisionFree;
	}
	public void setProvisionFree(Boolean provisionFree) {
		this.provisionFree = provisionFree;
	}
	public Boolean isBarrierFree() {
		return barrierFree;
	}
	public void setBarrierFree(Boolean barrierFree) {
		this.barrierFree = barrierFree;
	}
	public Boolean isSeniorAppartment() {
		return seniorAppartment;
	}
	public void setSeniorAppartment(Boolean seniorAppartment) {
		this.seniorAppartment = seniorAppartment;
	}
	public Integer getBuiltYearFrom() {
		return builtYearFrom;
	}
	public void setBuiltYearFrom(Integer builtYearFrom) {
		this.builtYearFrom = builtYearFrom;
	}
	public Integer getBuiltYearTo() {
		return builtYearTo;
	}
	public void setBuiltYearTo(Integer builtYearTo) {
		this.builtYearTo = builtYearTo;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getSizeFrom() {
		return sizeFrom;
	}
	public void setSizeFrom(String sizeFrom) {
		this.sizeFrom = sizeFrom;
	}
	public String getSizeTo() {
		return sizeTo;
	}
	public void setSizeTo(String sizeTo) {
		this.sizeTo = sizeTo;
	}
	public String getRoomsFrom() {
		return roomsFrom;
	}
	public void setRoomsFrom(String roomsFrom) {
		this.roomsFrom = roomsFrom;
	}
	public String getRoomsTo() {
		return roomsTo;
	}
	public void setRoomsTo(String roomsTo) {
		this.roomsTo = roomsTo;
	}
	public String getCostFrom() {
		return costFrom;
	}
	public void setCostFrom(String costFrom) {
		this.costFrom = costFrom;
	}
	public String getCostTo() {
		return costTo;
	}
	public void setCostTo(String costTo) {
		this.costTo = costTo;
	}
	public SortingType getSortType() {
		return sortType;
	}
	public void setSortType(SortingType sortType) {
		this.sortType = sortType;
	}
	public List<CategoryType> getAppartmentTypes() {
		return categoryTypes;
	}
	public void setAppartmentTypes(List<CategoryType> categoryTypes) {
		this.categoryTypes = categoryTypes;
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
	public Boolean isGarageAvailable() {
		return garageAvailable;
	}

	public void setGarageAvailable(Boolean garageAvailable) {
		this.garageAvailable = garageAvailable;
	}
	public Boolean isGardenAvailable() {
		return gardenAvailable;
	}

	public void setGardenAvailable(Boolean gardenAvailable) {
		this.gardenAvailable = gardenAvailable;
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
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
        result = prime * result + ((balconyAvailable == null) ? 0 : balconyAvailable.hashCode());
        result = prime * result + ((barrierFree == null) ? 0 : barrierFree.hashCode());
        result = prime * result + ((builtYearFrom == null) ? 0 : builtYearFrom.hashCode());
        result = prime * result + ((builtYearTo == null) ? 0 : builtYearTo.hashCode());
        result = prime * result + ((categoryTypes == null) ? 0 : categoryTypes.hashCode());
        result = prime * result + ((cellarAvailable == null) ? 0 : cellarAvailable.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((costFrom == null) ? 0 : costFrom.hashCode());
        result = prime * result + ((costTo == null) ? 0 : costTo.hashCode());
        result = prime * result + ((energyPassAvailable == null) ? 0 : energyPassAvailable.hashCode());
        result = prime * result + ((furnished == null) ? 0 : furnished.hashCode());
        result = prime * result + ((garageAvailable == null) ? 0 : garageAvailable.hashCode());
        result = prime * result + ((gardenAvailable == null) ? 0 : gardenAvailable.hashCode());
        result = prime * result + ((kitchenAvailable == null) ? 0 : kitchenAvailable.hashCode());
        result = prime * result + ((liftAvailable == null) ? 0 : liftAvailable.hashCode());
        result = prime * result + ((provisionFree == null) ? 0 : provisionFree.hashCode());
        result = prime * result + ((roomsFrom == null) ? 0 : roomsFrom.hashCode());
        result = prime * result + ((roomsTo == null) ? 0 : roomsTo.hashCode());
        result = prime * result + ((seniorAppartment == null) ? 0 : seniorAppartment.hashCode());
        result = prime * result + ((sizeFrom == null) ? 0 : sizeFrom.hashCode());
        result = prime * result + ((sizeTo == null) ? 0 : sizeTo.hashCode());
        result = prime * result + ((sortType == null) ? 0 : sortType.hashCode());
        result = prime * result + ((toiletWithBathRoom == null) ? 0 : toiletWithBathRoom.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        AdvertisementSearchFilter other = (AdvertisementSearchFilter) obj;
        if (areaCode == null) {
            if (other.areaCode != null)
                return false;
        } else if (!areaCode.equals(other.areaCode))
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
        if (builtYearFrom == null) {
            if (other.builtYearFrom != null)
                return false;
        } else if (!builtYearFrom.equals(other.builtYearFrom))
            return false;
        if (builtYearTo == null) {
            if (other.builtYearTo != null)
                return false;
        } else if (!builtYearTo.equals(other.builtYearTo))
            return false;
        if (categoryTypes == null) {
            if (other.categoryTypes != null)
                return false;
        } else if (!categoryTypes.equals(other.categoryTypes))
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
        if (costFrom == null) {
            if (other.costFrom != null)
                return false;
        } else if (!costFrom.equals(other.costFrom))
            return false;
        if (costTo == null) {
            if (other.costTo != null)
                return false;
        } else if (!costTo.equals(other.costTo))
            return false;
        if (energyPassAvailable == null) {
            if (other.energyPassAvailable != null)
                return false;
        } else if (!energyPassAvailable.equals(other.energyPassAvailable))
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
        if (gardenAvailable == null) {
            if (other.gardenAvailable != null)
                return false;
        } else if (!gardenAvailable.equals(other.gardenAvailable))
            return false;
        if (kitchenAvailable == null) {
            if (other.kitchenAvailable != null)
                return false;
        } else if (!kitchenAvailable.equals(other.kitchenAvailable))
            return false;
        if (liftAvailable == null) {
            if (other.liftAvailable != null)
                return false;
        } else if (!liftAvailable.equals(other.liftAvailable))
            return false;
        if (provisionFree == null) {
            if (other.provisionFree != null)
                return false;
        } else if (!provisionFree.equals(other.provisionFree))
            return false;
        if (roomsFrom == null) {
            if (other.roomsFrom != null)
                return false;
        } else if (!roomsFrom.equals(other.roomsFrom))
            return false;
        if (roomsTo == null) {
            if (other.roomsTo != null)
                return false;
        } else if (!roomsTo.equals(other.roomsTo))
            return false;
        if (seniorAppartment == null) {
            if (other.seniorAppartment != null)
                return false;
        } else if (!seniorAppartment.equals(other.seniorAppartment))
            return false;
        if (sizeFrom == null) {
            if (other.sizeFrom != null)
                return false;
        } else if (!sizeFrom.equals(other.sizeFrom))
            return false;
        if (sizeTo == null) {
            if (other.sizeTo != null)
                return false;
        } else if (!sizeTo.equals(other.sizeTo))
            return false;
        if (sortType == null) {
            if (other.sortType != null)
                return false;
        } else if (!sortType.equals(other.sortType))
            return false;
        if (toiletWithBathRoom == null) {
            if (other.toiletWithBathRoom != null)
                return false;
        } else if (!toiletWithBathRoom.equals(other.toiletWithBathRoom))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }
	

}
