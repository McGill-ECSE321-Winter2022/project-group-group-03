package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    @Transactional
    public Store getStore(int storeId){
        Store store = storeRepository.findById(storeId);
        return store;
    }
    @Transactional
    public List<Store> getAllStores(){
        return toList(storeRepository.findAll());
    }
    @Transactional
    public List<Holiday> getStoreHolidays(int storeId){
        return storeRepository.findById(storeId).getHoliday();
    }
    @Transactional
    public List<Item> getItems(int storeId){
        return storeRepository.findById(storeId).getItem();
    }

    @Transactional
    public List<Employee> getEmployees(int storeId){
        return storeRepository.findById(storeId).getEmployee();
    }

    @Transactional
    public List<BusinessHour> getBusinessHours(int storeId){
        return storeRepository.findById(storeId).getBusinessHour();
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }






}
