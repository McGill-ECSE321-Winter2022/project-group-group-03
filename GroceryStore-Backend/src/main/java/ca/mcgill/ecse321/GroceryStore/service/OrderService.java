package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.model.Order;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
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
    @Autowired
    DeliveryOrderService deliveryOrderService;
    @Autowired
    PickupOrderService pickupOrderService;

    @Transactional
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        for (PickupOrder pickupOrder : pickupOrderRepository.findAll()) orders.add(pickupOrder);
        for(DeliveryOrder deliveryOrder: deliveryOrderRepository.findAll()) orders.add(deliveryOrder);
        return orders;
    }


    @Transactional
    public void addPurchasedItemToOrder(int confirmationNumber, PurchasedItem purchasedItem){
        int count = 0;
        try{
            deliveryOrderService.addPurchasedItemToDeliveryOrder(confirmationNumber, purchasedItem);
        }catch (Exception e){
            count++;
        }
        try{
            pickupOrderService.addPurchasedItemToPickupOrder(confirmationNumber, purchasedItem);
        }catch (Exception e){
            count++;
        }
        if (count==2){
            throw new IllegalArgumentException("The input confirmation number does not correspond to an Order");
        }
    }

    @Transactional
    public String getOrderStatus(int confirmationNumber){
        String s = "";
        try{
            DeliveryOrder d = deliveryOrderService.getDeliveryOrder(confirmationNumber);
            s = d.getShippingStatusFullName();
        }catch(Exception e){

        }
        try{
            PickupOrder p = pickupOrderService.getPickupOrder(confirmationNumber);
            s = p.getPickupStatusFullName();
        }catch (Exception e){

        }
        return s;
    }



}
