package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.model.Order;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(String aUsername, String aPassword, String aEmail, String aAddress) {
        if(aUsername == null || aUsername == "") {
            throw new IllegalArgumentException("Username can't be empty");
        }
        if(aPassword == null || aPassword == ""){
            throw new IllegalArgumentException("Password can't be empty");
        }
        if(aEmail == null || aEmail == ""){
            throw new IllegalArgumentException("Email can't be empty");
        }
        if(aAddress == null || aAddress == ""){
            throw new IllegalArgumentException("Address can't be empty");
        }
        for (Customer customer : customerRepository.findAll()){
            if (customer.getUsername().equals(aUsername)) throw new IllegalArgumentException("An identical customer already exists.");
            if (customer.getEmail().equals(aEmail)) throw new IllegalArgumentException("An identical customer already exists.");
        }
        Customer newCustomer = new Customer();
        newCustomer.setAddress(aAddress);
        newCustomer.setEmail(aEmail);
        newCustomer.setPassword(aPassword);
        newCustomer.setUsername(aUsername);
        customerRepository.save(newCustomer);
        return newCustomer;
    }
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
    @Transactional
    public List<Customer> getAllCustomers(){

        List<Customer> customers = new ArrayList<>();
        for (Customer customer:customerRepository.findAll() ) {
            customers.add(customer);
        }

        return customers;
    }

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
    @Transactional
    public List<Order> getCustomerOrders(String aUsername){
        if(!customerRepository.existsById(aUsername))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        return customerRepository.findCustomerByUsername(aUsername).getOrder();
    }
    @Transactional
    public Customer setCustomerOrders(String aUsername, List<Order> orders){
        if(!customerRepository.existsById(aUsername))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        customerRepository.findCustomerByUsername(aUsername).setOrder(orders);
        return customerRepository.findCustomerByUsername(aUsername);
    }
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
