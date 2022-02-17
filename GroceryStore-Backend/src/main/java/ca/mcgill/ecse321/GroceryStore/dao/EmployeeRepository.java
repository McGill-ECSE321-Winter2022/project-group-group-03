package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{

   Employee findByUsername(String username);

}