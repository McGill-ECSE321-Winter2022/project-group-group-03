package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.model.Order;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    PickupOrderRepository pickupOrderRepository;
    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Transactional
    public List<Order> getAllOrders() {
        String error = null;
        List<Order> orders = new ArrayList<>();
        for (PickupOrder pickupOrder : pickupOrderRepository.findAll()) orders.add(pickupOrder);
        for(DeliveryOrder deliveryOrder: deliveryOrderRepository.findAll()) orders.add(deliveryOrder);
        return orders;
    }
}
