package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, String>{

    Customer findCustomerByUsername(String username);


}