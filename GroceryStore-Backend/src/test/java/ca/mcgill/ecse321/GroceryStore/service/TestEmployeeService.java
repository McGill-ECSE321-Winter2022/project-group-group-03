package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    private String EMPLOYEE_USERNAME = "TEST_USERNAME";
    private String EMPLOYEE_EMAIL = "TEST_EMAIL@mail.ca";
    private String EMPLOYEE_PASSWORD = "TEST_PASSWORD";
    private String EMPLOYEE_ADDRESS = "TEST_ADDRESS";

    private String OWNER_USERNAME = "testOwner";
    private String OWNER_EMAIL = "owner@mail.ca";
    private String OWNER_PASSWORD = "testPass";

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WorkShiftRepository workShiftRepository;

    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;

    @Mock
    private PickupOrderRepository pickupOrderRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @InjectMocks
    private WorkShiftService workShiftService;

    @InjectMocks
    private DeliveryOrderService deliveryOrderService;

    @InjectMocks
    private PickupOrderService pickupOrderService;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private StoreService storeService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(customerRepository.existsById(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(OWNER_USERNAME)){
                return true;
            }
            else{
                return null;
            }
        });
        lenient().when(storeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Store s = storeService.createStore("ADDRESS", 5,5);
            return new ArrayList<Store>(Arrays.asList(s));
        });

        lenient().when(ownerRepository.existsById(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(OWNER_USERNAME)){
                return true;
            }
            else{
                return null;
            }
        });
        lenient().when(employeeRepository.existsById(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_USERNAME)){
                return true;
            }
            else{
                return null;
            }
        });
    }

    @Test
    public void testCreateEmployee() {

        Employee employee = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME, EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
        }catch(IllegalArgumentException e){
            fail();
        }
        assertNotNull(employee);
    }

    @Test
    public void testCreateEmployeeNullName() {

        String username = null;
        Employee employee = null;
        String error = null;

        try{
            employee= employeeService.createEmployee(username, EMPLOYEE_EMAIL, EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(employee);
        assertEquals("Username can't be empty.",error);
    }

    @Test
    public void testCreateEmployeeNullEmail() {

        String email = null;
        Employee employee = null;
        String error = null;

        try{
            employee= employeeService.createEmployee(EMPLOYEE_USERNAME, email, EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(employee);
        assertEquals("Email can't be empty.",error);
    }

    @Test
    public void testCreateEmployeeNullPassword() {

        String password = null;
        Employee employee = null;
        String error = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME, EMPLOYEE_EMAIL, password, EMPLOYEE_ADDRESS);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(employee);
        assertEquals("Password can't be empty.",error);
    }

    @Test
    public void testCreateEmployeeNullAddress() {

        String address = null;
        Employee employee = null;
        String error = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME, EMPLOYEE_EMAIL, EMPLOYEE_PASSWORD, address);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(employee);
        assertEquals("Address can't be empty.",error);
    }

    @Test
    public void testCreateEmployeeDuplicateUsername() {

        Employee employee1 = null;
        Employee employee2 = null;
        String error = null;

        when(employeeRepository.findAll()).thenReturn(Arrays.asList());

        try{
            employee1 = employeeService.createEmployee(EMPLOYEE_USERNAME, "email1@mail.com", EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS);
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));

            employee2 = employeeService.createEmployee(EMPLOYEE_USERNAME, "email2@mail.com", EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNotNull(employee1);
        assertNull(employee2);
        assertEquals("An identical employee already exists.",error);
    }

    @Test
    public void testCreateEmployeeDuplicateEmail() {

        Employee employee1 = null;
        Employee employee2 = null;
        String error = null;
        //test stub
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());

        try{
            employee1 = employeeService.createEmployee("boss1", EMPLOYEE_EMAIL, EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS);
            //test stub
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));
            employee2 = employeeService.createEmployee("boss2", EMPLOYEE_EMAIL, EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(employee1);
        assertNull(employee2);
        assertEquals("An identical employee already exists.",error);
    }

    @Test
    public void testGetEmployee() {

        Employee employee1 = null;
        Employee employee2 = null;
        String error = null;

        try{
            employee1 = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            //test stub
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));
            employee2 = employeeService.getEmployee(EMPLOYEE_USERNAME);
        }catch (Exception e){
            fail();
        }
        assertNotNull(employee2);
        assertEquals(employee1,employee2);
    }

    @Test
    public void testGetEmployeeDoesNotExist() {

        Employee employee1 = null;
        Employee employee2 = null;
        String error = null;

        try{
            employee1 = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            //test stub
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1));
            employee2 = employeeService.getEmployee("EMPLOYEE_USERNAME");
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(employee2);
        assertNotNull(error);
        assertEquals(error, "Invalid username: Either no Employee has this username or the string given was null");
    }

    @Test
    public void testGetEmployeeNullString() {

        Employee employee1 = null;
        Employee employee2 = null;
        String error = null;

        try{
            employee1 = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            employee2 = employeeService.getEmployee(null);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(employee2);
        assertNotNull(error);
        assertEquals(error, "Invalid username: Either no Employee has this username or the string given was null");
    }

    @Test
    public void testGetAll(){
        List<Employee> employeeArrayList = null;
        Employee employee1 = null;
        Employee employee2 = null;
        Employee employee3 = null;

        try{
            employee1 = employeeService.createEmployee("1", "a@ma.ca", EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS);
            employee2 = employeeService.createEmployee("2", "b@ma.ca", EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS);
            employee3 = employeeService.createEmployee("3", "c@ma.ca", EMPLOYEE_PASSWORD, EMPLOYEE_ADDRESS);
            //test stub
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1,employee2,employee3));
            employeeArrayList = employeeService.getAllEmployees();
        }catch (Exception e){
            fail();
        }
        assertNotNull(employeeArrayList);
        assertEquals(employeeArrayList, Arrays.asList(employee1,employee2,employee3));
    }

    @Test
    public void testUpdateEmployeePassword(){
        Employee employee = null;
        Employee employee1 = null;
        String newPassword= "newPassword";
        String error = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
            employee1 = employeeService.updateEmployeePassword(EMPLOYEE_USERNAME, newPassword);
        }catch (Exception e){
            fail();
        }

        assertNotNull(employee);
        assertNotNull(employee1);
        assertEquals(employee1.getPassword(), newPassword);
    }

    @Test
    public void testUpdateEmployeePasswordNull(){
        Employee employee = null;
        Employee employee1 = null;
        String newPassword= null;
        String error = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
            employee1 = employeeService.updateEmployeePassword(EMPLOYEE_USERNAME, newPassword);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals(error, "Password cannot be empty");
    }

    @Test
    public void testUpdateEmployeeAddress(){
        Employee employee = null;
        Employee employee1 = null;
        String newAddress = "new address";

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
            employee1 = employeeService.updateEmployeeAddress(EMPLOYEE_USERNAME, newAddress);
        }catch (Exception e){
            fail();
        }
        assertNotNull(employee);
        assertNotNull(employee1);
        assertEquals(employee1.getAddress(), newAddress);
    }

    @Test
    public void testUpdateEmployeeAddressNull(){
        Employee employee = null;
        Employee employee1 = null;
        String newAddress = null;
        String error = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
            employee1 = employeeService.updateEmployeeAddress(EMPLOYEE_USERNAME, newAddress);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals(error, "Address cannot be empty");
    }

}
