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

    /**
     * Creates an item with the specified parameters
     * @param name - the name of the item to be created
     * @param purchasable - specifies if the item is available for online purchasing
     * @param price - the price of the item to be created
     * @param description - the description to be associated with the item, which will be shown in the frontend
     * @param stock - the stock of the item available in the store
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return the created item
     */
    @Transactional
    public Item createItem(String name, boolean purchasable, int price, String description, int stock) {
        ArrayList<String> errorMessages = new ArrayList<>();

        if (name == null || name.trim().length() == 0) errorMessages.add("Name can't be empty.");

        if (description == null || description.trim().length() == 0) errorMessages.add("Description can't be empty.");

        if (price == 0) errorMessages.add("Item's price can't be 0.");

        if (stock == 0) errorMessages.add("Item's stock can't be 0.");

        if (price < 0) errorMessages.add("Item's price can't be negative.");

        if (stock < 0) errorMessages.add("Item's stock can't be negative.");

        if (errorMessages.size() > 0) throw new IllegalArgumentException(String.join(" ", errorMessages));

        if (itemRepository.existsById(name)) {
            errorMessages.add("An identical Item already exists.");
        }


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

    /**
     * Method to get an item specified by the input name
     * @param name - the name of the item the user wishes to get
     * @return - the item with the specified name
     */
    @Transactional
    public Item getItem(String name) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        return item;
    }

    /**
     * Method to get all the items currently in the database
     * @return a list containing all the items
     */
    @Transactional
    public List<Item> getAllItems() {
        return toList(itemRepository.findAll());
    }

    /**
     * Method to change if the item is purchasable online or not
     * @param name - the name of the item whose purchasable we wish to change
     * @param newPurchasable - the updated purchasable variable
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the updated item with the newPurchasable variable
     */
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

    /**
     * Method to change an item's description
     * @param name - the name of the item whose description we wish to change
     * @param newDescription - the string representing the new description
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the updated item with the new description
     */
    @Transactional
    public Item updateItemDescription(String name, String newDescription) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newDescription == null || newDescription.trim().length() == 0) throw new IllegalArgumentException("A Description parameter is needed.");
        item.setDescription(newDescription);
        return item;
    }

    /**
     * Method to update an item's total quantity total purchased
     * @param name - the name of the item whose total purchased we wish to update
     * @param newTotalPurchased - the number representing the new total purchased
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * For example, the new total purchased can't be 0 or negative
     * If the new purchased total is seen to be bigger than the stock available, an error is thrown
     * If the item in question, has 0 stock, the appropriate error is thrown again
     * After the new total purchased has been updated, the item's stock is decremented accordingly.
     * @return - the item with the updated total purchased value
     */
    @Transactional
    public Item updateItemTotalPurchased(String name, int newTotalPurchased) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newTotalPurchased == 0) throw new IllegalArgumentException("Total Purchased can't be 0.");
        if (newTotalPurchased < 0) throw new IllegalArgumentException("Total Purchased can't be negative.");
        if (item.getStock() == 0) throw new IllegalArgumentException("There are no " + name + "s available currently.");
        if (item.getStock() < newTotalPurchased) throw new IllegalArgumentException("There are only " + item.getStock() + " " + name + "s available currently.");
        item.setTotalPurchased(item.getTotalPurchased()+newTotalPurchased);
        item.setStock(item.getStock()-newTotalPurchased);
        return item;
    }

    /**
     * Method to update an item's stock
     * @param name - the name of the item whose stock we wish to update
     * @param newStock - the number representing the new stock
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the item with the updated stock
     */
    @Transactional
    public Item updateItemStock(String name, int newStock) {
        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException("A name parameter is needed.");
        Item item = itemRepository.findByName(name);
        if (item == null) throw new IllegalArgumentException("The Item with name: " + name + " was not found in the database.");
        if (newStock < 0) throw new IllegalArgumentException("Stock can't be negative.");
        item.setStock(newStock);
        return item;
    }

    /**
     * Method that updated an item's total stock by adding the specified number to the number in stock already
     * @param name - the name of the item whose stock needs to be incremented
     * @param addedStock - the number to add to the pre-existing stock
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the item with the newly updated stock
     */
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

    /**
     * Method to update the item's price
     * @param name - the name of the item whose price we wish to change
     * @param newPrice - the number representing the new price
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the item with the newly updated price
     */
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

    /**
     * Method to update the item's image in the frontend
     * @param name - the name of the item whose image we wish to change
     * @param image - the string representing the url of the new image
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the item with the newly updated image
     */
    @Transactional
    public Item updateItemImage(String name, String image){

        Item item = getItem(name);
        if (image == null || image.trim().length() == 0) throw new IllegalArgumentException("An image parameter is needed.");
        item.setImage(image);
        return item;

    }

    /**
     * Similar to all the methods for updating parameters above. However, this is to update all of them from one method.
     * @param name - the name of the item whose parameters we wish to update
     * @param image - the url representing the new image
     * @param newPrice - the new price of the item
     * @param newStock - the new stock of the item
     * @param newDescription - the new description of the item
     * @param newPurchasable - the new purchasable online or not of the item in question
     * If any of the given parameters raise an error, the appropriate error message is thrown
     * @return - the item with the newly updated parameters
     */
   @Transactional
   public Item updateItem(String name, String image, int newPrice, int newStock, String newDescription, boolean newPurchasable) {

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
        updateItemImage(name, image);
        return item;
    }

    /**
     * method to convert an iterable to a list. Used in the getAll method.
     * @param iterable - an iterable object
     * @return - the list from the iterable object
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
