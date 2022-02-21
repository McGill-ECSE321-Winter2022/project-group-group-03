package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;

public interface PurchasedItemRepository extends CrudRepository<PurchasedItem, Integer>{

    PurchasedItem findByPurchasedItemID(Integer purchasedItemID);

}