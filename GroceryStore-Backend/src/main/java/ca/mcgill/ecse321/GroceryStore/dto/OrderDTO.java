package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import ca.mcgill.ecse321.GroceryStore.model.Store;

import java.util.List;

public class OrderDTO {

    private int confirmationNumber;
    private int totalCost;
    private String orderType;
    private String username;

    //Order Associations
    private int storeID;
    private List<PurchasedItem> purchasedItem;

    public OrderDTO(int confirmationNumber, int totalCost, Store store, List<PurchasedItem> purchasedItem, String orderType, String username) {
        this.confirmationNumber = confirmationNumber;
        this.totalCost = totalCost;
        this.storeID = store.getStoreID();
        this.purchasedItem = purchasedItem;
        this.orderType = orderType;
        this.username = username;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getStore() {
        return storeID;
    }

    public List<PurchasedItem> getPurchasedItem() {
        return purchasedItem;
    }

    public String getOrderType() {return orderType;}

    public String getUsername() {
        return username;
    }
}
