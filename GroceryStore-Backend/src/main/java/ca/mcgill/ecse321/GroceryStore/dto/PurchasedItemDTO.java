package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.Item;

public class PurchasedItemDTO {

    private Item item;
    private int aItemQuantity;
    private int aPurchasedItem;
    private int purchasedItemID;


    public PurchasedItemDTO(Item aItem, int aItemQuantity, int aPurchasedItemID) {
        this.item = aItem;
        this.aItemQuantity = aItemQuantity;
        this.aPurchasedItem = aPurchasedItem;
        this.purchasedItemID = aPurchasedItemID;
    }

    public int getPurchasedItemID() {
        return purchasedItemID;
    }

    public Item getItem() {
        return item;
    }

    public int getaPurchasedItem() {
        return aPurchasedItem;
    }

    public int getaItemQuantity() {
        return aItemQuantity;
    }
    
}
