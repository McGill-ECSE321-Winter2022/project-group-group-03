package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Integer>{

    BusinessHour findBusinessHourByID(Integer hoursID);

}