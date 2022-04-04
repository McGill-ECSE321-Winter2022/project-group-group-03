package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.EmployeeDTO;
import ca.mcgill.ecse321.GroceryStore.dto.OrderDTO;
import ca.mcgill.ecse321.GroceryStore.dto.WorkShiftDTO;
import ca.mcgill.ecse321.GroceryStore.model.Commission;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
import ca.mcgill.ecse321.GroceryStore.model.Employee;
import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
import ca.mcgill.ecse321.GroceryStore.service.EmployeeService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService service;

    @GetMapping(value = { "/all_employees", "/all_employees/" })
    public List<EmployeeDTO> getAllEmployees() {
        return service.getAllEmployees().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = {"/employee_order/{username}", "employee_order/{username}/"})
    private OrderDTO getEmployeeOrder(@PathVariable String username){
        return convertToDto(service.getEmployeeOrder(username));
    }

    @PostMapping(value = { "/employee", "/employee/" })
    public ResponseEntity<?> createEmployee(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String address) throws IllegalArgumentException {
        try {
            return ResponseEntity.ok(convertToDto(service.createEmployee(username, email,password,address)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = {"/employee_login", "/employee_login/"})
    public ResponseEntity<?> loginEmployee(@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
        try {
            return ResponseEntity.ok(convertToDto(service.loginEmployee(username, password)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = { "/employee", "/employee/" })
    public EmployeeDTO getEmployeeByUsername(@RequestParam String username) throws IllegalArgumentException {
        Employee e = service.getEmployee(username);
        return convertToDto(e);
    }

    @GetMapping(value = { "/workshift/employee", "/workshift/employee/" })
    public List<WorkShiftDTO> getWorkShiftsOfEmployee(@RequestParam String username) {
        return createWorkShiftDtosForEmployee(convertToDomainObject(getEmployeeByUsername(username)));
    }

    @GetMapping(value = { "/delivery_order/employee", "/delivery_order/employee/" })
    public List<OrderDTO> getOrdersOfEmployee(@RequestParam String username) {
        return createOrderDtosForEmployee(convertToDomainObject(getEmployeeByUsername(username)));
    }

    @PutMapping(value = { "/update_employee", "/update_employee/"})
    public EmployeeDTO updateEmployee(@RequestParam String username, @RequestParam String password, @RequestParam String address) throws IllegalArgumentException{
        return convertToDto(service.updateEmployee(username, password, address));
    }

    @PutMapping(value = { "/update_employee_password", "/update_employee_password/"})
    public EmployeeDTO updateEmployeePassword(@RequestParam String username, @RequestParam String password){
        return convertToDto(service.updateEmployeePassword(username, password));
    }

    @PutMapping(value = { "/update_employee_address", "/update_employee_address/"})
    public EmployeeDTO updateEmployeeAddress(@RequestParam String username,  @RequestParam String address){
        return convertToDto(service.updateEmployeeAddress(username, address));
    }

    @PutMapping(value = {"/fire_employee", "/fire_employee/"})
    public EmployeeDTO fireEmployee(@RequestParam String username){
        return convertToDto(service.fireEmployee(username));
    }

    @DeleteMapping(value = {"/employee", "/employee/"})
    public void deleteEmployee(@RequestParam String username){
        service.deleteEmployee(username);
    }

    private EmployeeDTO convertToDto(Employee e) {
        if (e == null) {
            throw new IllegalArgumentException("There is no such Employee!");
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(e.getUsername(),e.getPassword(),e.getEmail(),e.getAddress(), e.getWorkingStatusFullName());
        if (e.getOrder() == null) employeeDTO.setOrders(null);
        else {
            List<OrderDTO> orderDTOS = new ArrayList<>();
            for (Commission o : e.getOrder()) orderDTOS.add(convertToDto(o));
            employeeDTO.setOrders(orderDTOS);
        }
        if (e.getWorkShift()==null)employeeDTO.setWorkShifts(null);
        else {
            List<WorkShiftDTO> workShiftDTOS = new ArrayList<>();
            for (WorkShift w : e.getWorkShift()) workShiftDTOS.add(convertToDto(w));
            employeeDTO.setWorkShifts(workShiftDTOS);
        }
        return employeeDTO;
    }

    private OrderDTO convertToDto(Commission o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Order!");
        }
        String orderType = "";
        if (o instanceof DeliveryCommission)  orderType = "Delivery";
        else orderType = "Pickup";
        if(o.getCustomer().getUsername()== null)  return new OrderDTO(o.getConfirmationNumber(),o.getTotalCost(),o.getStore(),o.getPurchasedItem(), orderType,o.getEmployee().getUsername());
        else return new OrderDTO(o.getConfirmationNumber(),o.getTotalCost(),o.getStore(),o.getPurchasedItem(), orderType,o.getCustomer().getUsername());
    }

    private WorkShiftDTO convertToDto(WorkShift w) {
        if (w == null) {
            throw new IllegalArgumentException("There is no such Work Shift!");
        }
        return new WorkShiftDTO(w.getStartTime(), w.getEndTime(), w.getShiftID(),w.getDay().name());
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
        if (e == null) throw new IllegalArgumentException("There is no such Employee.");
        List<WorkShift> workShiftsForEmployee = service.getEmployeeWorkShifts(e.getUsername());
        List<WorkShiftDTO> workShifts = new ArrayList<>();
        for (WorkShift workShift : workShiftsForEmployee) {
            workShifts.add(convertToDto(workShift));
        }
        return workShifts;
    }

    private List<OrderDTO> createOrderDtosForEmployee(Employee e) {
        if (e == null) throw new IllegalArgumentException("There is no such Employee.");
        List<Commission> ordersForEmployee = service.getEmployeeOrders(e.getUsername());
        List<OrderDTO> commissions = new ArrayList<>();
        for (Commission commission : ordersForEmployee) {
            commissions.add(convertToDto(commission));
        }
        return commissions;
    }
}
