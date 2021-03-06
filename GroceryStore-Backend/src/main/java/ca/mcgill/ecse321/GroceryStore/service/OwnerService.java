package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.model.Employee;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    /**
     * This method would create an owner object in our database with the right checks
     * @param aUsername is the username of the owner that is being created
     * @param aEmail is the email of the owner that is being created
     * @param aPassword is the password of the owner that is being created
     * @return the owner object that was created
     */


    @Transactional
    public Owner createOwner(String aUsername, String aEmail, String aPassword){
        if (aUsername==null || aUsername.equals("")) throw new IllegalArgumentException("Username can't be empty.");
        if (aEmail==null  || aEmail.equals("")) throw new IllegalArgumentException("Email can't be empty.");

        if (aEmail.indexOf("@") <= 0 ||
                aEmail.indexOf("@") != aEmail.lastIndexOf("@") ||
                aEmail.indexOf("@") >= aEmail.lastIndexOf(".") - 1 ||
                aEmail.lastIndexOf(".") >= aEmail.length() - 1)  throw new IllegalArgumentException( "Invalid email");
        if (aPassword==null || aPassword.equals("")) throw new IllegalArgumentException("Password can't be empty.");

        for (Owner owner : ownerRepository.findAll()){
            if (owner.getUsername().equals(aUsername)) throw new IllegalArgumentException("An identical owner already exists.");
            if (owner.getEmail().equals(aEmail)) throw new IllegalArgumentException("An identical owner already exists.");
        }
        for (Customer customer : customerRepository.findAll()){
            if (customer.getUsername().equals(aUsername)) throw new IllegalArgumentException("A customer already has this username");
            if (customer.getEmail().equals(aEmail)) throw new IllegalArgumentException("A customer already has this email");
        }
        for (Employee employee : employeeRepository.findAll()){
            if (employee.getUsername().equals(aUsername)) throw new IllegalArgumentException("A customer already has this username");
            if (employee.getEmail().equals(aEmail)) throw new IllegalArgumentException("A customer already has this email");
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
        
        return newOwner;
    }
    /**
     * gets the owner of the store
     * @param aUsername the username of the owner we are trying to search
     *@return the owner of the store with said username
     */
    @Transactional
    public Owner getOwner(String aUsername) {
        if (aUsername == null || aUsername.equals(""))throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
            for(Owner owner : ownerRepository.findAll()){
                if (owner.getUsername().equals(aUsername)) return owner;
            }

        throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }

    /**
     * Gets all the owners that are in the system
     * @return a list of owners
     */
    @Transactional
    public List<Owner> getOwners(){
        List<Owner> owners = new ArrayList<>();
        for(Owner o: ownerRepository.findAll()) owners.add(o);
        return owners;
    }

    /**
     * login to the store system as owner
     * @param username the username of the owner we are trying to log in as
     * @param password the password of the owner we are trying to log in as
     *@return the owner of the store with said username and password
     */
    @Transactional
    public Owner loginOwner(String username, String password){
        Owner owner = getOwner(username);
        if (owner.getPassword().equals(password)) return owner;
        throw new IllegalArgumentException("Wrong password was given for username: " + username);
    }

    /**
     * update the login credentials of the store owner
     * @param username the username of the owner we are trying to log in as
     * @param password the password of the owner we are trying to log in as
     *@return the owner of the store with said username and password
     */
    @Transactional
    public Owner updateOwner(String username, String password){
        if (password == null || password.equals("")) throw new IllegalArgumentException("Password cannot be empty");
        Owner o = getOwner(username);
        o.setPassword(password);
        return o;
    }

    /**
     * Deletes the owner that is associated to that specific username
     * @param aUsername the username that is associated to the owner
     */
    @Transactional
    public void deleteOwner(String aUsername){
        if (aUsername == null || aUsername.equals("")) throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");

            for(Owner owner : ownerRepository.findAll()){
                if (owner.getUsername().equals(aUsername)) {
                    ownerRepository.deleteById(aUsername);
                    return;
                }

        }
        throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }

    /**
     * return the store of the owener with the given username
     * @param aUsername the username of the owner
     *@return the store of the owner with said username
     */
    @Transactional
    public Store getOwnerStore(String aUsername) {
        if (aUsername == null  || aUsername.equals("")) throw new IllegalArgumentException("Invalid username: the string given was null");

            for(Owner owner : ownerRepository.findAll()){
                if (owner.getUsername().equals(aUsername)) return owner.getStore();

        }
        throw new IllegalArgumentException("Invalid username: Either no Owner has this username or the string given was null");
    }
}
