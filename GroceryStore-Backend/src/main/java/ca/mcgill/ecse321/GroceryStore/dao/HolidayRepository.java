package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Holiday;

public interface HolidayRepository extends CrudRepository<Holiday, String>{

    Holiday findByName(String name);

}