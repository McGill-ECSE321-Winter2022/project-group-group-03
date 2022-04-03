package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.PickupOrderDTO;
import ca.mcgill.ecse321.GroceryStore.model.Employee;
import ca.mcgill.ecse321.GroceryStore.model.PickupCommission;
import ca.mcgill.ecse321.GroceryStore.service.CustomerService;
import ca.mcgill.ecse321.GroceryStore.service.EmployeeService;
import ca.mcgill.ecse321.GroceryStore.service.PickupOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class PickupOrderRestController {
    @Autowired
    private PickupOrderService service;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = { "/pickupOrder", "/pickupOrder/" })
    public PickupOrderDTO createPickupOrder(@RequestParam String username, @RequestParam String paymentMethod,
                                                @RequestParam String accountType) throws IllegalArgumentException {
        System.out.println("1");
        PickupCommission p = service.createPickupOrder(username, paymentMethod, accountType);
        System.out.println("2");
        System.out.println(customerService.getCustomerOrders(username));
        System.out.println("3");
        if (accountType.equals("Customer")) customerService.addOrder(username ,p);
        System.out.println("4");
        return convertToDto(p);
    }

    @GetMapping(value = {"/pickupOrder","/pickupOrder/"})
    public List<PickupOrderDTO> getPickupOrder() throws IllegalArgumentException {
        return service.getAllPickupOrders().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping(value = {"/pickupOrder/{confirmationNumber}", "/pickupOrder/{confirmationNumber}/"})
    public PickupOrderDTO getPickupOrder(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException {
        return convertToDto(service.getPickupOrder(confirmationNumber));
    }
    @PutMapping(value = {"/editPickupOrderStatus/{confirmationNumber}/"})
    public PickupOrderDTO updatePickupStatus(@PathVariable("confirmationNumber") int confirmationNumber, @RequestParam String newPickupStatus) throws IllegalArgumentException {
        return convertToDto(service.updatePickupStatus(confirmationNumber, newPickupStatus));
    }
    @PutMapping(value = {"/editPickupOrderPaymentMethod/{confirmationNumber}/"})
    public PickupOrderDTO updatePaymentMethod(@PathVariable("confirmationNumber") int confirmationNumber, @RequestParam String newPaymentMethod) throws IllegalArgumentException {
        return convertToDto(service.updatePaymentMethod(confirmationNumber, newPaymentMethod));
    }

    @PutMapping(value = {"/editPickupOrderTotalCost/{confirmationNumber}/"})
    public PickupOrderDTO updateTotalCost(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException {
        return convertToDto(service.updateTotalCost(confirmationNumber));
    }


    @DeleteMapping(value = {"/pickupOrder/{confirmationNumber}", "/pickupOrder/{confirmationNumber}/"})
    public void deletePickupOrder(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException {
        service.deletePickupOrder(confirmationNumber);
    }
    private PickupOrderDTO convertToDto(PickupCommission aPickupOrder) {
        if (aPickupOrder == null) throw new IllegalArgumentException("There is no such Pickup Order!");
        return new PickupOrderDTO(aPickupOrder.getPaymentMethod().name(), aPickupOrder.getPickupStatus().name(), aPickupOrder.getConfirmationNumber(),aPickupOrder.getTotalCost());
    }


}
