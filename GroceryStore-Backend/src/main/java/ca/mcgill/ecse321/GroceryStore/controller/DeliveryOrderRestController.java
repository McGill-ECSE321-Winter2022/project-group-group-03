package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.DeliveryOrderDTO;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class DeliveryOrderRestController {
    @Autowired
    private DeliveryOrderService service;

    @PostMapping(value = { "/deliveryOrder", "/deliveryOrder/" })
    public DeliveryOrderDTO createDeliveryOrder(@RequestParam String shippingAddress, @RequestParam String shippingStatus,
                                               @RequestParam int confirmationNumber, @RequestParam int totalCost, @RequestParam boolean isOutOfTown) throws IllegalArgumentException {
        return convertToDto(service.createDeliveryOrder(shippingAddress,shippingStatus,confirmationNumber,totalCost, isOutOfTown));
    }

    @GetMapping(value = {"/deliveryOrder","/deliveryOrder/"})
    public List<DeliveryOrderDTO> getDeliveryOrders() throws IllegalArgumentException {
        return service.getAllDeliveryOrders().stream().map(this::convertToDto).collect(Collectors.toList());

    }
    @GetMapping(value = {"/deliveryOrder/{confirmationNumber}", "/deliveryOrder/{confirmationNumber}/"})
    public DeliveryOrderDTO getDeliveryOrder(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException{
        return convertToDto(service.getDeliveryOrder(confirmationNumber));
    }


    @PutMapping(value = {"/editDeliveryOrderShippingAddress/{confirmationNumber}/"})
    public DeliveryOrderDTO updateDeliveryOrderShippingAddress(@PathVariable("confirmationNumber") int confirmationNumber, @RequestParam String newAddress) throws IllegalArgumentException{
        return convertToDto(service.setShippingAddress(confirmationNumber, newAddress));
    }


    @DeleteMapping(value = {"/deliveryOrder/{confirmationNumber}", "/deliveryOrder/{confirmationNumber}/"})
    public void deleteDeliveryOrder(@PathVariable("confirmationNumber") int confirmationNumber) throws IllegalArgumentException {
        service.deleteDeliveryOrder(confirmationNumber);
    }

    private DeliveryOrderDTO convertToDto(DeliveryOrder aDeliveryOrder) {
        if (aDeliveryOrder == null) throw new IllegalArgumentException("There is no such Delivery Order!");
        return new DeliveryOrderDTO(aDeliveryOrder.getShippingAddress(),aDeliveryOrder.getShippingStatus().name(),aDeliveryOrder.getConfirmationNumber(), aDeliveryOrder.getTotalCost(), aDeliveryOrder.isOutOfTown());
    }
}
