package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.WorkShiftRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    private String EMPLOYEE_USERNAME = "TEST_USERNAME";
    private String EMPLOYEE_EMAIL = "TEST_EMAIL";
    private String EMPLOYEE_PASSWORD = "TEST_PASSWORD";
    private String EMPLOYEE_ADDRESS = "TEST_ADDRESS";

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WorkShiftRepository workShiftRepository;

    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;

    @Mock
    private PickupOrderRepository pickupOrderRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @InjectMocks
    private WorkShiftService workShiftService;

    @InjectMocks
    private DeliveryOrderService deliveryOrderService;

    @InjectMocks
    private PickupOrderService pickupOrderService;

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
    public void testGetWorkShift(){
        Employee employee = null;
        WorkShift workShift1 = null;
        WorkShift workShift2 = null;
        List<WorkShift> workShiftList = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            workShift1 = workShiftService.createWorkShift(Time.valueOf(LocalTime.of(10,10)), Time.valueOf(LocalTime.of(11,10)), "Monday");
            workShift2 = workShiftService.createWorkShift(Time.valueOf(LocalTime.of(11,10)), Time.valueOf(LocalTime.of(12,10)), "Tuesday");
            employee.setWorkShift(Arrays.asList(workShift1,workShift2));
            //test stub
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
            workShiftList = employeeService.getEmployeeWorkShifts(EMPLOYEE_USERNAME);
        } catch (Exception e){
            fail();
        }
        assertNotNull(workShiftList);
        assertEquals(workShiftList, Arrays.asList(workShift1, workShift2));
    }

    @Test
    public void getEmployeeOrders(){
        Employee employee = null;
        DeliveryOrder deliveryOrder = null;
        PickupOrder pickupOrder = null;
        List<Order> orderList = null;
        String error = null;

        try{
            employee = employeeService.createEmployee(EMPLOYEE_USERNAME,EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_ADDRESS);
            deliveryOrder = deliveryOrderService.createDeliveryOrder("my house", "Delivered", 69, 70, true);
            pickupOrder = pickupOrderService.createPickupOrder("Cash", "PickedUp", 420, 85);
            employee.setOrder(Arrays.asList(deliveryOrder, pickupOrder));
            when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
            orderList = employeeService.getEmployeeOrders(EMPLOYEE_USERNAME);
        }catch (Exception e){
            fail();
        }
        assertNotNull(orderList);
        assertEquals(orderList, Arrays.asList(deliveryOrder, pickupOrder));
    }
}
