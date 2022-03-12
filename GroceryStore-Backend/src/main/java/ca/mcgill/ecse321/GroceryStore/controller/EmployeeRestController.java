package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.EmployeeDTO;
import ca.mcgill.ecse321.GroceryStore.model.Employee;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import ca.mcgill.ecse321.GroceryStore.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService service;

    @GetMapping(value = { "/employee", "/employee/" })
    public List<EmployeeDTO> getAllEmployees() {
        return service.getAllEmployees().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = { "/employee/{username}", "/employee/{username}/" })
    public EmployeeDTO createEmployee(@PathVariable("username") String username, @RequestParam String password, @RequestParam String email, @RequestParam String address) throws IllegalArgumentException {
        Employee employee = service.createEmployee(username, password, email, address);
        return convertToDto(employee);
    }

    @GetMapping(value = { "/workshift/employee/{username}", "/workshift/employee/{nuserame}/" })
    public List<WorkShiftDTO> getEventsOfPerson(@PathVariable("username") EmployeeDTO eDto) {
        Employee e = convertToDomainObject(eDto);
        return createWorkShiftDtosForEmployee(e);
    }

    private EmployeeDTO convertToDto(Employee e) {
        if (e == null) {
            throw new IllegalArgumentException("There is no such Employee!");
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(e.getUsername(),e.getPassword(),e.getEmail(),e.getAddress());
        return employeeDTO;
    }

    private Employee convertToDomainObject(EmployeeDTO eDto) {
        List<Employee> allEmployees = service.getAllEmployees();
        for (Employee employee : allEmployees) {
            if (employee.getUsername().equals(eDto.getUsername())) {
                return employee;
            }
        }
        return null;
    }

    private List<WorkShiftDTO> createWorkShiftDtosForEmployee(Employee e) {
        List<WorkShift> workShiftsForEmployee = service.getEmployeeWorkShifts(e.getUsername());
        List<WorkShiftDTO> workShifts = new ArrayList<>();
        for (WorkShift workShift : workShiftsForEmployee) {
            workShifts.add(convertToDto(workShift));
        }
        return workShifts;
    }
}
