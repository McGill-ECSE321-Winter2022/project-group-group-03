package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.model.Employee;

import ca.mcgill.ecse321.GroceryStore.model.Order;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(String aEmail, String aUsername, String aPassword, String aAddress){
        Employee newEmployee = new Employee();
        newEmployee.setEmail(aEmail);
        newEmployee.setUsername(aUsername);
        newEmployee.setPassword(aPassword);
        newEmployee.setAddress(aAddress);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    @Transactional
    public Employee getEmployee(String aUsername) {
        if (aUsername != null && employeeRepository.findByUsername(aUsername) != null)
            return employeeRepository.findByUsername(aUsername);
        else throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");

    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return toList(employeeRepository.findAll());
    }

    @Transactional
    public List<WorkShift> getEmployeeWorkShifts(String aUsername){
        if (aUsername != null && employeeRepository.findByUsername(aUsername) != null)
            return employeeRepository.findByUsername(aUsername).getWorkShift();
        else throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");
    }

    @Transactional
    public List<Order> getEmployeeOrders(String aUsername){
        if (aUsername != null && employeeRepository.findByUsername(aUsername) != null)
            return employeeRepository.findByUsername(aUsername).getOrder();
        else throw new IllegalArgumentException("Invalid username: Either no Employee has this username or the string given was null");

    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
