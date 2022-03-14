package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestOwnerService {
    private String OWNER_USERNAME = "testOwner";
    private String OWNER_EMAIL = "owner@mail.ca";
    private String OWNER_PASSWORD = "testPass";


    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private OwnerService ownerService;
    @InjectMocks
    private StoreService storeService;

    @Test
    public void testCreateOwner() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

        Owner owner = null;

        try{
            owner = ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, OWNER_PASSWORD);
        }catch(IllegalArgumentException e){
            fail();
        }
        assertNotNull(owner);
    }

    @Test
    public void testCreateOwnerNoStore() {

        //test stub
        when(storeRepository.findAll()).thenReturn(new ArrayList<>());

        Owner owner = null;
        String error = null;

        try{
            owner = ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, OWNER_PASSWORD);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("A store is needed to initialize an owner", error);
    }


    @Test
    public void testCreateOwnerNullName() {

        String username = null;
        Owner owner = null;
        String error = null;

        try{
            owner= ownerService.createOwner(username, OWNER_EMAIL, OWNER_PASSWORD);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(owner);
        assertEquals("Username can't be empty.",error);
    }

    @Test
    public void testCreateOwnerNullEmail() {

        String email = null;
        Owner owner = null;
        String error = null;

        try{
            owner= ownerService.createOwner(OWNER_USERNAME, email, OWNER_PASSWORD);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(owner);
        assertEquals("Email can't be empty.",error);
    }

    @Test
    public void testCreateOwnerNullPassword() {

        String password = null;
        Owner owner = null;
        String error = null;

        try{
            owner= ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, password);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(owner);
        assertEquals("Password can't be empty.",error);
    }

    @Test
    public void testGetOwner() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

        Owner owner1 = null;
        Owner owner2 = null;

        try{
            owner1 = ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, OWNER_PASSWORD);

            when(ownerRepository.findAll()).thenReturn(Arrays.asList(owner1));

            owner2 = ownerService.getOwner(owner1.getUsername());
        }catch(IllegalArgumentException e){
            fail();
        }
        assertEquals(owner1, owner2);
    }

    @Test
    public void testGetOwnerDoesNotExist() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

        Owner owner1 = null;
        Owner owner2 = null;
        String error = null;

        try{
            owner1 = ownerService.createOwner("owner1", OWNER_EMAIL, OWNER_PASSWORD);

            when(ownerRepository.findAll()).thenReturn(Arrays.asList(owner1));

            owner2 = ownerService.getOwner("owner2");
        }catch(IllegalArgumentException e){
            error= e.getMessage();
        }
        assertNull(owner2);
        assertEquals(error, "Invalid username: Either no Owner has this username or the string given was null");
    }

    @Test
    public void testGetOwnerNullUsername() {

        Owner owner = null;
        String username = null;
        String error = null;

        try{
            owner = ownerService.getOwner(username);
        }catch(IllegalArgumentException e){
            error= e.getMessage();
        }
        assertNull(owner);
        assertEquals(error, "Invalid username: Either no Owner has this username or the string given was null");
    }

    @Test
    public void testGetOwnerStore() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

        Owner owner = null;
        Store store2 = null;

        try{
            owner = ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, OWNER_PASSWORD);

            when(ownerRepository.findAll()).thenReturn(Arrays.asList(owner));

            store2 = ownerService.getOwnerStore(owner.getUsername());
        }catch(IllegalArgumentException e){
            fail();
        }
        assertEquals(store, store2);
    }

    @Test
    public void testCreateOwnerDuplicateUsername() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub to create owner because we need a store
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

        Owner owner1 = null;
        Owner owner2 = null;
        String error = null;


        when(ownerRepository.findAll()).thenReturn(Arrays.asList());

        try{
            owner1 = ownerService.createOwner(OWNER_USERNAME, "email1@mail.com", OWNER_PASSWORD);
            when(ownerRepository.findAll()).thenReturn(Arrays.asList(owner1));

            owner2 = ownerService.createOwner(OWNER_USERNAME, "email2@mail.com", OWNER_PASSWORD);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNotNull(owner1);
        assertNull(owner2);
        assertEquals("An identical owner already exists.",error);
    }

    @Test
    public void testCreateOwnerDuplicateEmail() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub to create owner because we need a store
        when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

        Owner owner1 = null;
        Owner owner2 = null;
        String error = null;

        when(ownerRepository.findAll()).thenReturn(Arrays.asList());

        try{
            owner1 = ownerService.createOwner("boss1", OWNER_EMAIL, OWNER_PASSWORD);

            when(ownerRepository.findAll()).thenReturn(Arrays.asList(owner1));

            owner2 = ownerService.createOwner("boss2", OWNER_EMAIL, OWNER_PASSWORD);

        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNotNull(owner1);
        assertNull(owner2);
        assertEquals("An identical owner already exists.",error);
    }
}
