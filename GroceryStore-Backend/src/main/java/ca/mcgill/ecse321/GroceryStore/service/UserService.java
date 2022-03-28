package ca.mcgill.ecse321.GroceryStore.service;


import ca.mcgill.ecse321.GroceryStore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;

    @Transactional
    public void addOrder(String username, Order o) {
        if (o==null) throw new IllegalArgumentException("An order parameter is required");
        int count = 0;
        try {
            customerService.addOrder(username, o);
        } catch (Exception e) {
            count++;
        }
        try {
            employeeService.addOrder(username, o);
        } catch (Exception e) {
            count++;
        }
        if (count == 2) throw new IllegalArgumentException("The input username does not correspond to an account");
    }
}
