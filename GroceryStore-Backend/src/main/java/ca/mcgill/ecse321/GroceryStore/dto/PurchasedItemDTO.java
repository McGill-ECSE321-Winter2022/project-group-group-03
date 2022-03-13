package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.Item;

public class PurchasedItemDTO {

    private Item item;
    private int aItemQuantity;
    private int purchasedItemID;


    public PurchasedItemDTO(Item aItem, int aItemQuantity, int aPurchasedItemID) {
        this.item = aItem;
        this.aItemQuantity = aItemQuantity;
        this.purchasedItemID = aPurchasedItemID;
    }

    public int getPurchasedItemID() {
        return purchasedItemID;
    }

    public Item getItem() {
        return item;
    }


    public int getItemQuantity() {
        return aItemQuantity;
    }
    
}
