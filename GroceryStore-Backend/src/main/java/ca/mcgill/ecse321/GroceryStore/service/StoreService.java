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
        return store;
    }

    public List<Holiday> addHoliday( Holiday holiday) {
        if (holiday==null) throw new IllegalArgumentException("A Holiday parameter is needed");
        List<Holiday> holidays = getStore().getHoliday();
        holidays.add(holiday);
        return holidays;
    }

    public List<Employee> addEmployee(Employee employee) {
        if (employee==null) throw new IllegalArgumentException("An Employee parameter is needed");
        List<Employee> employees = getStore().getEmployee();
        employees.add(employee);
        return employees;
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
    public Store setAddress(String address){
        if(address == null || address.equals("") || address.equals(" ")){
            throw new IllegalArgumentException("Address can't be empty.");
        }
        Store store = getStore();
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
