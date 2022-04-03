package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
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

    @Autowired
    StoreService storeService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Transactional
    public DeliveryCommission createDeliveryOrder(String username, String shippingAddress, Integer confirmationNumber, boolean isOutOfTown){
        DeliveryCommission newDeliveryOrder = new DeliveryCommission();
        List<DeliveryCommission> deliveryOrders = this.getAllDeliveryOrders();

        if(shippingAddress == null || shippingAddress.equals("") || shippingAddress.equals(" ")) {
            throw new IllegalArgumentException("Shipping address can't be empty.");
        }
        else if(confirmationNumber == null){
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        else if(confirmationNumber <= 0){
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }

        else if (deliveryOrders != null && deliveryOrders.size() != 0) {
            for (DeliveryCommission d : deliveryOrders) {
                if (d.getConfirmationNumber() == (confirmationNumber)) {
                    throw  new IllegalArgumentException("An identical delivery order with the same confirmation number already exists.");
                }
            }
        }
        newDeliveryOrder.setShippingAddress(shippingAddress);
        newDeliveryOrder.setConfirmationNumber(confirmationNumber);
        newDeliveryOrder.setIsOutOfTown(isOutOfTown);
        newDeliveryOrder.setTotalCost(0);
        newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.InCart);

        newDeliveryOrder.setStore(storeService.getStore());
        deliveryOrderRepository.save(newDeliveryOrder);
        return newDeliveryOrder;
    }
    @Transactional
    public DeliveryCommission getDeliveryOrder(Integer confirmationNumber) {
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
    public List<DeliveryCommission> getAllDeliveryOrders() {
        List<DeliveryCommission> deliveryOrders = new ArrayList<>();
        for (DeliveryCommission deliveryOrder:deliveryOrderRepository.findAll() ) {
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
        DeliveryCommission d = getDeliveryOrder(confirmationNumber);
        d.getPurchasedItem().add(purchasedItem);
    }

    @Transactional
    public DeliveryCommission setShippingStatus(Integer confirmationNumber, String shippingStatus) {
        if (confirmationNumber == null) {
            throw new IllegalArgumentException("Confirmation number can't be empty.");
        }
        if (confirmationNumber <= 0) {
            throw new IllegalArgumentException("Confirmation number must be greater than 0.");
        }
        if(!deliveryOrderRepository.existsById(confirmationNumber)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }
        DeliveryCommission newDeliveryOrder = deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
        switch(shippingStatus){
            case "InCart" -> newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.InCart);
            case "Ordered" -> newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.Ordered);
            case "Prepared" -> newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.Prepared);
            case "Delivered" -> newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.Delivered);
            default -> throw new IllegalArgumentException("Invalid shipping status");
        }
        if (newDeliveryOrder.getShippingStatus().name().equals("Ordered")) storeService.incrementActiveDelivery();
        if (newDeliveryOrder.getShippingStatus().name().equals("Delivered")) storeService.decrementActiveDelivery();
        return newDeliveryOrder;
    }

    @Transactional
    public DeliveryCommission setShippingAddress(Integer current, String address){
        if (address == null || address.equals("") || address.equals(" ")) {
            throw new IllegalArgumentException("Address can't be empty.");
        }
        if(!deliveryOrderRepository.existsById(current)){
            throw new IllegalArgumentException("Delivery order doesn't exist.");
        }
        DeliveryCommission order = getDeliveryOrder(current);
        order.setShippingAddress(address);
        return order;
    }


    @Transactional
    public DeliveryCommission updateTotalCost(Integer OrderId){
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
            totalCost += DeliveryCommission.SHIPPINGFEE;
        DeliveryCommission deliveryOrder = deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(OrderId);
        deliveryOrder.setTotalCost(totalCost);
        return deliveryOrder;
    }

    private static int curID = 100000;

    @Transactional
    public DeliveryCommission createDeliveryOrder(String username, String shippingAddress, String accountType, boolean isOutOfTown){
        DeliveryCommission newDeliveryOrder = new DeliveryCommission();

        if(shippingAddress == null || shippingAddress.equals("") || shippingAddress.equals(" ")) {
            throw new IllegalArgumentException("Shipping address can't be empty.");
        }
        while(deliveryOrderRepository.existsById(curID)){
            curID++;
        }
        newDeliveryOrder.setShippingAddress(shippingAddress);
        newDeliveryOrder.setConfirmationNumber(curID);
        newDeliveryOrder.setIsOutOfTown(isOutOfTown);
        newDeliveryOrder.setTotalCost(0);
        newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.InCart);

        newDeliveryOrder.setStore(storeService.getStore());
        if (accountType.equals("Customer")){
            customerService.addOrder(username, newDeliveryOrder);
            newDeliveryOrder.setCustomer(customerService.getCustomer(username));
        }
        else if (accountType.equals("Employee")) {
            employeeService.addOrder(username, newDeliveryOrder);
            newDeliveryOrder.setEmployee(employeeService.getEmployee(username));
        }
        deliveryOrderRepository.save(newDeliveryOrder);
        return newDeliveryOrder;
    }


}
