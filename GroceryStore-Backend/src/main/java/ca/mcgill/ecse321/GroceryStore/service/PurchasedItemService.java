package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasedItemService {

    private static int curID = 90000;
    @Autowired
    PurchasedItemRepository purchasedItemRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    DeliveryOrderService deliveryOrderService;

    @Autowired
    PickupOrderService pickupOrderService;

    @Transactional
    public void deletePurchasedItem(int purchasedItemID) {
        PurchasedItem pItem = purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        if (pItem == null) {
            throw new IllegalArgumentException("The purchased item with ID: " + purchasedItemID + " does not exist.");
        } else {
            purchasedItemRepository.deleteById(purchasedItemID);
        }
    }

    @Transactional
    public PurchasedItem getPurchasedItem(int purchasedItemID) {
        String error = null;
        if (purchasedItemID <=0) {
            error = "ID is invalid.";
        } else if (!purchasedItemRepository.existsById(purchasedItemID)) {
            error = "PurchasedItem doesn't exist.";
        }
        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        return purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
    }

    @Transactional
    public Item getItem(String name) {
        Item item = itemRepository.findByName(name);
        if (item == null) {
            throw new IllegalArgumentException("The item with name: " + name + " does not exist.");
        }
        return item;
    }

    @Transactional
    public List<PurchasedItem> getAllPurchasedItem() {
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        for(PurchasedItem purchasedItem : purchasedItemRepository.findAll()) purchasedItems.add(purchasedItem);
        return purchasedItems;
    }

    @Transactional
    public PurchasedItem createPurchasedItem(String itemName, int itemQuantity, int confirmationNumber, String orderType) {
        PurchasedItem purchasedItem = new PurchasedItem();
        String error = null;
        if (itemName== null || itemName.trim().length() == 0) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        Item item = itemService.getItem(itemName);

        if (!item.getPurchasable()) {
            error="item is not purchasable.";
        }
        else if (itemQuantity > item.getStock()) {
            error="itemQuantity cannot be greater than the stock.";
        }
        if (itemQuantity == 0) {
            error="item quantity cannot be zero.";
        }
        else if (itemQuantity < 0) {
            error="item quantity cannot be negative.";
        }
        if (error != null) {
            throw new IllegalArgumentException(error);
        }

        while(purchasedItemRepository.existsById(curID)){
            curID++;
        }

        purchasedItem.setItem(item);
        purchasedItem.setItemQuantity(itemQuantity);
        purchasedItem.setPurchasedItemID(curID);
        if (orderType.equals("Delivery")) deliveryOrderService.addPurchasedItemToDeliveryOrder(confirmationNumber, purchasedItem);
        else if (orderType.equals("Pickup")) pickupOrderService.addPurchasedItemToPickupOrder(confirmationNumber,purchasedItem);
        purchasedItemRepository.save(purchasedItem);
        return purchasedItem;
    }

    @Transactional
    public PurchasedItem createPurchasedItem(String itemName, int itemQuantity, int confirmationNumber) {
        PurchasedItem purchasedItem = new PurchasedItem();
        String error = null;
        if (itemName== null || itemName.trim().length() == 0) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        Item item = itemService.getItem(itemName);

        if (!item.getPurchasable()) {
            error="item is not purchasable.";
        }
        else if (itemQuantity > item.getStock()) {
            error="itemQuantity cannot be greater than the stock.";
        }
        if (itemQuantity == 0) {
            error="item quantity cannot be zero.";
        }
        else if (itemQuantity < 0) {
            error="item quantity cannot be negative.";
        }
        if (error != null) {
            throw new IllegalArgumentException(error);
        }

        while(purchasedItemRepository.existsById(curID)){
            curID++;
        }

        purchasedItem.setItem(item);
        purchasedItem.setItemQuantity(itemQuantity);
        purchasedItem.setPurchasedItemID(curID);
        orderService.addPurchasedItemToOrder(confirmationNumber, purchasedItem);
        purchasedItemRepository.save(purchasedItem);
        return purchasedItem;
    }

    @Transactional
    public PurchasedItem updatePurchasedItemQuantity(int itemQuantity, int purchasedItemID) {
        String error = null;

        if (!purchasedItemRepository.existsById(purchasedItemID)) {
            error = "PurchasedItem doesn't exist.";
        }

        PurchasedItem purchasedItem = this.getPurchasedItem(purchasedItemID);
        Item item = purchasedItem.getItem();

        if (itemQuantity == 0) {
            error="item quantity cannot be zero.";
        }
        else if (itemQuantity < 0) {
            error="item quantity cannot be negative.";
        }

        if (error != null) {
            throw new IllegalArgumentException(error);
        }
        if(itemQuantity > item.getStock())
            throw new IllegalArgumentException("itemQuantity cannot be greater than the stock.");

        purchasedItem.setItemQuantity(itemQuantity);
        purchasedItemRepository.save(purchasedItem);
        return purchasedItem;
    }

}
