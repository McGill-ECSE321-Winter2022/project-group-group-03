package ca.mcgill.ecse321.GroceryStore.service;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class PurchasedItemService {

    @Autowired
    PurchasedItemRepository purchasedItemRepository;

    @Transactional
    public void deletePurchasedItem(int purchasedItemID) {
        purchasedItemRepository.deleteById(purchasedItemID);
    }

    @Transactional
    public PurchasedItem getPurchasedItem(int purchasedItemID) {
        return purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
    }

    @Transactional
    public PurchasedItem getAllPurchasedItem() {
        return (PurchasedItem) toList(purchasedItemRepository.findAll());
    }

    @Transactional
    public PurchasedItem createPurchasedItem(Item item, int aItemQuantity, int aPurchasedItem) {
        PurchasedItem purchasedItem = new PurchasedItem();

        purchasedItem.setPurchasedItemID(aPurchasedItem);
        purchasedItem.setItem(item);
        purchasedItem.setItemQuantity(aItemQuantity);

        purchasedItemRepository.save(purchasedItem);
        return purchasedItem;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }



}
