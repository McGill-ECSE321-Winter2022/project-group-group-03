package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.CustomerDTO;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.model.Order;
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
        return service.getAllCustomers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }
    @PostMapping(value = { "/customer/{username}", "/customer/{username}/" })
    public CustomerDTO createCustomer(@PathVariable("username") String username, @RequestParam String password, @RequestParam String email, @RequestParam String address) throws IllegalArgumentException {
        Customer customer = service.createCustomer(username, password, email, address);
        return convertToDto(customer);
    }
    @GetMapping(value = { "/customer/{username}", "/customer/{username}/" })
    public CustomerDTO getCustomerByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
        return convertToDto(service.getCustomer(username));
    }
    @GetMapping(value = { "/delivery_order/customer/{username}", "/delivery_order/customer/{username}/" })
    public List<OrderDTO> getDeliveryOrdersOfCustomer(@PathVariable("username") CustomerDTO eDto) {
        Customer c = convertToDomainObject(eDto);
        return createOrderDtosForCustomer(c);
    }

    private CustomerDTO convertToDto(Customer c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Customer!");
        }
        CustomerDTO customerDTO = new CustomerDTO(c.getUsername(),c.getPassword(),c.getEmail(),c.getAddress());
        return customerDTO;
    }
    private OrderDTO convertToDto(Order o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Order!");
        }
        OrderDTO orderDTO = new OrderDTO(c.getUsername(),c.getPassword(),c.getEmail(),c.getAddress());
        return CustomerDTO;
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
