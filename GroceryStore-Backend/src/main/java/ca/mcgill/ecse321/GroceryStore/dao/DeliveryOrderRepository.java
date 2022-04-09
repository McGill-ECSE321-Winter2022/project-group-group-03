package ca.mcgill.ecse321.GroceryStore.dao;

import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryCommission, Integer>{

    DeliveryCommission findDeliveryOrderByConfirmationNumber(Integer confirmationNumber);

}