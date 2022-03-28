package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.CustomerDTO;
import ca.mcgill.ecse321.GroceryStore.dto.OrderDTO;
import ca.mcgill.ecse321.GroceryStore.dto.OwnerDTO;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.model.Order;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService service;

    @GetMapping(value = {"/customer", "/customer/"})
    public List<CustomerDTO> getAllCustomers(){
        return service.getAllCustomers().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @PostMapping(value = { "/customer", "/customer/" })
    public CustomerDTO createCustomer(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String address) throws IllegalArgumentException {
        Customer customer = service.createCustomer(username, password, email, address);
        return convertToDto(customer);
    }
    @GetMapping(value = { "/customer/{username}", "/customer/{username}/" })
    public CustomerDTO getCustomerByUsername(@PathVariable("username") String username, @RequestParam String password) throws IllegalArgumentException {
        return convertToDto(service.getCustomer(username));
    }
    @GetMapping(value = {"/customer_login", "/customer_login/"})
    public CustomerDTO loginOwner(@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
        Customer customer= service.loginCustomer(username, password);
        return convertToDto(customer);
    }
    @GetMapping(value = { "/delivery_order/customer/{username}", "/delivery_order/customer/{username}/" })
    public List<OrderDTO> getDeliveryOrdersOfCustomer(@PathVariable("username") String username) {
        List<Order> customerOrders = service.getCustomerOrders(username);
        return customerOrders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PutMapping(value = {"/editPassword/{username}"})
    public CustomerDTO updateCustomerPassword(@PathVariable("username") String username, @RequestParam String password) throws IllegalArgumentException{
        return convertToDto(service.setPassword(username, password));
    }
    @PutMapping(value = {"/editEmail/{username}"})
    public CustomerDTO updateCustomerEmail(@PathVariable("username") String username, @RequestParam String email) throws IllegalArgumentException{
        return convertToDto(service.setEmail(username, email));
    }
    @PutMapping(value = {"/editAddress/{username}"})
    public CustomerDTO updateCustomerAddress(@PathVariable("username") String username, @RequestParam String address) throws  IllegalArgumentException{
        return convertToDto(service.setAddress(username, address));
    }

    private CustomerDTO convertToDto(Customer c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        return new CustomerDTO(c.getUsername(),c.getPassword(),c.getEmail(),c.getAddress());
    }
    private OrderDTO convertToDto(Order o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Order!");
        }
        return new OrderDTO(o.getConfirmationNumber(),o.getTotalCost(),o.getStore(),o.getPurchasedItem());
    }
    private Customer convertToDomainObject(CustomerDTO eDto) {
        List<Customer> allCustomers = service.getAllCustomers();
        for (Customer customer : allCustomers) {
            if (customer.getUsername().equals(eDto.getUsername())) {
                return customer;
            }
        }
        return null;
    }
    private List<OrderDTO> createOrderDtosForCustomer(Customer c) {
        List<Order> ordersForCustomer = service.getCustomerOrders(c.getUsername());
        List<OrderDTO> orders = new ArrayList<>();
        for (Order order : ordersForCustomer) {
            orders.add(convertToDto(order));
        }
        return orders;
    }


}
