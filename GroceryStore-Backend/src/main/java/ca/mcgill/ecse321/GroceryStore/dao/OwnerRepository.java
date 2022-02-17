package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, String>{

    Owner findOwnerByUsername(String username);


}