package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.Item;

public class PurchasedItemDTO {

    private Item item;
    private int aItemQuantity;
    private int aPurchasedItem;
    private int purchasedItemID;

    public PurchasedItemDTO() {
    }

    //TODO: SuppressWarning
    /*@SuppressWarnings("unchecked")
    public PersonDto(String name) {
        this(name, Collections.EMPTY_LIST);
    }*/

    public PurchasedItemDTO(Item item, int aItemQuantity, int aPurchasedItem) {
        this.item = item;
        this.aItemQuantity = aItemQuantity;
        this.aPurchasedItem = aPurchasedItem;
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
