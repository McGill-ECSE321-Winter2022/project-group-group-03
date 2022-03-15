package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Transactional
    public Store createStore(Integer storeID, String aAddress, Integer aCurrentActiveDelivery, Integer aCurrentActivePickup){
        Store store = new Store();
        List<Store> stores = this.getAllStores();
        if(storeID == null) {
            throw new IllegalArgumentException("Store ID can't be null.");
        }
        else if(storeID <= 0){
            throw new IllegalArgumentException("Store ID must be greater than 0.");
        }
        else if(aAddress == null || aAddress.equals("") || aAddress.equals(" ")){
            throw new IllegalArgumentException("Address can't be empty.");
        }
        else if(aCurrentActiveDelivery == null){
            throw new IllegalArgumentException("Current active delivery can't be null.");
        }
        else if(aCurrentActiveDelivery < 0){
            throw new IllegalArgumentException("Current active delivery can't be negative.");
        }
        else  if(aCurrentActivePickup == null){
            throw new IllegalArgumentException("Current active pickup can't be null.");
        }
        else  if(aCurrentActivePickup < 0){
            throw new IllegalArgumentException("Current active pickup can't be negative.");
        }
        else if (stores != null && stores.size() != 0) {
            for (Store s : stores) {
                if (s.getStoreID() == (storeID)) {
                    throw  new IllegalArgumentException("An identical store with the same store ID already exists.");
                }
            }
        }
        Store newStore = new Store();
        newStore.setStoreID(storeID);
        newStore.setAddress(aAddress);
        newStore.setCurrentActiveDelivery(aCurrentActiveDelivery);
        newStore.setCurrentActivePickup(aCurrentActivePickup);
        storeRepository.save(newStore);
        return newStore;
    }
    @Transactional
    public Store getStore(int storeID){
        Store st = storeRepository.findById(storeID);
        if(st == null) {
            throw new IllegalArgumentException("Store ID can't be null.");
        }
        if(storeID <= 0) {
            throw new IllegalArgumentException("Store ID must be greater than 0.");
        }
        return storeRepository.findById(storeID);
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
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }






}
