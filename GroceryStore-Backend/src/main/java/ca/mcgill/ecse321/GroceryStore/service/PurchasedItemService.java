package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
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
        PurchasedItem pItem = purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        if (pItem == null) {
            throw new IllegalArgumentException("The purchased item with ID: " + purchasedItemID + " does not exist.");
        } else {
            return purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        }
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
        return toList(purchasedItemRepository.findAll());
    }


    @Transactional
    public PurchasedItem createPurchasedItem(Item aItem, int itemQuantity) {
        PurchasedItem purchasedItem = new PurchasedItem();
        List <PurchasedItem> purchasedItems = this.getAllPurchasedItem();
        String error = null;
        PurchasedItem purchasedItem1 = purchasedItemRepository.findByPurchasedItemID(curID);


        if (aItem == null) {
            error="item cannot be null.";
        }

       else if (itemQuantity > aItem.getStock()) {
           System.out.println("print");
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

        purchasedItem.setItem(aItem);
        purchasedItem.setItemQuantity(itemQuantity);
        purchasedItem.setPurchasedItemID(curID);
        purchasedItemRepository.save(purchasedItem);
        return purchasedItem;
    }

    @Transactional
    public Item getPurchasedItemItem (Integer id){
        if (id != null && purchasedItemRepository.findByPurchasedItemID(id) != null)
            return purchasedItemRepository.findByPurchasedItemID(id).getItem();
        else throw new IllegalArgumentException("Invalid id: Either no PurchasedItem has this id or the id given was null");
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }


}
