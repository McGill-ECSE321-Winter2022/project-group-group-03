package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.*;
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

    private String EMPLOYEE_USERNAME = "TEST_USERNAME";
    private String EMPLOYEE_EMAIL = "TEST_EMAIL@mail.ca";
    private String EMPLOYEE_PASSWORD = "TEST_PASSWORD";
    private String EMPLOYEE_ADDRESS = "TEST_ADDRESS";

    private String OWNER_USERNAME = "testOwner";
    private String OWNER_EMAIL = "owner@mail.ca";
    private String OWNER_PASSWORD = "testPass";

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private PickupOrderRepository pickupOrderRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private PurchasedItemRepository purchasedItemRepository;


    @InjectMocks
    private CustomerService customerService;
    @InjectMocks
    private DeliveryOrderService deliveryOrderService;
    @InjectMocks
    private PickupOrderService pickupOrderService;
    @InjectMocks
    private EmployeeService employeeService;
    @InjectMocks
    private OwnerService ownerService;
    @InjectMocks
    private ItemService itemService;
    @InjectMocks
    private PurchasedItemService purchasedItemService;

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
        lenient().when(employeeRepository.existsById(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
           if (invocation.getArgument(0).equals(EMPLOYEE_USERNAME)){
               ArrayList<Employee> employees = new ArrayList<Employee>();
               Employee employee = new Employee();
               employee.setUsername(EMPLOYEE_USERNAME);
               employee.setPassword(EMPLOYEE_PASSWORD);
               employee.setEmail(EMPLOYEE_EMAIL);
               employee.setAddress(EMPLOYEE_ADDRESS);
               employees.add(employee);
               return employee;
           }
           else{
               return null;
           }
        });
        lenient().when(ownerRepository.existsById(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(OWNER_USERNAME)){
                ArrayList<Owner> owners = new ArrayList<Owner>();
                Owner owner = new Owner();
                owner.setUsername(EMPLOYEE_USERNAME);
                owner.setPassword(EMPLOYEE_PASSWORD);
                owner.setEmail(EMPLOYEE_EMAIL);
                owners.add(owner);
                return owner;
            }
            else{
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
    public void testLoginCustomerWrongPassword(){
        Customer customer = null;
        String error = null;

        try{
            customer = customerService.loginCustomer(USERNAME_KEY, "badpassword");

        } catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(customer);
        assertEquals("Wrong password was given for username: " + USERNAME_KEY, error);
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
            deliveryOrder = deliveryOrderService.createDeliveryOrder("my house", "Delivered", 69,  true);
            pickupOrder = pickupOrderService.createPickupOrder("Cash", "PickedUp", 70);
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


    @Test
    public void testUpdateCustomerNullPassword() {
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String password = null;
        String error = null;

        try {
            customerService.setPassword(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getPassword());
        assertEquals("Password can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmptyPassword(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String password = "";
        String error = null;

        try {
            customerService.setPassword(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getPassword());
        assertEquals("Password can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmptySpacePassword(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String password = " ";
        String error = null;

        try {
            customerService.setPassword(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getPassword());
        assertEquals("Password can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerPasswordWRONGID(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String password = "ILOVEECSE321!";
        String error = null;

        try {
            customerService.setPassword("ALSDFLAKSJFLAKSJDA", password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getPassword());
        assertEquals("Customer does not currently exist in system.", error);
    }

    @Test
    public void testUpdateCustomerNullEmail() {
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = null;
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Email can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmptyEmail(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Email can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmptySpaceEmail(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = " ";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Email can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmailAtSymbolFirst(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "@223";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testUpdateCustomerEmailAtSymbolLast(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "223@";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testUpdateCustomerEmailAtSymbolAfterDot(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "2.23@";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testUpdateCustomerEmailDotSymbolLast(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "22@3.";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testUpdateCustomerEmailNoDotSymbol(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "223@gmailcom";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testUpdateCustomerEmailNoAtSymbol(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "223gmail.com";
        String error = null;

        try {
            customerService.setEmail(USERNAME_KEY, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testUpdateCustomerEmailWRONGID(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String email = "223@gmail.com";
        String error = null;

        try {
            customerService.setEmail("AFKLJSADFJLK", email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Customer does not currently exist in system.", error);
    }



    @Test
    public void testUpdateCustomerNullAddress(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String address = null;
        String error = null;

        try {
            customerService.setAddress(USERNAME_KEY, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmptyAddress(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String address = "";
        String error = null;

        try {
            customerService.setAddress(USERNAME_KEY, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerEmptySpaceAddress(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String address = " ";
        String error = null;

        try {
            customerService.setAddress(USERNAME_KEY, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Address can't be empty.", error);
    }
    @Test
    public void testUpdateCustomerAddressWRONGID(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String address = "3064 rue edmond rostand";
        String error = null;

        try {
            customerService.setAddress("ADASDFHJKAS", address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(customer.getEmail());
        assertEquals("Customer does not currently exist in system.", error);
    }

    @Test
    public void testGetOrderWRONGID(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String error = null;

        try {
            customer.setOrder(customerService.getCustomerOrders("ASKALFA"));
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer.getOrder());
        assertEquals("Customer does not currently exist in system.", error);
    }

    @Test
    public void testSetOrderWRONGID(){
        assertEquals(0, customerService.getAllCustomers().size());
        Customer customer = null;
        customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        String error = null;
        List<Order> order = new ArrayList<>();
        try {
            customerService.setCustomerOrders("ASKALFA", order);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(customer.getOrder());
        assertEquals("Customer does not currently exist in system.", error);
    }

    @Test
    public void testCustomerWantsToUpdateStockDeliveryOrderOverflow(){
        String error = null;
        //Initialization
        Customer customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        Item item = itemService.createItem("Cheeze", true, 10, "Cheezy", 10 );
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 5);
        DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder("3064 edmond rostand", "InCart", 123,  false);
        List<Order> orders = new ArrayList<>();
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        purchasedItem.setItem(item);
        purchasedItems.add(purchasedItem);
        deliveryOrder.setPurchasedItem(purchasedItems);
        orders.add(deliveryOrder);
        customer.setOrder(orders);
        //Test
        try{
            when(customerRepository.findCustomerByUsername(anyString())).thenReturn(customer);
            //Test Customer -> Order
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getConfirmationNumber(), deliveryOrder.getConfirmationNumber());
            //Test Customer -> Order -> Purchased Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getPurchasedItemID(), purchasedItem.getPurchasedItemID());
            //Test Customer -> Order -> Purchased Item -> Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem().getName(), "Cheeze");
            //Adjust purchased item quantity from customer
            Item tempItem = customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem();
            when(itemRepository.findByName(anyString())).thenReturn(item);
            itemService.updateItemTotalPurchased(tempItem.getName(), 400);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "There are only " + item.getStock() + " " + item.getName()+ "s available currently.");
    }



    @Test
    public void testCustomerWantsToUpdateStockDeliveryOrderNegative(){
        String error = null;
        //Initialization
        Customer customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        Item item = itemService.createItem("Cheeze", true, 10, "Cheezy", 10 );
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 5);
        DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder("3064 edmond rostand", "InCart", 123,  false);
        List<Order> orders = new ArrayList<>();
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        purchasedItem.setItem(item);
        purchasedItems.add(purchasedItem);
        deliveryOrder.setPurchasedItem(purchasedItems);
        orders.add(deliveryOrder);
        customer.setOrder(orders);
        //Test
        try{
            when(customerRepository.findCustomerByUsername(anyString())).thenReturn(customer);
            //Test Customer -> Order
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getConfirmationNumber(), deliveryOrder.getConfirmationNumber());
            //Test Customer -> Order -> Purchased Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getPurchasedItemID(), purchasedItem.getPurchasedItemID());
            //Test Customer -> Order -> Purchased Item -> Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem().getName(), "Cheeze");
            //Adjust purchased item quantity from customer
            Item tempItem = customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem();
            when(itemRepository.findByName(anyString())).thenReturn(item);
            itemService.updateItemTotalPurchased(tempItem.getName(), -2);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Total Purchased can't be negative.");
    }

    @Test
    public void testCustomerWantsToUpdateStockDeliveryOrderZero(){
        String error = null;
        //Initialization
        Customer customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        Item item = itemService.createItem("Cheeze", true, 10, "Cheezy", 10 );
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 5);
        DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder("3064 edmond rostand", "InCart", 123,  false);
        List<Order> orders = new ArrayList<>();
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        purchasedItem.setItem(item);
        purchasedItems.add(purchasedItem);
        deliveryOrder.setPurchasedItem(purchasedItems);
        orders.add(deliveryOrder);
        customer.setOrder(orders);
        //Test
        try{
            when(customerRepository.findCustomerByUsername(anyString())).thenReturn(customer);
            //Test Customer -> Order
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getConfirmationNumber(), deliveryOrder.getConfirmationNumber());
            //Test Customer -> Order -> Purchased Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getPurchasedItemID(), purchasedItem.getPurchasedItemID());
            //Test Customer -> Order -> Purchased Item -> Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem().getName(), "Cheeze");
            //Adjust purchased item quantity from customer
            Item tempItem = customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem();
            when(itemRepository.findByName(anyString())).thenReturn(item);
            itemService.updateItemTotalPurchased(tempItem.getName(), 0);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "Total Purchased can't be 0.");
    }

    @Test
    public void testCustomerWantsToUpdatePurchasedQuantityButStockNull(){
        String error = null;
        //Initialization
        Customer customer = customerService.createCustomer(USERNAME_KEY, PASSWORD, EMAIL, ADDRESS);
        Item item = itemService.createItem("Cheeze", true, 10, "Cheezy", 2 );
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 2);
        DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder("3064 edmond rostand", "InCart", 123,  false);
        List<Order> orders = new ArrayList<>();
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        purchasedItem.setItem(item);
        purchasedItems.add(purchasedItem);
        deliveryOrder.setPurchasedItem(purchasedItems);
        orders.add(deliveryOrder);
        customer.setOrder(orders);
        //Test
        try{
            when(customerRepository.findCustomerByUsername(anyString())).thenReturn(customer);
            //Test Customer -> Order
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getConfirmationNumber(), deliveryOrder.getConfirmationNumber());
            //Test Customer -> Order -> Purchased Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getPurchasedItemID(), purchasedItem.getPurchasedItemID());
            //Test Customer -> Order -> Purchased Item -> Item
            assertEquals(customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem().getName(), "Cheeze");
            //Adjust purchased item quantity from customer
            Item tempItem = customerRepository.findCustomerByUsername(USERNAME_KEY).getOrder().get(0).getPurchasedItem().get(0).getItem();
            when(itemRepository.findByName(anyString())).thenReturn(item);
            //Empty item stock
            itemService.updateItemTotalPurchased(tempItem.getName(), 2);
            //Add more when stock is down
            itemService.updateItemTotalPurchased(tempItem.getName(), 5);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "There are no " + item.getName() + "s available currently.");
    }

    @Test
    public void testCustomerWantsToUpdatePurchasedQuantityButStockItemIDWrong(){
        String error = null;
        try{
            itemService.updateItemTotalPurchased("Ball", 2);
        }
        catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals(error, "The Item with name: " + "Ball" + " was not found in the database.");
    }

}
