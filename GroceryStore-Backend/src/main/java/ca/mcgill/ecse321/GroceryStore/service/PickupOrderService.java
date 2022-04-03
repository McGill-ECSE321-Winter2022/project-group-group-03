package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.PickupCommission;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PickupOrderService {
    @Autowired
    PickupOrderRepository pickupOrderRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;


    @Autowired
    StoreService storeService;

    @Transactional
    public PickupCommission createPickupOrder(String username, String paymentMethod, Integer confirmationNumber){
        PickupCommission newPickupOrder = new PickupCommission();
        List<PickupCommission> pickupOrders = this.getAllPickupOrders();
        if(paymentMethod == null || paymentMethod.equals("") || paymentMethod.equals(" ")) {
            throw new IllegalArgumentException("Payment method can't be empty.");
        } else if(confirmationNumber == null){
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        else if(confirmationNumber <= 0){
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }

        else if (pickupOrders != null && pickupOrders.size() != 0) {
            for (PickupCommission p : pickupOrders) {
                if (p.getConfirmationNumber() == (confirmationNumber)) {
                    throw  new IllegalArgumentException("An identical pickup order with the same confirmation number already exists.");
                }
            }
        }
        newPickupOrder.setConfirmationNumber(confirmationNumber);
        newPickupOrder.setTotalCost(0);
        switch(paymentMethod) {
            case "Cash" -> newPickupOrder.setPaymentMethod(PickupCommission.PaymentMethod.Cash);
            case "CreditCard" -> newPickupOrder.setPaymentMethod(PickupCommission.PaymentMethod.CreditCard);
        }
        newPickupOrder.setPickupStatus(PickupCommission.PickupStatus.InCart);
        newPickupOrder.setStore(storeService.getStore());

        pickupOrderRepository.save(newPickupOrder);
        return newPickupOrder;
    }
    @Transactional
    public PickupCommission getPickupOrder(Integer confirmationNumber){
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
    public PickupCommission updatePickupStatus(Integer confirmationNumber, String pickupStatus){
        if (confirmationNumber == null){
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!pickupOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Pickup order doesn't exist.");
        }
        PickupCommission newPickupOrder = pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
        switch(pickupStatus){
            case "InCart" -> newPickupOrder.setPickupStatus(PickupCommission.PickupStatus.InCart);
            case "Ordered" -> newPickupOrder.setPickupStatus(PickupCommission.PickupStatus.Ordered);
            case "Prepared" -> newPickupOrder.setPickupStatus(PickupCommission.PickupStatus.Prepared);
            case "PickedUp" -> newPickupOrder.setPickupStatus(PickupCommission.PickupStatus.PickedUp);
            default -> throw new IllegalArgumentException("Not a valid pickup status");
        }
        if(newPickupOrder.getPickupStatus().name().equals("Ordered")) storeService.incrementActivePickup();
        if(newPickupOrder.getPickupStatus().name().equals("PickedUp")) storeService.decrementActivePickup();
        return pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
    }

    @Transactional
    public void addPurchasedItemToPickupOrder(Integer confirmationNumber, PurchasedItem purchasedItem){
        PickupCommission p = getPickupOrder(confirmationNumber);
        p.getPurchasedItem().add(purchasedItem);
    }

    @Transactional
    public PickupCommission updatePaymentMethod(Integer confirmationNumber, String paymentMethod){
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!pickupOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Pickup order doesn't exist.");
        }
        PickupCommission newPickupOrder = pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
        switch(paymentMethod) {
            case "Cash" -> newPickupOrder.setPaymentMethod(PickupCommission.PaymentMethod.Cash);
            case "CreditCard" -> newPickupOrder.setPaymentMethod(PickupCommission.PaymentMethod.CreditCard);
            default -> throw new IllegalArgumentException("Invalid payment method");
        }
        return pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
    }


    @Transactional
    public List<PickupCommission> getAllPickupOrders(){
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
    public PickupCommission updateTotalCost(Integer OrderId){
        if (OrderId == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (OrderId <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!pickupOrderRepository.existsById(OrderId)){
            throw new IllegalArgumentException("Pickup order doesn't exist.");
        }
        int totalCost = 0;
        for(PurchasedItem purchasedItem : pickupOrderRepository.findByConfirmationNumber(OrderId).getPurchasedItem()){
            totalCost += purchasedItem.getItemQuantity()*purchasedItem.getItem().getPrice();
        }
        PickupCommission pickupOrder = pickupOrderRepository.findByConfirmationNumber(OrderId);
        pickupOrder.setTotalCost(totalCost);
        return pickupOrder;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<>();
        for (T t: iterable){
            resultList.add(t);
        }
        return resultList;
    }
    private static int curID = 200000;

    @Transactional
    public PickupCommission createPickupOrder(String username, String paymentMethod, String accountType){
        PickupCommission newPickupOrder = new PickupCommission();
        if(paymentMethod == null ||  paymentMethod.equals("") || paymentMethod.equals(" ")) {
            throw new IllegalArgumentException("Payment method can't be empty.");
        }
        while(pickupOrderRepository.existsById(curID)){
            curID++;
        }
        newPickupOrder.setConfirmationNumber(curID);
        newPickupOrder.setTotalCost(0);
        switch(paymentMethod) {
            case "Cash" -> newPickupOrder.setPaymentMethod(PickupCommission.PaymentMethod.Cash);
            case "CreditCard" -> newPickupOrder.setPaymentMethod(PickupCommission.PaymentMethod.CreditCard);
        }
        newPickupOrder.setPickupStatus(PickupCommission.PickupStatus.InCart);
        newPickupOrder.setStore(storeService.getStore());
        if (accountType.equals("Customer")) customerService.addOrder(username, newPickupOrder);
        else if (accountType.equals("Employee")) employeeService.addOrder(username, newPickupOrder);
        pickupOrderRepository.save(newPickupOrder);
        return newPickupOrder;
    }
}
