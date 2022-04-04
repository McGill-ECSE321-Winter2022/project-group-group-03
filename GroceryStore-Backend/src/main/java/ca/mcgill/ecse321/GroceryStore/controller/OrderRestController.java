package ca.mcgill.ecse321.GroceryStore.controller;


import ca.mcgill.ecse321.GroceryStore.dto.OrderDTO;
import ca.mcgill.ecse321.GroceryStore.model.Commission;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
import ca.mcgill.ecse321.GroceryStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = {"/order", "/order/"})
    public List<OrderDTO> getHolidays() throws IllegalArgumentException {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Commission commission : orderService.getAllOrders()) orderDTOS.add(convertToDto(commission));
        return orderDTOS;
    }

    private OrderDTO convertToDto(Commission o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Order!");
        }
        String orderType;
        if (o instanceof DeliveryCommission)  orderType = "Delivery";
        else orderType = "Pickup";
        if(o.getCustomer().getUsername()== null)  return new OrderDTO(o.getConfirmationNumber(),o.getTotalCost(),o.getStore(),o.getPurchasedItem(), orderType,o.getEmployee().getUsername());
        else return new OrderDTO(o.getConfirmationNumber(),o.getTotalCost(),o.getStore(),o.getPurchasedItem(), orderType,o.getCustomer().getUsername());
    }
}
