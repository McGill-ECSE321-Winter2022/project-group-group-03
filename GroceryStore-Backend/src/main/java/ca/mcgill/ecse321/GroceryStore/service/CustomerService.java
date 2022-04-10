package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PickupOrderService pickupOrderService;
    @Autowired
    DeliveryOrderService deliveryOrderService;


    /**
     * Creates an object customer with all the credentials that has been provided
     * @param aUsername  the username of that customer
     * @param aPassword the password of that customer
     * @param aEmail the email of that customer
     * @param aAddress the housing address of that person
     * @return the object of that customer that was created with all the credentials
     */
    @Transactional
    public Customer createCustomer(String aUsername, String aPassword, String aEmail, String aAddress) {
        if(aUsername == null || aUsername.equals("")) {
            throw new IllegalArgumentException("Username can't be empty");
        }
        if(aPassword == null || aPassword.equals("")){
            throw new IllegalArgumentException("Password can't be empty");
        }

        if(aEmail == null || aEmail.equals("") || aEmail.equals(" ")) throw new IllegalArgumentException("Email can't be empty");
        if (aEmail.indexOf("@") <= 0 || aEmail.indexOf("@") != aEmail.lastIndexOf("@") ||
                aEmail.indexOf("@") >= aEmail.lastIndexOf(".") - 1 ||
                aEmail.lastIndexOf(".") >= aEmail.length() - 1)
            throw new IllegalArgumentException("Invalid email format");

        if(aAddress == null || aAddress.equals("")){
            throw new IllegalArgumentException("Address can't be empty");
        }
        for (Customer customer : customerRepository.findAll()){
            if (customer.getUsername().equals(aUsername)) throw new IllegalArgumentException("An identical customer already exists.");
            if (customer.getEmail().equals(aEmail)) throw new IllegalArgumentException("An identical customer already exists.");
        }
        for (Owner owner : ownerRepository.findAll()){
            if (owner.getUsername().equals(aUsername)) throw new IllegalArgumentException("An identical owner already exists.");
            if (owner.getEmail().equals(aEmail)) throw new IllegalArgumentException("An identical owner already exists.");
        }
        for (Employee employee : employeeRepository.findAll()){
            if (employee.getUsername().equals(aUsername)) throw new IllegalArgumentException("A customer already has this username");
            if (employee.getEmail().equals(aEmail)) throw new IllegalArgumentException("A customer already has this email");
        }
        Customer newCustomer = new Customer();
        newCustomer.setAddress(aAddress);
        newCustomer.setEmail(aEmail);
        newCustomer.setPassword(aPassword);
        newCustomer.setUsername(aUsername);
        newCustomer.setOrder(new ArrayList<>());
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    /**
     * Get the specific username that is associated with the customer
     * @param aUsername the username of the customer we would like to retrieve information from
     * @return the customer that is associated with the username
     */
    @Transactional
    public Customer getCustomer(String aUsername){
        if(aUsername == null || aUsername.equals("") || aUsername.equals(" ")){
            throw new IllegalArgumentException("The customer name can't be empty");
        }
        if(!customerRepository.existsById(aUsername)){
            throw new IllegalArgumentException("Customer doesn't exist");
        }
       return customerRepository.findCustomerByUsername(aUsername);
    }

    /**
     * The method authenticates by checking if the username and the password that was provided is valid or not
     * @param aUsername the username of the customer that is trying to log in
     * @param aPassword the password of the customer that is trying to log in
     * @return the customer object that has successfully logged in
     */
    @Transactional
    public Customer loginCustomer(String aUsername, String aPassword){
        Customer customer = getCustomer(aUsername);
        if (customer.getPassword().equals(aPassword)) return customer;
        throw new IllegalArgumentException("Wrong password was given for username: " + aUsername);
    }

    /**
     * Gets all list of customers that you can find in the repository
     * @return a list of customer object
     */
    @Transactional
    public List<Customer> getAllCustomers(){

        List<Customer> customers = new ArrayList<>();
        for (Customer customer:customerRepository.findAll() ) {
            customers.add(customer);
        }

        return customers;
    }

    /**
     * Gets all the order that is associated to the customer
     * @param username the username of the customer of whom we wished to get the orders of
     * @return a list of all the orders that is associated to that customer
     */
    @Transactional
    public Commission getCustomerOrder(String username){
        List<Commission> o = getCustomerOrders(username);
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
        throw new IllegalArgumentException("This Customer has no Orders in cart");
    }

    /**
     * Change the password of the customer that is associated to that username
     * @param current the username of the customer that wishes to change their password
     * @param password the new password that they want to change to
     * @return the customer object that has changed their password
     */
    @Transactional
    public Customer setPassword(String current, String password){
        if(password == null || password.equals("") || password.equals(" "))
            throw new IllegalArgumentException("Password can't be empty.");
        if(!customerRepository.existsById(current))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        Customer customer = getCustomer(current);
        customer.setPassword(password);
        return customer;
    }

    /**
     * Adds an order to the customer that is associated to that username
     * @param username the username of the customer who wishes to add an order
     * @param commission the order that he wishes to add
     */
    @Transactional
    public void addOrder(String username, Commission commission){
        Customer c = getCustomer(username);
        List<Commission> s = c.getOrder();
        s.add(commission);
        c.setOrder(s);
        customerRepository.save(c);
    }

    /**
     * Changes the email of the customer associated to that username
     * @param current the username that wishes to change their email address
     * @param email the new email that they wish to switch to
     * @return the customer object that has updated their email
     */
    @Transactional
    public Customer setEmail(String current, String email){
        if(email == null || email.equals("") || email.equals(" "))
            throw new IllegalArgumentException("Email can't be empty.");
        if (email.indexOf("@") <= 0 || email.indexOf("@") != email.lastIndexOf("@") ||
                email.indexOf("@") >= email.lastIndexOf(".") - 1 ||
                email.lastIndexOf(".") >= email.length() - 1)
            throw new IllegalArgumentException("Invalid email format");
        if(!customerRepository.existsById(current))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        Customer customer = getCustomer(current);
        customer.setEmail(email);
        return customer;
    }

    /**
     * Changes the address of the customer associated to that username
     * @param current the username that wishes to change their address
     * @param address the new address that they wish to switch to
     * @return the customer object that has updated their address
     */
    @Transactional
    public Customer setAddress(String current, String address){
        if(address == null || address.equals("") || address.equals(" "))
            throw new IllegalArgumentException("Address can't be empty.");
        if(!customerRepository.existsById(current))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        Customer customer = getCustomer(current);
        customer.setAddress(address);
        return customer;
    }

    /**
     * Gets all the orders of a specific customer that is associated to that username
     * @param aUsername the username of a customer
     * @return a list of orders of the customer
     */
    @Transactional
    public List<Commission> getCustomerOrders(String aUsername){
        if(!customerRepository.existsById(aUsername))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        return customerRepository.findCustomerByUsername(aUsername).getOrder();
    }

    /**
     * Sets a list of customer orders to the customer that is associated to it
     * @param aUsername the username of the customer
     * @param commissions the new orders that are associated to the customer
     * @return the new customer that has changed its list of orders
     */
    @Transactional
    public Customer setCustomerOrders(String aUsername, List<Commission> commissions){
        if(!customerRepository.existsById(aUsername))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        customerRepository.findCustomerByUsername(aUsername).setOrder(commissions);
        return customerRepository.findCustomerByUsername(aUsername);
    }

}
