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

    /**
     * Creates a store obejct in our backend with the credentials of said purchased item
     * @param aAddress the address of the store
     * @param aCurrentActiveDelivery the number of active deliveries
     * @param aCurrentActivePickup the number of active pickups
     * @return the store that was created
     */
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


    /**
     * This method gets the store object
     * @return the store
     */
    @Transactional
    public Store getStore(){
        int count = 0;
        for (Store s : storeRepository.findAll()) count++;
        if (count == 0) throw new IllegalArgumentException("There is no store yet.");
        return storeRepository.findAll().iterator().next();
    }

    /**
     * Gets all the store holidays in the system
     * @return a list of holidays
     */
    @Transactional
    public List<Holiday> getStoreHolidays(){
        return getStore().getHoliday();
    }
    /**
     * Gets all the items in the system
     * @return a list of items
     */
    @Transactional
    public List<Item> getItems(){
        return getStore().getItem();
    }

    /**
     * Gets all the employees in the system
     * @return a list of employees
     */
    @Transactional
    public List<Employee> getEmployees(){
        return getStore().getEmployee();
    }

    /**
     * Gets all the business hours in the system
     * @return a list of business hours
     */
    @Transactional
    public List<BusinessHour> getBusinessHours(){
        return getStore().getBusinessHour();
    }

    /**
     * This method sets the number of active deliveries
     * @param activeDelivery it takes in the quantity of active deliveries
     * @return the store with updated active delivery
     */
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

    /**
     * This method adds a holiday that will be associated with the store to the system
     * @param holiday it takes in the holiday that will be added
     */
    @Transactional
    public void addHoliday(Holiday holiday) {
        if (holiday==null) throw new IllegalArgumentException("A Holiday parameter is needed");
        List<Holiday> holidays = getStore().getHoliday();
        holidays.add(holiday);
        storeRepository.save(getStore());
    }

    /**
     * This method adds a employee that will be associated with the store to the system
     * @param employee it takes in the employee that will be added
     */
    @Transactional
    public void addEmployee(Employee employee) {
        if (employee==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<Employee> employees = getStore().getEmployee();
        employees.add(employee);
    }

    /**
     * This method adds a item that will be associated with the store to the system
     * @param item it takes in the item that will be added
     */
    @Transactional
    public void addItem(Item item) {
        if (item==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<Item> items = getStore().getItem();
        items.add(item);
        storeRepository.save(getStore());
    }

    /**
     * This method adds a business hour that will be associated with the store to the system
     * @param businessHour it takes in the business hour that will be added
     */
    @Transactional
    public void addBusinessHour(BusinessHour businessHour) {
        if (businessHour==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<BusinessHour> businessHours = getStore().getBusinessHour();
        businessHours.add(businessHour);
    }

    /**
     * This method adds a quantity of active pickups that will be associated with the store to the system
     * @param activePickup it takes in the quantity of active pickups that will be added
     */
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

    /**
     * This method decrements the number of active pickups by 1
     */
    @Transactional
    public Store decrementActivePickup(){
        Store store = getStore();
        if (store.getCurrentActivePickup()-1 < 0) throw new IllegalArgumentException("Active pickups can't be negative.");
        store.setCurrentActivePickup(store.getCurrentActivePickup() -1);
        return store;
    }

    /**
     * This method increments the number of active pickups by 1
     */
    @Transactional
    public Store incrementActivePickup(){
        Store store = getStore();
        int i = store.getCurrentActivePickup();
        if (store.getCurrentActivePickup()+1 > Store.MAXPICKUPS) throw new IllegalArgumentException("Active pickups can't exceed max: "+Store.MAXPICKUPS+ ",right now it is: "+i);
        store.setCurrentActivePickup(store.getCurrentActivePickup() +1);
        return store;
    }

    /**
     * This method decrements the number of active deliveries by 1
     */
    @Transactional
    public Store decrementActiveDelivery(){
        Store store = getStore();
        if (store.getCurrentActiveDelivery()-1 < 0) throw new IllegalArgumentException("Active deliveries can't be negative.");
        store.setCurrentActiveDelivery(store.getCurrentActiveDelivery() -1);
        return store;
    }

    /**
     * This method increments the number of active deliveries by 1
     */
    @Transactional
    public Store incrementActiveDelivery(){
        Store store = getStore();
        int i = store.getCurrentActiveDelivery();
        if (store.getCurrentActiveDelivery()+1 > Store.MAXSHIPPING) throw new IllegalArgumentException("Active deliveries can't exceed max: "+Store.MAXSHIPPING+ ",right now it is: "+i);
        store.setCurrentActiveDelivery(store.getCurrentActiveDelivery() +1);
        return store;
    }

    /**
     * This method sets the address of the store
     */
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
