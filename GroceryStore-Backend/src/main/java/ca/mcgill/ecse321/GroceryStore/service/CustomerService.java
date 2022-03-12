package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(String aUsername, String aPassword, String aEmail, String aAddress){
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
        if (aUsername != null && customerRepository.findByUsername(aUsername) != null)
            return customerRepository.findByUsername(aUsername);
        else throw new IllegalArgumentException("Invalid username: Either no Customer has this username or the string given was null");
    }
    @Transactional
    public List<Customer> getAllCustomers(){
        return toList(customerRepository.findAll());
    }
    @Transactional
    public List<Order> getCustomerOrders(String aUsername){
        if (aUsername != null && customerRepository.findByUsername(aUsername) != null)
            return customerRepository.findByUsername(aUsername).getOrder();
        else throw new IllegalArgumentException("Invalid username: Either no Customer has this username or the string given was null");

    }
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
