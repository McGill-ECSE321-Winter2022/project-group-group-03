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
    public PickupOrder createPickupOrder(String paymentMethod, String pickupStatus, Integer confirmationNumber, Integer totalCost){
        PickupOrder newPickupOrder = new PickupOrder();
        List<PickupOrder> pickupOrders = this.getAllPickupOrders();
        if(paymentMethod == null || paymentMethod == "" || paymentMethod == " ") {
            throw new IllegalArgumentException("Payment method can't be empty.");
        }
        else if(pickupStatus == null || pickupStatus == "" || pickupStatus == " "){
            throw new IllegalArgumentException("Pickup status can't be empty.");
        }
        else if(confirmationNumber == null){
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        else if(confirmationNumber <= 0){
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        else if(totalCost == null){
            throw new IllegalArgumentException("Total cost can't be empty.");
        }
        else  if(totalCost <= 0){
            throw new IllegalArgumentException("Total cost must be greater than 0.");
        }
        else if (pickupOrders != null && pickupOrders.size() != 0) {
            for (PickupOrder p : pickupOrders) {
                if (p.getConfirmationNumber() == (confirmationNumber)) {
                    throw  new IllegalArgumentException("An identical pickup order with the same confirmation number already exists.");
                }
            }
        }
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
    public PickupOrder getPickupOrder(Integer confirmationNumber){
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!pickupOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Pickup order doesn't exist.");
        }
        return pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
    }

    @Transactional
    public List<PickupOrder> getAllPickupOrders(){
        return toList(pickupOrderRepository.findAll());
    }
    @Transactional
    public void deletePickupOrder(Integer confirmationNumber){
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!pickupOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Pickup order doesn't exist.");
        }

        pickupOrderRepository.deleteById(confirmationNumber);
    }
    @Transactional
    public void setConfirmationNumber(Integer current, Integer confirmationNumber){
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!pickupOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Pickup order doesn't exist.");
        }
        PickupOrder order = getPickupOrder(current);
        order.setConfirmationNumber(confirmationNumber);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t: iterable){
            resultList.add(t);
        }
        return resultList;
    }
}
