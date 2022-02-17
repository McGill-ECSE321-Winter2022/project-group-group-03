package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Store;

public interface StoreRepository extends CrudRepository<Store, Integer>{

	Store findById(int storeid);


}