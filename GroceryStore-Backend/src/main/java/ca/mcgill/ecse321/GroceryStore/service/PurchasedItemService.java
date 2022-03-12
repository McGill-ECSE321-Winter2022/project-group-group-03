package ca.mcgill.ecse321.GroceryStore.service;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchasedItemService {

    @Autowired
    PurchasedItemRepository purchasedItemRepository;

    @Autowired
    ItemRepository itemRepository;

    private static int curID = 90000;

    @Transactional
    public void deletePurchasedItem(int purchasedItemID) {
        PurchasedItem pItem= purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        if (pItem == null ) {
            throw new IllegalArgumentException("The purchased item with ID: " + purchasedItemID + " does not exist.");
        } else {
            purchasedItemRepository.deleteById(purchasedItemID);
        }
                purchasedItemRepository.deleteById(purchasedItemID);
    }

    @Transactional
    public PurchasedItem getPurchasedItem(int purchasedItemID) {
        return purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
    }

    @Transactional
    public Item getItem(String name) {
        Item item = itemRepository.findByName(name);
        return item;
    }

    @Transactional
    public List<PurchasedItem>  getAllPurchasedItem() {
        return toList(purchasedItemRepository.findAll());
    }

    @Transactional
    public PurchasedItem createPurchasedItem(Item aItem, int aItemQuantity) {
        PurchasedItem purchasedItem = new PurchasedItem();

        purchasedItem.setItem(aItem);
        purchasedItem.setItemQuantity(aItemQuantity);

        purchasedItem.setPurchasedItemID(curID++);

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
