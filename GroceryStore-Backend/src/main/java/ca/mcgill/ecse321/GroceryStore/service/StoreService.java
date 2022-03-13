package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Transactional
    public Store createStore(int StoreID, String aAddress, int aCurrentActiveDelivery, int aCurrentActivePickup){
        Store newStore = new Store();
        newStore.setStoreID(StoreID);
        newStore.setAddress(aAddress);
        newStore.setCurrentActiveDelivery(aCurrentActiveDelivery);
        newStore.setCurrentActivePickup(aCurrentActivePickup);
        storeRepository.save(newStore);
        return newStore;
    }



}
