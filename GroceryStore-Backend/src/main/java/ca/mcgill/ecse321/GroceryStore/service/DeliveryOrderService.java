package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryOrderService {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    //TODO: uncomment this code when create function is updated to add to employee
    //@Autowired
    //EmployeeService employeeService;

    @Autowired
    StoreService storeService;

    @Transactional
    public DeliveryOrder createDeliveryOrder(String shippingAddress, String shippingStatus, Integer confirmationNumber, boolean isOutOfTown){
        DeliveryOrder newDeliveryOrder = new DeliveryOrder();
        List<DeliveryOrder> deliveryOrders = this.getAllDeliveryOrders();

        if(shippingAddress == null || shippingAddress.equals("") || shippingAddress.equals(" ")) {
            throw new IllegalArgumentException("Shipping address can't be empty.");
        }
        else if(shippingStatus == null || shippingStatus.equals("") || shippingStatus.equals(" ")){
            throw new IllegalArgumentException("Shipping status can't be empty.");
        }
        else if(confirmationNumber == null){
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        else if(confirmationNumber <= 0){
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }

        else if (deliveryOrders != null && deliveryOrders.size() != 0) {
            for (DeliveryOrder d : deliveryOrders) {
                if (d.getConfirmationNumber() == (confirmationNumber)) {
                    throw  new IllegalArgumentException("An identical delivery order with the same confirmation number already exists.");
                }
            }
        }
        newDeliveryOrder.setShippingAddress(shippingAddress);
        newDeliveryOrder.setConfirmationNumber(confirmationNumber);
        newDeliveryOrder.setIsOutOfTown(isOutOfTown);
        newDeliveryOrder.setTotalCost(0);
        switch(shippingStatus){
            case "InCart" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.InCart);
            case "Ordered" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Ordered);
            case "Prepared" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Prepared);
            case "Delivered" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Delivered);
            default -> throw new IllegalArgumentException("Invalid shipping status");
        }
        newDeliveryOrder.setStore(storeService.getStore());
        //TODO: to be uncommented later
        //employeeService.addOrder(username, newDeliveryOrder);
        deliveryOrderRepository.save(newDeliveryOrder);
        return newDeliveryOrder;
    }
    @Transactional
    public DeliveryOrder getDeliveryOrder(Integer confirmationNumber) {
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!deliveryOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }
        return deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
    }

    @Transactional
    public List<DeliveryOrder> getAllDeliveryOrders() {
        List<DeliveryOrder> deliveryOrders = new ArrayList<>();
        for (DeliveryOrder deliveryOrder:deliveryOrderRepository.findAll() ) {
            deliveryOrders.add(deliveryOrder);
        }

        return deliveryOrders;
    }

    @Transactional
    public void deleteDeliveryOrder(Integer confirmationNumber) {
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!deliveryOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }

        deliveryOrderRepository.deleteById(confirmationNumber);
    }

    @Transactional
    public void addPurchasedItemToDeliveryOrder(Integer confirmationNumber, PurchasedItem purchasedItem){
        DeliveryOrder d = getDeliveryOrder(confirmationNumber);
        d.getPurchasedItem().add(purchasedItem);
    }

    @Transactional
    public DeliveryOrder setShippingStatus(Integer confirmationNumber, String shippingStatus) {
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!deliveryOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }
        DeliveryOrder newDeliveryOrder = deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
        switch(shippingStatus){
            case "InCart" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.InCart);
            case "Ordered" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Ordered);
            case "Prepared" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Prepared);
            case "Delivered" -> newDeliveryOrder.setShippingStatus(DeliveryOrder.ShippingStatus.Delivered);
            default -> throw new IllegalArgumentException("Invalid shipping status");
        }
        return deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
    }

    @Transactional
    public DeliveryOrder setShippingAddress(Integer current, String address){
        if (address == null || address.equals("") || address.equals(" ")) {
            throw new IllegalArgumentException("Address can't be empty.");
        }
        if(!deliveryOrderRepository.existsById(current)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }
        DeliveryOrder order = getDeliveryOrder(current);
        order.setShippingAddress(address);
        return order;
    }


    @Transactional
    public DeliveryOrder updateTotalCost(Integer OrderId){
        if (OrderId == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (OrderId <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!deliveryOrderRepository.existsById(OrderId)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }
        int totalCost = 0;
        for(PurchasedItem purchasedItem : deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(OrderId).getPurchasedItem()){
            totalCost += purchasedItem.getItemQuantity()*purchasedItem.getItem().getPrice();
        }
        if(deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(OrderId).isOutOfTown())
            totalCost += DeliveryOrder.SHIPPINGFEE;
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(OrderId);
        deliveryOrder.setTotalCost(totalCost);
        return deliveryOrder;
    }
}
