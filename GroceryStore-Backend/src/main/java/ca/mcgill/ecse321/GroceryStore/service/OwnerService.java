package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    StoreRepository storeRepository;

    @Transactional
    public Owner createOwner(String aEmail, String aUsername, String aPassword, String aAddress){
        Owner newOwner = new Owner();
        newOwner.setEmail(aEmail);
        newOwner.setUsername(aUsername);
        newOwner.setPassword(aPassword);
        newOwner.setStore(storeRepository.findAll().iterator().next());
        ownerRepository.save(newOwner);
        return newOwner;
    }

    @Transactional
    public Owner getOwner(String aUsername) {
        Owner owner = ownerRepository.findByUsername(aUsername);
        return owner;
    }

    public Store getOwnerStore(String aUsername){
        return ownerRepository.findByUsername(aUsername).getStore();
    }

}
