package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class TestStoreService {
    private int STORE_ID_KEY = 123;
    private String ADDRESS = "3064 rue edmond rostand";
    private int CURRENT_ACTIVE_DELIVERY = 0;
    private int CURRENT_ACTIVE_PICKUP = 0;

    @Mock
    private StoreRepository storeRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private BusinessHourRepository businessHourRepository;
    @Mock
    private HolidayRepository holidayRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private StoreService storeService;
    @InjectMocks
    private EmployeeService employeeService;
    @InjectMocks
    private BusinessHourService businessHourService;
    @InjectMocks
    private HolidayService holidayService;
    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(storeRepository.findById(any(int.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(STORE_ID_KEY)) {
                Store store = new Store();
                store.setStoreID(STORE_ID_KEY);
                store.setAddress(ADDRESS);
                store.setCurrentActiveDelivery(CURRENT_ACTIVE_DELIVERY);
                store.setCurrentActivePickup(CURRENT_ACTIVE_PICKUP);

                return store;
            } else {
                return null;
            }
        });
        lenient().when(storeRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(STORE_ID_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(storeRepository.save(any(Store.class))).thenAnswer(returnParameterAsAnswer);
    }
    @Test
    public void testCreateStore(){
        Store store = null;

        try{
            store = storeService.createStore(STORE_ID_KEY, ADDRESS, CURRENT_ACTIVE_DELIVERY, CURRENT_ACTIVE_PICKUP);
        }catch(IllegalArgumentException e){
            fail();
        }
        assertNotNull(store);
    }

    

}
