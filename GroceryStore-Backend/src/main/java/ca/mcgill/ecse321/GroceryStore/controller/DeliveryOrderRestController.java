package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.DeliveryOrderDTO;
import ca.mcgill.ecse321.GroceryStore.dto.PickupOrderDTO;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
import ca.mcgill.ecse321.GroceryStore.model.PickupCommission;
import ca.mcgill.ecse321.GroceryStore.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class DeliveryOrderRestController {
    @Autowired
    private DeliveryOrderService service;

    @PostMapping(value = { "/deliveryOrder", "/deliveryOrder/" })
    public DeliveryOrderDTO createDeliveryOrder(@RequestParam String username, @RequestParam String shippingAddress,
                                                @RequestParam String accountType, @RequestParam boolean isOutOfTown) throws IllegalArgumentException {
        return convertToDto(service.createDeliveryOrder(username, shippingAddress, accountType, isOutOfTown));
    }

    @GetMapping(value = {"/deliveryOrder","/deliveryOrder/"})
    public List<DeliveryOrderDTO> getDeliveryOrders() throws IllegalArgumentException {
        return service.getAllDeliveryOrders().stream().map(this::convertToDto).collect(Collectors.toList());

    }
    @GetMapping(value = {"/deliveryOrder/{confirmationNumber}", "/deliveryOrder/{confirmationNumber}/"})
    public DeliveryOrderDTO getDeliveryOrder(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException{
        return convertToDto(service.getDeliveryOrder(confirmationNumber));
    }

    @PutMapping(value = { "/convertToPickup", "/convertToPickup/" })
    public ResponseEntity<?> convertToPickup(@RequestParam String username, @RequestParam String paymentMethod,
                                                 @RequestParam String accountType) {
        try {
            return ResponseEntity.ok(convertToDto(service.convertDeliveryToPickup(username,paymentMethod,accountType)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editDeliveryOrderShippingAddress/{confirmationNumber}/"})
    public DeliveryOrderDTO updateDeliveryOrderShippingAddress(@PathVariable("confirmationNumber") int confirmationNumber, @RequestParam String newAddress) throws IllegalArgumentException{
        return convertToDto(service.setShippingAddress(confirmationNumber, newAddress));
    }

    @PutMapping(value = {"/payDelivery", "/payDelivery/"})
    public DeliveryOrderDTO payPickup(@RequestParam int confirmationNumber) throws IllegalArgumentException{
        return convertToDto(service.pay(confirmationNumber));
    }

    @PutMapping(value = {"/deliverDelivery", "/deliverDelivery/"})
    public DeliveryOrderDTO orderPickup(@RequestParam int confirmationNumber) throws IllegalArgumentException{
        return convertToDto(service.deliver(confirmationNumber));
    }

    @PutMapping(value = {"/editDeliveryOrderShippingStatus/{confirmationNumber}/"})
    public DeliveryOrderDTO setShippingStatus(@PathVariable("confirmationNumber") int confirmationNumber, @RequestParam String newShippingStatus) throws IllegalArgumentException{
        return convertToDto(service.setShippingStatus(confirmationNumber, newShippingStatus));
    }

    @PutMapping(value = {"/editDeliveryOrderTotalCost/{confirmationNumber}/"})
    public DeliveryOrderDTO updateTotalCost(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException{
        return convertToDto(service.updateTotalCost(confirmationNumber));
    }


    @DeleteMapping(value = {"/deliveryOrder/{confirmationNumber}", "/deliveryOrder/{confirmationNumber}/"})
    public void deleteDeliveryOrder(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException {
        service.deleteDeliveryOrder(confirmationNumber);
    }

    private DeliveryOrderDTO convertToDto(DeliveryCommission aDeliveryOrder) {
        if (aDeliveryOrder == null) throw new IllegalArgumentException("There is no such Delivery Order!");
        return new DeliveryOrderDTO(aDeliveryOrder.getShippingAddress(),aDeliveryOrder.getShippingStatus().name(),aDeliveryOrder.getConfirmationNumber(), aDeliveryOrder.getTotalCost(), aDeliveryOrder.isOutOfTown());
    }

    private PickupOrderDTO convertToDto(PickupCommission aPickupOrder) {
        if (aPickupOrder == null) throw new IllegalArgumentException("There is no such Pickup Order!");
        System.out.println("customer " + aPickupOrder.getCustomer());
        System.out.println("employee " + aPickupOrder.getEmployee());
        if (aPickupOrder.getCustomer() != null) return new PickupOrderDTO(aPickupOrder.getPaymentMethod().name(), aPickupOrder.getPickupStatus().name(), aPickupOrder.getConfirmationNumber(),aPickupOrder.getTotalCost(),aPickupOrder.getCustomer().getUsername());
        else if (aPickupOrder.getEmployee() != null) return new PickupOrderDTO(aPickupOrder.getPaymentMethod().name(), aPickupOrder.getPickupStatus().name(), aPickupOrder.getConfirmationNumber(),aPickupOrder.getTotalCost(),aPickupOrder.getEmployee().getUsername());
        else throw new IllegalArgumentException("There is no such Pickup Order!");
    }
}