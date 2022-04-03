package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.dao.OwnerRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

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



    @Transactional
    public Customer createCustomer(String aUsername, String aPassword, String aEmail, String aAddress) {
        if(aUsername == null || aUsername.equals("")) {
            throw new IllegalArgumentException("Username can't be empty");
        }
        if(aPassword == null || aPassword.equals("")){
            throw new IllegalArgumentException("Password can't be empty");
        }
        if(aEmail == null || aEmail.equals("")){
            throw new IllegalArgumentException("Email can't be empty");
        }
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
    public Customer loginCustomer(String aUsername, String aPassword){
        Customer customer = getCustomer(aUsername);
        if (customer.getPassword().equals(aPassword)) return customer;
        throw new IllegalArgumentException("Wrong password was given for username: " + aUsername);
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
    public Commission getCustomerOrder(String username){
        List<Commission> o = getCustomerOrders(username);
        for (Commission commission : o){
            String s = "";
            if (commission instanceof PickupCommission){
                s =  ((PickupCommission) commission).getPickupStatusFullName();
            }
            else if (commission instanceof DeliveryCommission){
                s= ((DeliveryCommission) commission).getShippingStatusFullName();
            }
            if (s.equals("InCart")) return commission;
        }
        throw new IllegalArgumentException("This Employee has no Orders in cart");
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
    public void addOrder(String username, Commission commission){
        Customer c = getCustomer(username);
        System.out.println("4");
        List<Commission> s = c.getOrder();
        System.out.println("5");
        s.add(commission);
        System.out.println("6");
        c.setOrder(s);
        System.out.println("7");
        customerRepository.save(c);
        System.out.println("8");
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
    public List<Commission> getCustomerOrders(String aUsername){
        if(!customerRepository.existsById(aUsername))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        return customerRepository.findCustomerByUsername(aUsername).getOrder();
    }
    @Transactional
    public Customer setCustomerOrders(String aUsername, List<Commission> commissions){
        if(!customerRepository.existsById(aUsername))
            throw new IllegalArgumentException("Customer does not currently exist in system.");
        customerRepository.findCustomerByUsername(aUsername).setOrder(commissions);
        return customerRepository.findCustomerByUsername(aUsername);
    }

}
