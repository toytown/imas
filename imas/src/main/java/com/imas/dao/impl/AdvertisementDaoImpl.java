package com.imas.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.imas.dao.interfaces.AdvertisementDao;
import com.imas.dao.utils.CriteriaUtil;
import com.imas.model.Advertisement;
import com.imas.model.Images;
import com.imas.valueobjects.AdvertisementSearchFilter;

@Repository("advertisementDao")
public class AdvertisementDaoImpl extends GenericDaoImpl<Advertisement, Long> implements AdvertisementDao {


    @Override
    public List<Advertisement> findAdvertBySearchCriteria(AdvertisementSearchFilter searchRequest) {
        logger.info("Performing price list search ..." + searchRequest.toString());
        
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Advertisement> criteriaQuery = cBuilder.createQuery(Advertisement.class);
        Root<Advertisement> advert = criteriaQuery.from(Advertisement.class);
        criteriaQuery.select(advert);
        
        TypedQuery<Advertisement> q = buildCriteriaQuery(searchRequest, cBuilder, advert, criteriaQuery);
        
        CriteriaUtil.setPagination(q, searchRequest);

        
        return q.getResultList();
    }
    
    
    
    @Override
    public int countByFilter(AdvertisementSearchFilter searchFilter) {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cBuilder.createQuery(Long.class);
        Root<Advertisement> advert = criteriaQuery.from(Advertisement.class);
        criteriaQuery.select(cBuilder.count(advert));
        
        Query q = buildCriteriaQuery(searchFilter, cBuilder, advert, criteriaQuery);

        return ((Long)q.getSingleResult()).intValue();
    }
    
    
    /**
     * Builds criteria based on input search parameters
     * 
     * 
     * @param <T>
     * @param filter
     * @param cBuilder
     * @param advert
     * @param criteriaQuery
     * @return
     */
    private <T> TypedQuery<T> buildCriteriaQuery(AdvertisementSearchFilter filter, CriteriaBuilder cBuilder, Root<Advertisement> advert, CriteriaQuery<T> criteriaQuery) {

        CriteriaUtil criteria = new CriteriaUtil(cBuilder);
        
        if(filter.getCity() != null) {
            criteria.equal(filter.getCity(), "city", advert);
        } 
        
        if (filter.getRoomsFrom() != null) {
            criteria.greaterThanOrEqualTo(filter.getRoomsFrom(), "totalRooms", advert);
        }
        
        if (filter.getRoomsTo() != null) {
            criteria.lessThanOrEqualTo(filter.getRoomsTo(), "totalRooms", advert);
        }
        
        if (filter.getSizeFrom() != null) {
            criteria.greaterThanOrEqualTo(filter.getSizeFrom(), "size", advert);
        }
        
        if (filter.getSizeTo() != null) {
            criteria.lessThanOrEqualTo(filter.getSizeTo(), "size", advert);
        }
        
        if (filter.getCostFrom() != null) {
            criteria.greaterThanOrEqualTo(filter.getRoomsFrom(), "cost", advert);
        }
        
        if (filter.getCostTo() != null) {
            criteria.lessThanOrEqualTo(filter.getRoomsTo(), "cost", advert);
        }
        
        if (filter.getUserName() != null) {
            criteria.equal(filter.getUserName(), "user", advert);
        }

        if (filter.getAppartmentTypes() != null && !filter.getAppartmentTypes().isEmpty()) {            
            criteria.in("categoryType", filter.getAppartmentTypes(), advert);
        }
        
        return criteria.createQuery(entityManager, criteriaQuery);
    }    

    @Override
    public Images findImageById(long id) {
        return entityManager.find(Images.class, id);
    }

    @Override
    public List<Advertisement> findAdvertByPostalCode(String zip) {
        TypedQuery<Advertisement> q = entityManager.createQuery("from Advertisement a where a.areaCode like :zipCode", Advertisement.class);
        q.setParameter("zipCode", zip + "%");
        return q.getResultList();
    }

    /*
	private static final String SEARCH_QUERY = "SELECT a FROM com.oas.model.Advertisement a WHERE 1=1 ";
	private static final String DEFAULT_ORDER_BY = "ORDER by a.totalRooms desc, a.cost asc, a.size desc, a.insertDate desc";
	
	public List<Advertisement> findAllAppartments(String zipCode, String city) {
		return null; //super.findAll();
	}

	public Advertisement findRealStateAdvertById(Long advertId) {
		return super.findById(advertId);
	}

	public List<Advertisement> findRealStateBySearchCriteria(SearchRequest searchRequest) {
		Map<String, Map> queryWithParamsMap = buildSearchQuery(searchRequest);
		Set<String> keys = queryWithParamsMap.keySet();

		String query = keys.iterator().next();
		Map params = queryWithParamsMap.get(query);
		return findByNamedParamsWithMaxResultSet(query, params);
	}

	public List findByNamedParamsWithMaxResultSet(final String queryString, final Map<String, ?> params) throws DataAccessException {
		return getJpaTemplate().executeFind(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query queryObject = em.createQuery(queryString);
				queryObject.setMaxResults(1000);
				if (params != null) {
					for (Map.Entry<String, ?> entry : params.entrySet()) {
						queryObject.setParameter(entry.getKey(), entry.getValue());
					}
				}
				return queryObject.getResultList();
			}
		});
	}

	
	private Map<String, Map> buildSearchQuery(SearchRequest searchRequest) {
		Map returnValue = new HashMap<String, Map>();

		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder buffer = new StringBuilder(SEARCH_QUERY);

		if (searchRequest.getCity() != null) {
			buffer.append(" and a.city = :city");
			parameters.put("city", searchRequest.getCity());
		}

		if (searchRequest.getRoomsFrom() != null) {
			buffer.append(" and a.totalRooms >= :roomsFrom");
			parameters.put("roomsFrom", Double.valueOf(searchRequest.getRoomsFrom()));
		}

		if (searchRequest.getRoomsTo() != null) {
			buffer.append(" and a.totalRooms <= :roomsTo");
			parameters.put("roomsTo", Double.valueOf(searchRequest.getRoomsTo()));
		}

		if (searchRequest.getSizeFrom() != null) {
			buffer.append(" and a.size >= :sizeFrom");
			parameters.put("sizeFrom", Double.valueOf(searchRequest.getSizeFrom()));
		}

		if (searchRequest.getSizeTo() != null) {
			buffer.append(" and a.size<= :sizeTo");
			parameters.put("sizeTo", Double.valueOf(searchRequest.getSizeTo()));
		}

		if (searchRequest.getCostFrom() != null) {
			buffer.append(" and a.cost >= :costFrom");
			parameters.put("costFrom", Double.valueOf(searchRequest.getCostFrom()));
		}

		if (searchRequest.getCostTo() != null) {
			buffer.append(" and a.cost<= :costTo");
			parameters.put("costTo", Double.valueOf(searchRequest.getCostTo()));
		}

		if (searchRequest.getUserName() != null) {
			buffer.append(" and a.customer.userName =:userName");
			parameters.put("userName", searchRequest.getUserName());
		}

		if (searchRequest.getAppartmentTypes() != null && !searchRequest.getAppartmentTypes().isEmpty()) {
			buffer.append(" and a.appartmentType.id in ( ");
			boolean firstSelect = false;
			for (CategoryType categoryType : searchRequest.getAppartmentTypes()) {
				if (firstSelect) {
					buffer.append(", ");
				} else {
					firstSelect = true;
				}
				buffer.append(categoryType.getId());
			}
			buffer.append(") ");
		}

		if (searchRequest.isGarageAvailable() != null && searchRequest.isGarageAvailable()) {
			buffer.append(" and a.garageAvailable =1 ");
		}

		if (searchRequest.isKitchenAvailable() != null && searchRequest.isKitchenAvailable()) {
			buffer.append(" and a.kitchenAvailable =1 ");
		}

		if (searchRequest.isBalconyAvailable() != null && searchRequest.isBalconyAvailable()) {
			buffer.append(" and a.balconyAvailable =1 ");
		}

		if (searchRequest.isLiftAvailable() != null && searchRequest.isLiftAvailable()) {
			buffer.append(" and a.liftAvailable =1 ");
		}

		if (searchRequest.isToiletWithBathRoom() != null && searchRequest.isToiletWithBathRoom()) {
			buffer.append(" and a.toiletWithBathRoom =1 ");
		}

		if (searchRequest.isCellarAvailable() != null && searchRequest.isCellarAvailable()) {
			buffer.append(" and a.cellarAvailable =1 ");
		}

		if (searchRequest.isGardenAvailable() != null && searchRequest.isGardenAvailable()) {
			buffer.append(" and a.gardenAvailable =1 ");
		}

		if (searchRequest.isEnergyPassAvailable() != null && searchRequest.isEnergyPassAvailable()) {
			buffer.append(" and a.energyPassAvailable =1 ");
		}

		if (searchRequest.isFurnished() != null && searchRequest.isFurnished()) {
			buffer.append(" and a.furnished =1 ");
		}

		if (searchRequest.isProvisionFree() != null && searchRequest.isProvisionFree()) {
			buffer.append(" and a.provisionFree =1 ");
		}

		if (searchRequest.isBarrierFree() != null && searchRequest.isBarrierFree()) {
			buffer.append(" and a.barrierFree =1 ");
		}

		if (searchRequest.isSeniorAppartment() != null && searchRequest.isSeniorAppartment()) {
			buffer.append(" and a.seniorAppartment =1 ");
		}

		if (searchRequest.getBuiltYearFrom() != null) {
			buffer.append(" and a.builtYear >= :builtYearFrom");
			parameters.put("builtYearFrom", searchRequest.getBuiltYearFrom());
		}

		if (searchRequest.getBuiltYearTo() != null) {
			buffer.append(" and a.builtYear <= :builtYearTo");
			parameters.put("builtYearTo", searchRequest.getBuiltYearTo());
		}

		buffer.append(" ");

		int sortingType = -1; 
			
		if (searchRequest.getSortType() == null) {
			buffer.append(" ");
		} else {
			sortingType = searchRequest.getSortType().getId();
		}
		
		switch (sortingType) {
		case 0:
			buffer.append("ORDER by a.insertDate desc, a.cost asc, a.totalRooms desc");
			break;
		case 1:
			buffer.append("ORDER by a.cost asc");
			break;
		case 2:
			buffer.append("ORDER by a.cost desc");
			break;
		case 3:
			buffer.append("ORDER by a.size asc");
			break;
		case 4:
			buffer.append("ORDER by a.size desc");
			break;
		case 5:
			buffer.append("ORDER by a.totalRooms asc");
			break;
		case 6:
			buffer.append("ORDER by a.totalRooms desc");
			break;
		default:
			buffer.append(DEFAULT_ORDER_BY);
			break;
		}

		returnValue.put(buffer.toString(), parameters);
		return returnValue;

	}

	public void deleteImage(Images appartmentImage) {
		getJpaTemplate().remove(appartmentImage);
	}

	public Images findImageById(long id) {
		return getJpaTemplate().find(Images.class, id);
	}

	public void delete(Advertisement advert) {
		super.delete(advert);
	}

	public List<String> findCities(String searchStr) {
		return getJpaTemplate().find("SELECT p.city FROM com.oas.model.PostalCode p WHERE p.city LIKE ?", searchStr + "%");
	}
    */
}
