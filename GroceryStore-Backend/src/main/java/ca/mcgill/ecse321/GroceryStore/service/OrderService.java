package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
import ca.mcgill.ecse321.GroceryStore.model.Commission;
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
    public List<Commission> getAllOrders() {
        List<Commission> commissions = new ArrayList<>();
        for (PickupCommission pickupOrder : pickupOrderRepository.findAll()) commissions.add(pickupOrder);
        for(DeliveryCommission deliveryOrder: deliveryOrderRepository.findAll()) commissions.add(deliveryOrder);
        return commissions;
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
            DeliveryCommission d = deliveryOrderService.getDeliveryOrder(confirmationNumber);
            s = d.getShippingStatusFullName();
        }catch(Exception e){
        }
        try{
            PickupCommission p = pickupOrderService.getPickupOrder(confirmationNumber);
            s = p.getPickupStatusFullName();
        }catch (Exception e){
        }
        return s;
    }



}
