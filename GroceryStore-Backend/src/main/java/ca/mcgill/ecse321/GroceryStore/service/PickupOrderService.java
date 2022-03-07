package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickupOrderService {

    @Autowired
    PickupOrderRepository pickupOrderRepository;


    @Transactional
    public PickupOrder createPickupOrder(String name, int confirmationNumber, int totalCost, PickupOrder.PaymentMethod paymentMethod, PickupOrder.PickupStatus status){
        PickupOrder pickupOrder = new PickupOrder();
        pickupOrder.setConfirmationNumber(confirmationNumber);
        pickupOrder.setTotalCost(totalCost);
        pickupOrder.setPaymentMethod(paymentMethod);
        pickupOrder.setPickupStatus(status);
        pickupOrderRepository.save(pickupOrder);

        return pickupOrder;
    }

    @Transactional
    public PickupOrder getPickupOrder(int confirmationNumber){
        return  pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
    }

    public List<PickupOrder> getAllPickupOrder(){
        List<PickupOrder> pickupOrders = new ArrayList<>();
        for (PickupOrder pickupOrder:pickupOrderRepository.findAll() ) {
            pickupOrders.add(pickupOrder);
        }
        return pickupOrders;
    }



}
