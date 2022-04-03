package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.PickupCommission;

public interface PickupOrderRepository extends CrudRepository<PickupCommission, Integer>{

    PickupCommission findByConfirmationNumber(Integer confirmationNumber);

}