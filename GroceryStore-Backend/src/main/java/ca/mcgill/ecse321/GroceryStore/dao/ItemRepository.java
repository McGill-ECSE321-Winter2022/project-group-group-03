package ca.mcgill.ecse321.GroceryStore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.GroceryStore.model.Item;

public interface ItemRepository extends CrudRepository<Item, String>{

    Item findByName(String name);

}