package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import ca.mcgill.ecse321.GroceryStore.model.Store;

import java.util.List;

public class OrderDTO {

    private int confirmationNumber;
    private int totalCost;

    //Order Associations
    private Store store;
    private List<PurchasedItem> purchasedItem;

    public OrderDTO(int confirmationNumber, int totalCost, Store store, List<PurchasedItem> purchasedItem) {
        this.confirmationNumber = confirmationNumber;
        this.totalCost = totalCost;
        this.store = store;
        this.purchasedItem = purchasedItem;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public Store getStore() {
        return store;
    }

    public List<PurchasedItem> getPurchasedItem() {
        return purchasedItem;
    }
}
