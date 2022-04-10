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

    /**
     * Method to create an employee with the given parameters
     * @param aUsername - the username with which to create the employee
     * @param aEmail - the email of the employee
     * @param aPassword - the password for the employee's account
     * @param aAddress - the address of the employee
     * If any of the parameters raise an error, the appropriate error message is thrown
     * @return - the created employee
     */
    @Transactional
    public Employee createEmployee(String aUsername, String aEmail, String aPassword, String aAddress){

        if (aUsername==null || aUsername.equals("")) throw new IllegalArgumentException("Username can't be empty.");
        if (aEmail==null || aEmail.equals("")) throw new IllegalArgumentException("Email can't be empty.");
        if (aPassword==null || aPassword.equals("")) throw new IllegalArgumentException("Password can't be empty.");
        if (aAddress==null || aAddress.equals("")) throw new IllegalArgumentException("Address can't be empty.");
        //this checks to see if the email string is a valid email
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
        newEmployee.setWorkingStatus(Employee.WorkingStatus.Hired); //employee's working status is automatically set to hired when created
        newEmployee.setOrder(new ArrayList<>()); //initially his order will be empty
        newEmployee.setWorkShift(new ArrayList<>()); //initially his work shifts will be empty
        storeService.addEmployee(newEmployee); //add the created employee to the store
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    /**
     * Method to get the order currently in cart for the given employee
     * @param username - the username of the employee whose order we wish to get
     * if the employee has no orders in the cart, the appropriate error message is shown
     * @return - the order that is currently in cart for the given employee
     */
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

    /**
     * Method to get the employee associated to the given username
     * @param aUsername - the username of the employee we wish to get
     * If no employee exists, the appropriate error message is thrown
     * @return - the employee with the given username
     */
    @Transactional
    public Employee getEmployee(String aUsername) {
        if (aUsername == null || aUsername.equals("")) throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
        for(Employee employee : employeeRepository.findAll()){
            if (employee.getUsername().equals(aUsername)) return employee;
        }
        throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
    }

    /**
     * This is the method to let an employee login (to be used for the login page in the frontend)
     * @param username - the username of the employee who wishes to log in
     * @param password - the password which will be used when logging in
     * This method calls the getEmployee described above. If the employee exists, the login is successful,
     * but only if the given password is the correct one associated with the username
     * @return - the employee who has logged in
     */
    @Transactional
    public Employee loginEmployee(String username, String password){
        Employee employee = getEmployee(username);
        if (employee.getPassword().equals(password)) return employee;
        throw new IllegalArgumentException("Wrong password was given for username: " + username);
    }

    /**
     * Method to get all the employees currently in the system
     * @return - a list containing all the employees in the system
     */
    @Transactional
    public List<Employee> getAllEmployees() {
        return toList(employeeRepository.findAll());
    }

    /**
     * Method to get all the work shift of the employees
     * @param aUsername - the username of the employee whose work shifts we wish to get
     * @return - the list of work shifts associated with the employee
     */
    @Transactional
    public List<WorkShift> getEmployeeWorkShifts(String aUsername){
        return getEmployee(aUsername).getWorkShift();
    }

    /**
     * Method to fire an employee
     * @param username - the username of the employee who we wish to fire
     * If the employee has already been fired, the appropriate error message will be thrown
     * @return - the employee who has now been fired
     */
    @Transactional
    public Employee fireEmployee(String username){
        Employee e = getEmployee(username);
        if (e.getWorkingStatusFullName().equals("Fired")) throw new IllegalArgumentException("Employee has already been fired");
        e.fireEmployee();
        return e;
    }

    /**
     * Method to hire an employee
     * @param username - the username of the employee who we wish to hire
     * If the employee has already been hired, the appropriate error message will be thrown
     * @return - the employee who has now been hired
     */
    @Transactional
    public Employee hireEmployee(String username){
        Employee e = getEmployee(username);
        if (e.getWorkingStatusFullName().equals("Hired")) throw new IllegalArgumentException("Employee has already been hired");
        e.setWorkingStatus(Employee.WorkingStatus.Hired);
        return e;
    }

    /**
     * Method to update an employee's credentials
     * @param username - username of the employee whose credentials we wish to update
     * @param password - a string representing the new password
     * @param address - a string representing the new address
     * If any of the parameter raise an error, the appropriate error message will be thrown
     * @return - the employee who now has his newly updated credentials
     */
    @Transactional
    public Employee updateEmployee(String username, String password, String address){
        if (password==null || password.equals("")) throw new IllegalArgumentException("Password cannot be empty");
        if (address==null || address.equals("")) throw new IllegalArgumentException("Address cannot be empty");

        Employee employee = getEmployee(username);
        employee.setPassword(password);
        employee.setAddress(address);
        return employee;
    }

    /**
     * Method that only updates an employee's password
     * @param username - the username of the employee whose password we wish to update
     * @param password - a string representing the new password
     * This method calls the update method described above, therefore any error messages will be thrown
     * based on the error handling described in that method
     * @return - the employee with his newly updated password
     */
    @Transactional
    public Employee updateEmployeePassword(String username, String password){
        Employee employee = getEmployee(username);
        return updateEmployee(username, password, employee.getAddress());
    }

    /**
     * Method that only updates an employee's address
     * @param username - the username of the employee whose address we wish to update
     * @param address - a string representing the new address
     * This method calls the update method described above, therefore any error messages will be thrown
     * based on the error handling described in that method
     * @return - the employee with his newly updated address
     */
    @Transactional
    public Employee updateEmployeeAddress(String username, String address){
        Employee employee = getEmployee(username);
        return updateEmployee(username, employee.getPassword(), address);
    }

    /**
     * Method to add a work shift to the list of work shifts associated with the employee
     * @param username - the username of the employee for whom we want to add a work shift
     * @param workShift - the work shift we wish to add
     */
    @Transactional
    public void addWorkShift(String username, WorkShift workShift){
        Employee e = getEmployee(username);
        e.getWorkShift().add(workShift);
    }

    /**
     * Method to add an order to the list of orders associated with the employee
     * @param username - the username of the employee for whom we want to add an order
     * @param commission - the order we wish to add
     */
    @Transactional
    public void addOrder(String username, Commission commission){
        Employee e = getEmployee(username);
        List<Commission> s = e.getOrder();
        s.add(commission);
        e.setOrder(s);
    }

    /**
     * Method to get all the orders of the employees
     * @param aUsername - the username of the employee whose orders we wish to get
     * @return - the list of orders associated with the employee
     */
    @Transactional
    public List<Commission> getEmployeeOrders(String aUsername){
        return getEmployee(aUsername).getOrder();
    }

    /**
     * A method to delete an employee from the system
     * @param aUsername - the username of the employee we wish to delete from the system
     * If the username parameter raises an error, the appropriate error message is thrown
     */
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

    /**
     * method to convert an iterable to a list. Used in the getAll method.
     * @param iterable - an iterable object
     * @return - the list from the iterable object
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
