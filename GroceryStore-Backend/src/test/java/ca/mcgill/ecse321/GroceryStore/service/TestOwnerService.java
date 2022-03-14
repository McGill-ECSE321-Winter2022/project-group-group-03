package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
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

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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



    @BeforeEach
    public void setMockOutput() {

        lenient().when(ownerRepository.findByUsername(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(OWNER_USERNAME)) {
                ArrayList<Owner> owners = new ArrayList<Owner>();
                Owner owner= new Owner();
                owner.setUsername(OWNER_USERNAME);
                owner.setEmail(OWNER_EMAIL);
                owner.setPassword(OWNER_PASSWORD);
                owners.add(owner);
                return owners;
            } else {
                return new ArrayList<Owner>();
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(ownerRepository.save(any(Owner.class))).thenAnswer(returnParameterAsAnswer);
    }


    @Test
    public void testCreateOwner() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub
        ArrayList<Store> ls = new ArrayList<>();
        ls.add(store);
        when(storeRepository.findAll()).thenReturn(ls);

        String username = "BossMan";
        String email = "evilboss@mail.com";
        String password = "ih8employees";
        Owner owner = null;

        try{
            owner = ownerService.createOwner(username, email, password);
        }catch(IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void testCreateOwnerNullName() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);



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

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);



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

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);



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
    public void testCreateOwnerDuplicate() {

        Store store = storeService.createStore(15, "MTL", 9, 8);
        storeRepository.save(store);

        //test stub to create owner because we need a store
        ArrayList<Store> ls = new ArrayList<>();
        ls.add(store);
        when(storeRepository.findAll()).thenReturn(ls);



        Owner owner1 = null;
        Owner owner2 = null;
        String error = null;


        when(ownerRepository.existsById(anyString())).thenReturn(Objects.nonNull(owner1));

        try{
            owner1 = ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, OWNER_PASSWORD);
            when(ownerRepository.existsById(anyString())).thenReturn(Objects.nonNull(owner1));
            owner2 = ownerService.createOwner(OWNER_USERNAME, OWNER_EMAIL, OWNER_PASSWORD);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        //verify(ownerRepository, never()).save(any(Owner.class));
        assertNotNull(owner1);

        assertNull(owner2);
        assertEquals("An identical owner already exists.",error);
    }

}
