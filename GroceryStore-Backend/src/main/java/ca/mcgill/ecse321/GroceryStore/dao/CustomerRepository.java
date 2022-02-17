package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{

    Customer findByUsername(String username);

}