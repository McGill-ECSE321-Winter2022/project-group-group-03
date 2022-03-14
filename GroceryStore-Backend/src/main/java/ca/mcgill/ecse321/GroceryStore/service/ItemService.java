package ca.mcgill.ecse321.GroceryStore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public Item createItem(String name, boolean purchasable, int price, String description, int stock) {
        ArrayList<String> errorMessages = new ArrayList<>();

        if (name == null || name.trim().length() == 0) errorMessages.add("Name can't be empty.");

        if (description == null || description.trim().length() == 0) errorMessages.add("Description can't be empty.");

        if (price == 0) errorMessages.add("Item's price can't be 0.");

        if (stock == 0) errorMessages.add("Item's stock can't be 0.");

        if (price < 0) errorMessages.add("Item's price can't be negative.");

        if (stock < 0) errorMessages.add("Item's stock can't be negative.");

        if (errorMessages.isEmpty()) {
            if (itemRepository.existsById(name)) {
                errorMessages.add("An identical Item already exists.");
            }
        }
        
        if (errorMessages.size() > 0) throw new IllegalArgumentException(String.join(" ", errorMessages));

        Item item = new Item();
        item.setName(name);
        item.setPurchasable(purchasable);
        item.setPrice(price);
        item.setDescription(description);
        item.setStock(stock);
        item.setTotalPurchased(0);
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public Item getItem(String name) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        return item;
    }

    @Transactional
    public List<Item> getAllItems() {
        return toList(itemRepository.findAll());
    }

    @Transactional
    public void deleteItem(String name) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The item with name " + name + " does not exist.");
        else itemRepository.deleteById(name);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
