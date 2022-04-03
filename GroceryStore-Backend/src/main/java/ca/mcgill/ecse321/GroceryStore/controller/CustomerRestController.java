package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.CustomerDTO;
import ca.mcgill.ecse321.GroceryStore.dto.OrderDTO;
import ca.mcgill.ecse321.GroceryStore.model.Commission;
import ca.mcgill.ecse321.GroceryStore.model.Customer;
import ca.mcgill.ecse321.GroceryStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService service;


    @GetMapping(value = {"/customer_order/{username}", "employee_order/{username}/"})
    private OrderDTO getCustomerOrder(@PathVariable String username){
        return convertToDto(service.getCustomerOrder(username));
    }


    @GetMapping(value = {"/customer", "/customer/"})
    public List<CustomerDTO> getAllCustomers(){
        return service.getAllCustomers().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @PostMapping(value = { "/customer", "/customer/" })
    public ResponseEntity<?> createCustomer(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String address) throws IllegalArgumentException {
        try {
            return ResponseEntity.ok(convertToDto(service.createCustomer(username, password,email,address)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    @GetMapping(value = { "/customer/{username}", "/customer/{username}/" })
    public CustomerDTO getCustomerByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
        return convertToDto(service.getCustomer(username));
    }
    @GetMapping(value = {"/customer_login", "/customer_login/"})
    public ResponseEntity<Object> loginCustomer(@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
        try {
            return ResponseEntity.ok(convertToDto(service.loginCustomer(username, password)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
    @GetMapping(value = { "/delivery_order/customer/{username}", "/delivery_order/customer/{username}/" })
    public List<OrderDTO> getDeliveryOrdersOfCustomer(@PathVariable("username") String username) {
        List<Commission> customerCommissions = service.getCustomerOrders(username);
        return customerCommissions.stream().map(this::convertToDto).collect(Collectors.toList());
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
    private OrderDTO convertToDto(Commission o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Order!");
        }
        return new OrderDTO(o.getConfirmationNumber(),o.getTotalCost(),o.getStore(),o.getPurchasedItem());
    }

}
