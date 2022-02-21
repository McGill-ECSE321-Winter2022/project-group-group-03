package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;

public interface PickupOrderRepository extends CrudRepository<PickupOrder, Integer>{

    PickupOrder findByConfirmationNumber(Integer confirmationNumber);

}