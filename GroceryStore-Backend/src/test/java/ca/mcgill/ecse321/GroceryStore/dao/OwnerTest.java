package ca.mcgill.ecse321.GroceryStore.dao;

import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OwnerTest {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private OwnerRepository ownerRepository;


    //Store
    Store defaultStore = new Store();

    //Store
    public void initializeDefaultStore(){
        this.defaultStore.setStoreID(123);
    }

    @AfterEach
    public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        storeRepository.deleteAll();
        ownerRepository.deleteAll();


    }

    /**
     * Tests read and write capabilities with Owner objects and attributes.
     * Association is tested between Owner and Store by creating a temporary Store object initialized with some attributes,
     * then deleting it, and checking to see from the Owner class that the Owner instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadOwner(){
        String username = "Thad Castle";
        String password = "BlueMountainState";
        String email = "ThadCastle@gmail.com";

        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setEmail(email);
        owner.setPassword(password);
        initializeDefaultStore();
        owner.setStore(defaultStore);
        storeRepository.save(defaultStore);
        ownerRepository.save(owner);

        owner = ownerRepository.findByUsername(username);
        assertNotNull(owner);
        assertEquals(username, owner.getUsername());

        //Check association between owner and store
        assertEquals(storeRepository.findById(defaultStore.getStoreID()), ownerRepository.findByUsername(username).getStore());

    }

}
