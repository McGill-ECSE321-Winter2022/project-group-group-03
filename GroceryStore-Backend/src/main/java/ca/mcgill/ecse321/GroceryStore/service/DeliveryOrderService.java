package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryOrderService {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Transactional
    public DeliveryOrder createDeliveryOrder(String shippingAddress, String shippingStatus, Integer confirmationNumber, Integer totalCost){
        if(shippingAddress == null || shippingAddress == "" || shippingAddress == " ") {
            throw new IllegalArgumentException("Shipping address can't be empty.");
        }
        if(shippingStatus == null || shippingStatus == "" || shippingStatus == " "){
            throw new IllegalArgumentException("Shipping status can't be empty.");
        }
        if(confirmationNumber == null){
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if(confirmationNumber <= 0){
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(totalCost == null){
            throw new IllegalArgumentException("Total cost can't be empty.");
        }
        if(totalCost <= 0){
            throw new IllegalArgumentException("Total cost must be greater than 0.");
        }

        DeliveryOrder newDeliveryOrder = new DeliveryOrder();
        newDeliveryOrder.setShippingAddress(shippingAddress);
        newDeliveryOrder.setConfirmationNumber(confirmationNumber);
        newDeliveryOrder.setTotalCost(totalCost);
        switch(shippingStatus){
            case "InCart" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.InCart);
            case "Ordered" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Ordered);
            case "Prepared" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Prepared);
            case "Delivered" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Delivered);
        }
        deliveryOrderRepository.save(newDeliveryOrder);
        return newDeliveryOrder;
    }
    @Transactional
    public DeliveryOrder getDeliveryOrder(int confirmationNumber) {
        return deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
    }

    @Transactional
    public List<DeliveryOrder> getAllDeliveryOrders() {
        return toList(deliveryOrderRepository.findAll());
    }

    @Transactional
    public void deleteDeliveryOrder(int confirmationNumber) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
        if (deliveryOrder == null) throw new IllegalArgumentException("The deliveryOrder with confirmationNumber: " + confirmationNumber + " does not exist.");
        else deliveryOrderRepository.deleteById(confirmationNumber);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
