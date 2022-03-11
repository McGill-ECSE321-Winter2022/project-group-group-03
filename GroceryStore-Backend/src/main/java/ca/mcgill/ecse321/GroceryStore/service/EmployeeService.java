package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.model.Employee;

import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        Employee employee = employeeRepository.findByUsername(aUsername);
        return employee;
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        return toList(employeeRepository.findAll());
    }

    public List<WorkShift> getEmployeeWorkShifts(String aUsername){
        return employeeRepository.findByUsername(aUsername).getWorkShift();
    }


    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
