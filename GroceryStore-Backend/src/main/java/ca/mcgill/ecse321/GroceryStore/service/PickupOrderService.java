package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PickupOrderService {
    @Autowired
    PickupOrderRepository pickupOrderRepository;

    @Transactional
    public PickupOrder createPickupOrder(int confirmationNumber, int totalCost, String paymentMethod, String pickupStatus){
        PickupOrder newPickupOrder = new PickupOrder();
        newPickupOrder.setConfirmationNumber(confirmationNumber);
        newPickupOrder.setTotalCost(totalCost);
        switch(paymentMethod) {
            case "Cash" -> newPickupOrder.setPaymentMethod(PickupOrder.PaymentMethod.Cash);
            case "CreditCard" -> newPickupOrder.setPaymentMethod(PickupOrder.PaymentMethod.CreditCard);
        }
        switch(pickupStatus){
            case "InCart" -> newPickupOrder.setPickupStatus(PickupOrder.PickupStatus.InCart);
            case "Ordered" -> newPickupOrder.setPickupStatus(PickupOrder.PickupStatus.Ordered);
            case "Prepared" -> newPickupOrder.setPickupStatus(PickupOrder.PickupStatus.Prepared);
            case "PickedUp" -> newPickupOrder.setPickupStatus(PickupOrder.PickupStatus.PickedUp);
        }
        pickupOrderRepository.save(newPickupOrder);
        return newPickupOrder;
    }
    @Transactional
    public PickupOrder getPickupOrder(int confirmationNumber){
        return pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
    }

    @Transactional
    public List<PickupOrder> getAllPickupOrders(){
        return toList(pickupOrderRepository.findAll());
    }
    @Transactional
    public void deletePickupOrder(int confirmationNumber){
        PickupOrder pickupOrder = pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
        if(pickupOrder ==  null) throw new IllegalArgumentException("The pickup order with ID: " + confirmationNumber + "does not exist.");
        else pickupOrderRepository.deleteById(confirmationNumber);
    }
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t: iterable){
            resultList.add(t);
        }
        return resultList;
    }
}
