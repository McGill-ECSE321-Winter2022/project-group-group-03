package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    StoreRepository storeRepository;

    @Transactional
    public Owner createOwner(String aUsername, String aEmail, String aPassword){
        if (aUsername==null) throw new IllegalArgumentException("Username can't be empty.");
        if (aEmail==null) throw new IllegalArgumentException("Email can't be empty.");
        if (aPassword==null) throw new IllegalArgumentException("Password can't be empty.");

        if(ownerRepository.existsById(aUsername)) throw new IllegalArgumentException("An identical owner already exists.");

        Owner newOwner = new Owner();
        newOwner.setEmail(aEmail);
        newOwner.setUsername(aUsername);
        newOwner.setPassword(aPassword);
        System.out.println(storeRepository.findAll().toString());
        newOwner.setStore(storeRepository.findAll().iterator().next());
        ownerRepository.save(newOwner);
        return newOwner;
    }

    @Transactional
    public Owner getOwner(String aUsername) {
        if (aUsername != null && ownerRepository.findByUsername(aUsername) != null)
        return ownerRepository.findByUsername(aUsername);
        else throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }

    @Transactional
    public Store getOwnerStore(String aUsername) {
        if (aUsername != null && ownerRepository.findByUsername(aUsername) != null)
        return ownerRepository.findByUsername(aUsername).getStore();
        else throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }

}
