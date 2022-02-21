package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer>{

    DeliveryOrder findByConfirmationNumber(Integer confirmationNumber);

}