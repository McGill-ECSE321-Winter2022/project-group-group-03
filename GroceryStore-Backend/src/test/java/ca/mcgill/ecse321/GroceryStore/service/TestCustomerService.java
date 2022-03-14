package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.model.*;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
    private static final String USERNAME_KEY = "edward";
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
        lenient().when(customerRepository.existsById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
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

        Customer customer = null;
        try {
            customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(customer);
    }

    //CREATE NULL NAME CUSTOMER
    @Test
    public void testCreateCustomerNameNull() {

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
        assertEquals("Username can't be empty", error);
    }

    //CREATE NULL PASSWORD CUSTOMER
    @Test
    public void testCreateCustomerPasswordNull() {

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
        assertEquals("Password can't be empty", error);
    }

    @Test
    public void testCreateCustomerEmailNull() {

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
        assertEquals("Email can't be empty", error);
    }

    @Test
    public void testCreateCustomerAddressNull() {

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
        assertEquals("Address can't be empty", error);
    }

    @Test
    public void testCreateCustomerNameEmpty() {

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
        assertEquals("Username can't be empty", error);
    }

    @Test
    public void testCreateCustomerPasswordEmpty() {

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
        assertEquals("Password can't be empty", error);
    }

    @Test
    public void testCreateCustomerEmailEmpty() {

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
        assertEquals("Email can't be empty", error);
    }

    @Test
    public void testCreateCustomerAddressEmpty() {

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
        assertEquals("Address can't be empty", error);
    }

    @Test
    public void testCreateCustomerDuplicateUsername() {

        String name = "Bob";
        Customer customer1 = null;
        Customer customer2 = null;
        String error = null;

        ArrayList<Customer> ls = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(ls);

        try {
            customer1 = customerService.createCustomer(name, PASSWORD, EMAIL, ADDRESS);
            ls.add(customer1);
            customer2 = customerService.createCustomer(name, PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer1);
        assertNull(customer2);
        assertEquals("An identical customer already exists.", error);
    }

    @Test
    public void testCreateCustomerDuplicateEmail() {

        Customer customer1 = null;
        Customer customer2 = null;
        String error = null;
        //test stub
        when(customerRepository.findAll()).thenReturn(Arrays.asList());

        try {
            customer1 = customerService.createCustomer("customer1", PASSWORD, EMAIL, ADDRESS);
            //test stub
            when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1));
            customer2 = customerService.createCustomer("customer2", PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer1);
        assertNull(customer2);
        assertEquals("An identical customer already exists.", error);
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
        Customer customer2 = null;

        try {
            customer = customerService.getCustomer("wrong");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("Customer doesn't exist", error);

    }

    @Test
    public void testGetCustomerByEmptyID() {
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.getCustomer("");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("The customer name can't be empty", error);

    }

    @Test
    public void testGetCustomerNullID() {
        Customer customer = null;
        String error = null;

        try {
            customer = customerService.createCustomer(null, PASSWORD, EMAIL, ADDRESS);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("Username can't be empty", error);
    }

    @Test
    public void testGetAllCustomers() {
        String error = null;
        List<Customer> customers = new ArrayList<>();
        Customer customer = null;
        String name = "Edward";
        when(customerRepository.findAll()).thenReturn(customers);

        try {
            customer = customerService.createCustomer(name, PASSWORD, EMAIL, ADDRESS);
            customers.add(customer);
            customers = customerService.getAllCustomers();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customers);
    }

    @Test
    public void getCustomerOrders() {

        Customer customer = null;
        DeliveryOrder deliveryOrder = null;
        PickupOrder pickupOrder = null;
        List<Order> orderList = null;
        String error = null;

        try {
            customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
            deliveryOrder = deliveryOrderService.createDeliveryOrder("my house", "Delivered", 69, 70);
            pickupOrder = pickupOrderService.createPickupOrder("Cash", "PickedUp", 70, 100);
            customer.setOrder(Arrays.asList(deliveryOrder, pickupOrder));
            DeliveryOrder finalDeliveryOrder = deliveryOrder;
            PickupOrder finalPickupOrder = pickupOrder;
            lenient().when(customerRepository.findCustomerByUsername(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
                if (invocation.getArgument(0).equals(USERNAME_KEY)) {
                    ArrayList<Customer> customers = new ArrayList<Customer>();
                    Customer customer1 = new Customer();
                    customer1.setUsername(USERNAME_KEY);
                    customer1.setPassword(PASSWORD);
                    customer1.setEmail(EMAIL);
                    customer1.setAddress(ADDRESS);
                    customer1.setOrder(Arrays.asList(finalDeliveryOrder, finalPickupOrder));
                    return customer1;
                } else {
                    return null;
                }
            });
            orderList = customerService.getCustomerOrders(USERNAME_KEY);
        } catch (Exception e) {
            System.out.println("crash");
            fail();
        }
        System.out.println(orderList == null);
        assertNotNull(orderList);
        assertEquals(orderList, Arrays.asList(deliveryOrder, pickupOrder));
    }

}
