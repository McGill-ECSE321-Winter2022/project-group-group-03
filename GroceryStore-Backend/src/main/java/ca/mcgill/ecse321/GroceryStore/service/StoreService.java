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
        Store newStore = new Store();

        int count = 0;
        for (Store s : storeRepository.findAll()) count++;
        if (count >= 1) throw new IllegalArgumentException("There can't be more than one store");

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
        newStore.setStoreID(presetStoreID);
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
    public Store getStore(){
        int count = 0;
        for (Store s : storeRepository.findAll()) count++;
        if (count == 0) throw new IllegalArgumentException("There is no store yet.");
        return storeRepository.findAll().iterator().next();
    }

    @Transactional
    public List<Holiday> getStoreHolidays(){
        return getStore().getHoliday();
    }
    @Transactional
    public List<Item> getItems(){
        return getStore().getItem();
    }

    @Transactional
    public List<Employee> getEmployees(){
        return getStore().getEmployee();
    }

    @Transactional
    public List<BusinessHour> getBusinessHours(){
        return getStore().getBusinessHour();
    }

    @Transactional
    public Store setActiveDelivery(Integer activeDelivery){
        if(activeDelivery == null)
            throw new IllegalArgumentException("Active delivery can't be empty.");
        if(activeDelivery < 0)
            throw new IllegalArgumentException("Active delivery can't be negative.");
        Store store = getStore();
        store.setCurrentActiveDelivery(activeDelivery);
        storeRepository.save(store);
        return store;
    }

    /**
     * The four add functions return void because they are called in the create functions,
     * so their output is never used that function returns the created object, not the list in the
     * association
     */

    @Transactional
    public void addHoliday(Holiday holiday) {
        if (holiday==null) throw new IllegalArgumentException("A Holiday parameter is needed");
        List<Holiday> holidays = getStore().getHoliday();
        holidays.add(holiday);
        storeRepository.save(getStore());
    }

    @Transactional
    public void addEmployee(Employee employee) {
        if (employee==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<Employee> employees = getStore().getEmployee();
        employees.add(employee);
    }

    @Transactional
    public void addItem(Item item) {
        if (item==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<Item> items = getStore().getItem();
        items.add(item);
        storeRepository.save(getStore());
    }

    @Transactional
    public void addBusinessHour(BusinessHour businessHour) {
        if (businessHour==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<BusinessHour> businessHours = getStore().getBusinessHour();
        businessHours.add(businessHour);
    }

    @Transactional
    public Store setActivePickup(Integer activePickup){
        if(activePickup == null)
            throw new IllegalArgumentException("Active pickup can't be empty.");
        if(activePickup < 0)
            throw new IllegalArgumentException("Active pickup can't be negative.");
        Store store = getStore();
        store.setCurrentActivePickup(activePickup);
        return store;
    }

    @Transactional
    public Store decrementActivePickup(){
        Store store = getStore();
        if (store.getCurrentActivePickup()-1 < 0) throw new IllegalArgumentException("Active pickups can't be negative.");
        store.setCurrentActivePickup(store.getCurrentActivePickup() -1);
        return store;
    }

    @Transactional
    public Store incrementActivePickup(){
        Store store = getStore();
        if (store.getCurrentActivePickup()+1 < Store.MAXPICKUPS) throw new IllegalArgumentException("Active pickups can't be exceed max: "+Store.MAXPICKUPS);
        store.setCurrentActivePickup(store.getCurrentActivePickup() +1);
        return store;
    }

    @Transactional
    public Store decrementActiveDelivery(){
        Store store = getStore();
        if (store.getCurrentActiveDelivery()-1 < 0) throw new IllegalArgumentException("Active deliveries can't be negative.");
        store.setCurrentActiveDelivery(store.getCurrentActiveDelivery() -1);
        return store;
    }

    @Transactional
    public Store incrementActiveDelivery(){
        Store store = getStore();
        if (store.getCurrentActiveDelivery()+1 < Store.MAXSHIPPING) throw new IllegalArgumentException("Active deliveries can't be exceed max: "+Store.MAXSHIPPING);
        store.setCurrentActiveDelivery(store.getCurrentActiveDelivery() +1);
        return store;
    }

    @Transactional
    public Store setAddress(String address){
        if(address == null || address.equals("") || address.equals(" ")){
            throw new IllegalArgumentException("Address can't be empty.");
        }
        Store store = getStore();
        store.setAddress(address);
        return store;
    }




}
