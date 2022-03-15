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
        if (aUsername==null || aUsername.equals("")) throw new IllegalArgumentException("Username can't be empty.");
        if (aEmail.indexOf("@") <= 0 ||
                aEmail.indexOf("@") != aEmail.lastIndexOf("@") ||
                aEmail.indexOf("@") >= aEmail.lastIndexOf(".") - 1 ||
                aEmail.lastIndexOf(".") >= aEmail.length() - 1)  throw new IllegalArgumentException( "Invalid email");
        if (aEmail==null  || aEmail.equals("")) throw new IllegalArgumentException("Email can't be empty.");
        if (aPassword==null || aPassword.equals("")) throw new IllegalArgumentException("Password can't be empty.");

        for (Owner owner : ownerRepository.findAll()){
            if (owner.getUsername().equals(aUsername)) throw new IllegalArgumentException("An identical owner already exists.");
            if (owner.getEmail().equals(aEmail)) throw new IllegalArgumentException("An identical owner already exists.");
        }

        Owner newOwner = new Owner();
        newOwner.setUsername(aUsername);
        newOwner.setEmail(aEmail);
        newOwner.setPassword(aPassword);
        try{
            newOwner.setStore(storeRepository.findAll().iterator().next());
        }catch(Exception e){
            throw new IllegalArgumentException("A store is needed to initialize an owner");
        }
        ownerRepository.save(newOwner);
        System.out.println(newOwner.getStore());
        return newOwner;
    }

    @Transactional
    public Owner getOwner(String aUsername) {
        if (aUsername != null || !aUsername.equals("")) {
            for(Owner owner : ownerRepository.findAll()){
                if (owner.getUsername().equals(aUsername)) return owner;
            }
        }
        throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }

    @Transactional
    public Owner updateOwner(String username, String password){
        if (password == null || !password.equals("")) throw new IllegalArgumentException("Password cannot be empty");
        Owner o = getOwner(username);
        o.setPassword(password);
        return o;
    }

    @Transactional
    public void deleteOwner(String aUsername){
        if (aUsername != null || !aUsername.equals("")) {
            for(Owner owner : ownerRepository.findAll()){
                if (owner.getUsername().equals(aUsername)) {
                    owner = null;
                    ownerRepository.deleteById(aUsername);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }


    @Transactional
    public Store getOwnerStore(String aUsername) {
        if (aUsername != null  || !aUsername.equals("")) {
            for(Owner owner : ownerRepository.findAll()){
                if (owner.getUsername().equals(aUsername)) return owner.getStore();
            }
        }
        throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }

}
