package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
    private static final String USERNAME_KEY = "edward";
    //private static final String NONEXISTING_KEY = "notedward";
    private static final String PASSWORD = "123456";
    private static final String EMAIL = "edward@gmail.com";
    private static final String ADDRESS = "3064 rue Edmond Rostand";
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private PickupOrderRepository pickupOrderRepository;

    @InjectMocks
    private CustomerService customerService;
    @InjectMocks
    private DeliveryOrderService deliveryOrderService;
    @InjectMocks
    private PickupOrderService pickupOrderService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(customerRepository.findCustomerByUsername(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME_KEY)) {
                ArrayList<Customer> customers = new ArrayList<Customer>();
                Customer customer = new Customer();
                customer.setUsername(USERNAME_KEY);
                customer.setPassword(PASSWORD);
                customer.setEmail(EMAIL);
                customer.setAddress(ADDRESS);
                return customer;
            } else {
                return null;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
    }
    //CREATE CUSTOMER

    @Test
    public void testCreateCustomer() {
        createSaveOrders();

        String name = "Edward";
        String password = "lol";
        String email = "edward@hotmail.com";
        String address = "edward street";
        Customer customer = null;
        try {
            customer = customerService.createCustomer(name, PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(customer);
        assertEquals(name, customer.getUsername());
    }

    //CREATE NULL NAME CUSTOMER
    @Test
    public void testCreateCustomerNameNull() {
        createSaveOrders();

        String name = null;
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.createCustomer(name, PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer name cannot be empty!", error);
    }

    //CREATE NULL PASSWORD CUSTOMER
    @Test
    public void testCreateCustomerPasswordNull() {
        createSaveOrders();

        String password = null;
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.createCustomer(USERNAME_KEY, password, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer password cannot be empty!", error);
    }

    @Test
    public void testCreateCustomerEmailNull() {
        createSaveOrders();

        String email = null;
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, email, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer email cannot be empty!", error);
    }

    @Test
    public void testCreateCustomerAddressNull() {
        createSaveOrders();

        String address = null;
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer address cannot be empty!", error);
    }
    @Test
    public void testCreateCustomerNameEmpty() {
        createSaveOrders();

        String name = "";
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(name, PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer name cannot be empty!", error);
    }
    @Test
    public void testCreateCustomerPasswordEmpty() {
        createSaveOrders();

        String password = "";
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(USERNAME_KEY, password, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer password cannot be empty!", error);
    }
    @Test
    public void testCreateCustomerEmailEmpty() {
        createSaveOrders();

        String email = "";
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, email, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer email cannot be empty!", error);
    }
    @Test
    public void testCreateCustomerAddressEmpty() {
        createSaveOrders();

        String address = "";
        String error = null;
        Customer customer = null;
        try {
            customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        // check error
        assertEquals("Customer address cannot be empty!", error);
    }
    @Test
    public void testCreateCustomerDuplicate(){
        createSaveOrders();

        Customer customer1 = null;
        Customer customer2 = null;
        String error = null;

        when(customerRepository.existsById(anyString())).thenReturn(Objects.nonNull(customer1));

        try{
            customer1 = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
            when(customerRepository.existsById(anyString())).thenReturn(Objects.nonNull(customer1));
            customer2 = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNotNull(customer1);

        assertNull(customer2);
        assertEquals("An identical customer already exists.",error);
    }
    @Test
    public void testGetCustomerByUsername() {
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.getCustomer(USERNAME_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer);
        assertNull(error);

    }
    @Test
    public void testGetCustomerByInvalidUsername() {
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.getCustomer("wrong");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer);
        assertNull("Customer doesn't exist.", error);

    }





    public void createSaveOrders(){
        DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder("ship here", "Delivered", 1717, 180);
        deliveryOrderRepository.save(deliveryOrder);

        PickupOrder pickupOrder = pickupOrderService.createPickupOrder(1818, 190, "visa", "PickedUp");
        pickupOrderRepository.save(pickupOrder);
    }
}
