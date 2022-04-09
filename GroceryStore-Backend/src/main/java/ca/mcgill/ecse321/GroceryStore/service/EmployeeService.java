package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    StoreService storeService;
    @Autowired
    PickupOrderService pickupOrderService;
    @Autowired
    DeliveryOrderService deliveryOrderService;


    @Transactional
    public Employee createEmployee(String aUsername, String aEmail, String aPassword, String aAddress){

        if (aUsername==null || aUsername.equals("")) throw new IllegalArgumentException("Username can't be empty.");
        if (aEmail==null || aEmail.equals("")) throw new IllegalArgumentException("Email can't be empty.");
        if (aPassword==null || aPassword.equals("")) throw new IllegalArgumentException("Password can't be empty.");
        if (aAddress==null || aAddress.equals("")) throw new IllegalArgumentException("Address can't be empty.");

        if (aEmail.indexOf("@") <= 0 ||
                aEmail.indexOf("@") != aEmail.lastIndexOf("@") ||
                aEmail.indexOf("@") >= aEmail.lastIndexOf(".") - 1 ||
                aEmail.lastIndexOf(".") >= aEmail.length() - 1) throw new IllegalArgumentException("Invalid email");

        for (Employee employee : employeeRepository.findAll()){
            if (employee.getUsername().equals(aUsername)) throw new IllegalArgumentException("An identical employee already exists.");
            if (employee.getEmail().equals(aEmail)) throw new IllegalArgumentException("An identical employee already exists.");
        }
        for (Customer customer : customerRepository.findAll()){
            if (customer.getUsername().equals(aUsername)) throw new IllegalArgumentException("A customer already has this username");
            if (customer.getEmail().equals(aEmail)) throw new IllegalArgumentException("A customer already has this email");
        }
        for (Customer customer : customerRepository.findAll()){
            if (customer.getUsername().equals(aUsername)) throw new IllegalArgumentException("An owner already has this username");
            if (customer.getEmail().equals(aEmail)) throw new IllegalArgumentException("An owner already has this email");
        }

        Employee newEmployee = new Employee();
        newEmployee.setEmail(aEmail);
        newEmployee.setUsername(aUsername);
        newEmployee.setPassword(aPassword);
        newEmployee.setAddress(aAddress);
        newEmployee.setWorkingStatus(Employee.WorkingStatus.Hired);
        newEmployee.setOrder(new ArrayList<>());
        newEmployee.setWorkShift(new ArrayList<>());
        storeService.addEmployee(newEmployee);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    @Transactional

    public Commission getEmployeeOrder(String username){
        List<Commission> o = getEmployeeOrders(username);
        for (Commission commission : o){
            String s = "";
            if (commission instanceof PickupCommission){
                s =  ((PickupCommission) commission).getPickupStatusFullName();
                pickupOrderService.updateTotalCost(commission.getConfirmationNumber());
            }
            else if (commission instanceof DeliveryCommission){
                s= ((DeliveryCommission) commission).getShippingStatusFullName();
                deliveryOrderService.updateTotalCost(commission.getConfirmationNumber());
            }
            if (s.equals("InCart")) return commission;

        }
        throw new IllegalArgumentException("This Employee has no Orders in cart");
    }

    @Transactional
    public Employee getEmployee(String aUsername) {
        if (aUsername == null || aUsername.equals("")) throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
        for(Employee employee : employeeRepository.findAll()){
            if (employee.getUsername().equals(aUsername)) return employee;
        }
        throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
    }

    @Transactional
    public Employee loginEmployee(String username, String password){
        Employee employee = getEmployee(username);
        if (employee.getPassword().equals(password)) return employee;
        throw new IllegalArgumentException("Wrong password was given for username: " + username);
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return toList(employeeRepository.findAll());
    }

    @Transactional
    public List<WorkShift> getEmployeeWorkShifts(String aUsername){
        return getEmployee(aUsername).getWorkShift();
    }

    @Transactional
    public Employee fireEmployee(String username){
        Employee e = getEmployee(username);
        if (e.getWorkingStatusFullName().equals("Fired")) throw new IllegalArgumentException("Employee has already been fired");
        e.fireEmployee();
        return e;
    }

    @Transactional
    public Employee hireEmployee(String username){
        Employee e = getEmployee(username);
        if (e.getWorkingStatusFullName().equals("Hired")) throw new IllegalArgumentException("Employee has already been hired");
        e.setWorkingStatus(Employee.WorkingStatus.Hired);
        return e;
    }

    @Transactional
    public Employee updateEmployee(String username, String password, String address){
        if (password==null || password.equals("")) throw new IllegalArgumentException("Password cannot be empty");
        if (address==null || address.equals("")) throw new IllegalArgumentException("Address cannot be empty");

        Employee employee = getEmployee(username);
        employee.setPassword(password);
        employee.setAddress(address);
        return employee;
    }

    @Transactional
    public Employee updateEmployeePassword(String username, String password){
        Employee employee = getEmployee(username);
        return updateEmployee(username, password, employee.getAddress());
    }

    @Transactional
    public Employee updateEmployeeAddress(String username, String address){
        Employee employee = getEmployee(username);
        return updateEmployee(username, employee.getPassword(), address);
    }

    @Transactional
    public void addWorkShift(String username, WorkShift workShift){
        Employee e = getEmployee(username);
        e.getWorkShift().add(workShift);
    }

    @Transactional
    public void addOrder(String username, Commission commission){
        Employee e = getEmployee(username);
        List<Commission> s = e.getOrder();
        s.add(commission);
        e.setOrder(s);
    }

    @Transactional
    public List<Commission> getEmployeeOrders(String aUsername){
        return getEmployee(aUsername).getOrder();
    }

    @Transactional
    public void deleteEmployee(String aUsername){
        if (aUsername == null || aUsername.equals("")) throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
        for(Employee employee : employeeRepository.findAll()){
            if (employee.getUsername().equals(aUsername)) {
                employeeRepository.deleteById(aUsername);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
