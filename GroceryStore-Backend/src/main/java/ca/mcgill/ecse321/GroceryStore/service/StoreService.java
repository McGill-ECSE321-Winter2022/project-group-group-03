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
    private static int presetStoreID = 123;
    @Autowired
    StoreRepository storeRepository;

    @Transactional
    public Store createStore(String aAddress, Integer aCurrentActiveDelivery, Integer aCurrentActivePickup){
        Store store = new Store();



        if(aAddress == null || aAddress.equals("") || aAddress.equals(" ")){
            throw new IllegalArgumentException("Address can't be empty.");
        }
        else if(aCurrentActiveDelivery == null){
            throw new IllegalArgumentException("Active delivery can't be null.");
        }
        else if(aCurrentActiveDelivery < 0){
            throw new IllegalArgumentException("Active delivery can't be negative.");
        }
        else  if(aCurrentActivePickup == null){
            throw new IllegalArgumentException("Active pickup can't be null.");
        }
        else  if(aCurrentActivePickup < 0){
            throw new IllegalArgumentException("Active pickup can't be negative.");
        }
        while(storeRepository.existsById(presetStoreID)){
            presetStoreID++;
        }

        List<Holiday> holidays = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<BusinessHour> businessHours = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        Store newStore = new Store();
        store.setStoreID(presetStoreID);
        newStore.setAddress(aAddress);
        newStore.setCurrentActiveDelivery(aCurrentActiveDelivery);
        newStore.setCurrentActivePickup(aCurrentActivePickup);
        newStore.setHoliday(holidays);
        newStore.setEmployee(employees);
        newStore.setBusinessHour(businessHours);
        newStore.setItem(items);
        storeRepository.save(newStore);
        return newStore;
    }
    @Transactional
    public Store getStore(Integer storeID){
        if(storeID == null) {
            throw new IllegalArgumentException("Store ID can't be null.");
        }
        if(storeID <= 0) {
            throw new IllegalArgumentException("Store ID must be greater than 0.");
        }
        if(!storeRepository.existsById(storeID))
            throw new IllegalArgumentException("Store isn't registered in the system.");

        return storeRepository.findById((int)storeID);
    }
    @Transactional
    public List<Store> getAllStores(){
        return toList(storeRepository.findAll());
    }
    @Transactional
    public List<Holiday> getStoreHolidays(Integer storeId){
        if(storeId == null)
            throw  new IllegalArgumentException("Store Id can't be null.");
        if(storeId <= 0)
            throw new IllegalArgumentException("Store Id must be greater than 0.");
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        return storeRepository.findById((int)storeId).getHoliday();
    }
    @Transactional
    public List<Item> getItems(Integer storeId){
        if(storeId == null)
            throw  new IllegalArgumentException("Store Id can't be null.");
        if(storeId <= 0)
            throw new IllegalArgumentException("Store Id must be greater than 0.");
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        return storeRepository.findById((int)storeId).getItem();
    }

    @Transactional
    public List<Employee> getEmployees(Integer storeId){
        if(storeId == null)
            throw  new IllegalArgumentException("Store Id can't be null.");
        if(storeId <= 0)
            throw new IllegalArgumentException("Store Id must be greater than 0.");
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        return storeRepository.findById((int)storeId).getEmployee();
    }

    @Transactional
    public List<BusinessHour> getBusinessHours(Integer storeId){
        if(storeId == null)
            throw  new IllegalArgumentException("Store Id can't be null.");
        if(storeId <= 0)
            throw new IllegalArgumentException("Store Id must be greater than 0.");
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        return storeRepository.findById((int)storeId).getBusinessHour();
    }

    @Transactional
    public Store setActiveDelivery(int storeId, Integer activeDelivery){
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        if(activeDelivery == null)
            throw new IllegalArgumentException("Active delivery can't be empty.");
        if(activeDelivery < 0)
            throw new IllegalArgumentException("Active delivery can't be negative.");

        Store store = getStore(storeId);
        store.setCurrentActiveDelivery(activeDelivery);
        return store;
    }
    @Transactional
    public Store setActivePickup(int storeId, Integer activePickup){
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        if(activePickup < 0)
            throw new IllegalArgumentException("Active pickup can't be negative.");
        if(activePickup == null)
            throw new IllegalArgumentException("Active pickup can't be null");
        Store store = getStore(storeId);
        store.setCurrentActivePickup(activePickup);
        return store;
    }
    @Transactional
    public Store setAddress(int storeId, String address){
        if(!storeRepository.existsById(storeId))
            throw new IllegalArgumentException("Store isn't registered in the system.");
        if(address == null || address.equals("") || address.equals(" ")){
            throw new IllegalArgumentException("Address can't be empty.");
        }
        Store store = getStore(storeId);
        store.setAddress(address);
        return store;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }






}
