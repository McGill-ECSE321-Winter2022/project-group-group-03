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

    /**
     * Gets all the orders from both pick up or deliver order
     * @return a list of all the orders that is in the repos
     */
    @Transactional
    public List<Commission> getAllOrders() {
        List<Commission> commissions = new ArrayList<>();
        for (PickupCommission pickupOrder : pickupOrderRepository.findAll()) commissions.add(pickupOrder);
        for(DeliveryCommission deliveryOrder: deliveryOrderRepository.findAll()) commissions.add(deliveryOrder);
        return commissions;
    }

    /**
     * Adds the item that has been purchased to the corresponding order type
     * @param confirmationNumber the confirmation number of the order that we are looking for
     * @param purchasedItem the purchased item that we are adding to the corresponding order
     */
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

    /**
     * Gets the status of the order that is associated to that confirmation number
     * @param confirmationNumber the confirmation number of the order that we are trying to get
     * @return the status of the order
     */
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
