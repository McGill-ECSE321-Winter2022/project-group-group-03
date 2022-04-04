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

    @Autowired
    StoreService storeService;

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
        item.setImage("https://i.postimg.cc/XqtwLFCL/red-Square.png");
        itemRepository.save(item);
        storeService.addItem(item);
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
    public Item updateItemPurchasable(String name, Boolean newPurchasable) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newPurchasable.equals(item.getPurchasable())) {
            if (newPurchasable) throw new IllegalArgumentException("The Item with name: " + name + " is already purchasable.");
            else throw new IllegalArgumentException("The Item with name: " + name + " is already not purchasable.");
        }
        item.setPurchasable(newPurchasable);
        return item;
    }

    @Transactional
    public Item updateItemDescription(String name, String newDescription) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newDescription == null || newDescription.trim().length() == 0) throw new IllegalArgumentException("A Description parameter is needed.");
        item.setDescription(newDescription);
        return item;
    }

    @Transactional
    public Item updateItemTotalPurchased(String name, int newTotalPurchased) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newTotalPurchased == 0) throw new IllegalArgumentException("Total Purchased can't be 0.");
        if (newTotalPurchased < 0) throw new IllegalArgumentException("Total Purchased can't be negative.");
        if (item.getStock() == 0) throw new IllegalArgumentException("There are no " + name + "s available currently.");
        if (item.getStock() < newTotalPurchased) throw new IllegalArgumentException("There are only " + item.getStock() + " " + name + "s available currently.");
        item.setTotalPurchased(newTotalPurchased);
        item.setStock(item.getStock()-newTotalPurchased);
        return item;
    }

    @Transactional
    public Item updateItemStock(String name, int newStock) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newStock < 0) throw new IllegalArgumentException("Stock can't be negative.");
        item.setStock(newStock);
        return item;
    }

    @Transactional
    public Item addItemStock(String name, int addedStock) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (addedStock == 0) throw new IllegalArgumentException("Added Stock can't be 0.");
        if (addedStock < 0) throw new IllegalArgumentException("Added Stock can't be negative.");
        item.setStock(item.getStock()+addedStock);
        return item;
    }

    @Transactional
    public Item updateItemPrice(String name, int newPrice) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newPrice == 0) throw new IllegalArgumentException("Price can't be 0.");
        if (newPrice < 0) throw new IllegalArgumentException("Price can't be negative.");
        item.setPrice(newPrice);
        return item;
    }

    @Transactional
    public Item updateItemImage(String name, String image){
        Item item = getItem(name);
        if (image == null || image.trim().length() == 0) throw new IllegalArgumentException("An image parameter is needed.");
        item.setImage(image);
        return item;
    }

    @Transactional
    public Item updateItem(String name, int newPrice, int newStock, String newDescription, boolean newPurchasable) {
        Item item = itemRepository.findByName(name);
        ArrayList<String> errorMessages = new ArrayList<>();

        if (name == null || name.trim().length() == 0) errorMessages.add("Name can't be empty.");

        if (newDescription == null || newDescription.trim().length() == 0) errorMessages.add("Description can't be empty.");

        if (newPrice == 0) errorMessages.add("Item's price can't be 0.");

        if (newStock == 0) errorMessages.add("Item's stock can't be 0.");

        if (newPrice < 0) errorMessages.add("Item's price can't be negative.");

        if (newStock < 0) errorMessages.add("Item's stock can't be negative.");

        if (errorMessages.size() > 0) throw new IllegalArgumentException(String.join(" ", errorMessages));


        item.setPurchasable(newPurchasable);
        item.setPrice(newPrice);
        item.setDescription(newDescription);
        item.setStock(newStock);
        return item;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
