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
    public DeliveryOrder createDeliveryOrder(String shippingAddress, String shippingStatus, int confirmationNumber, int totalCost){
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
        if (deliveryOrder == null) throw new IllegalArgumentException("The business hour with ID: " + confirmationNumber + " does not exist.");
        else deliveryOrderRepository.deleteById(confirmationNumber);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
