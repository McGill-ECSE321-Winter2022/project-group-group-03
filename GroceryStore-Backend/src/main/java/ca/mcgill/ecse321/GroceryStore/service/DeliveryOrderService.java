package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryCommission;
import ca.mcgill.ecse321.GroceryStore.model.PickupCommission;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.GroceryStore.dao.EmployeeRepository;
import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
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

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    PickupOrderService pickupOrderService;


    /**
     * Creates an object DeliveryOrder with all the credentials that has been provided
     * @param username  the username of the customer/employee to whom the order is associated with
     * @param shippingAddress the address of the customer/employee to whom the order is associated with
     * @param confirmationNumber unique confirmation number associated to the delivery order
     * @param isOutOfTown determines whether a customer/employee is out of town in order to charge an additional fee
     * @return the object of that deliveryOrder that was created with all the credentials
     */
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
    /**
     * Get the specific DeliveryOrder that is associated with the confirmation number
     * @param confirmationNumber the confirmation number of the DeliveryOrder we would like to retrieve information from
     * @return the DeliveryOrder that is associated with the confirmationNumber
     */
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
    /**
     * Gets all list of DeliveryOrders that you can find in the repository
     * @return a list of DeliveryOrder object
     */
    @Transactional
    public List<DeliveryCommission> getAllDeliveryOrders() {
        List<DeliveryCommission> deliveryOrders = new ArrayList<>();
        for (DeliveryCommission deliveryOrder:deliveryOrderRepository.findAll() ) {
            deliveryOrders.add(deliveryOrder);
        }

        return deliveryOrders;
    }
    /**
     * Get the specific DeliveryOrder that is associated with the confirmation number and delete it
     * @param confirmationNumber the confirmation number of the DeliveryOrder we would like to delete
     */
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
    /**
     * Add the specific PurchasedItem to the DeliveryOrder associated to the confirmation number
     * @param confirmationNumber the confirmation number of the DeliveryOrder we would like to add the purchasedItem to
     * @param purchasedItem purchased item object that we want to add to the DeliveryOrder
     */
    @Transactional
    public void addPurchasedItemToDeliveryOrder(Integer confirmationNumber, PurchasedItem purchasedItem){
        DeliveryCommission d = getDeliveryOrder(confirmationNumber);
        List<PurchasedItem> p;
        if(d.getPurchasedItem()==null){
            p=new ArrayList<>();
        }else{
            p=d.getPurchasedItem();
        }
        p.add(purchasedItem);
        this.updateTotalCost(confirmationNumber);
    }
    /**
     * Convert a DeliveryOrder to a PickupOrder
     * @param username username of the customer/employee who wished to convert his delivery to a pickup order
     * @param paymentMethod payment method that the customer/employee wishes to use for the pickupOrder
     * @param accountType account type of the user who wishes to covert it's DeliveryOrder to PickupOrder
     * @return a PickupOrder object
     */
    @Transactional
    public PickupCommission convertDeliveryToPickup(String username, String paymentMethod, String accountType){
        DeliveryCommission commission = null;
        String username1 = null;

        if (employeeRepository.existsById(username)) {
            commission =  (DeliveryCommission) employeeService.getEmployeeOrder(username);
            username1 = username;
        }

        if (customerRepository.existsById(username)) {
            commission =  (DeliveryCommission) customerService.getCustomerOrder(username);
            username1 = username;
        }

        PickupCommission pickupCommission = pickupOrderService.createPickupOrder(username1,paymentMethod,accountType);

        System.out.println(commission.getPurchasedItem());
        for (PurchasedItem purchasedItem : commission.getPurchasedItem()) {
            String pItem=purchasedItem.getItem().getName();
            itemService.addItemStock(pItem,purchasedItem.getItemQuantity());
            pickupOrderService.addPurchasedItemToPickupOrder(pickupCommission.getConfirmationNumber(),purchasedItem);
        }

        deliveryOrderRepository.deleteById(commission.getConfirmationNumber());

        return pickupCommission;
    }
    /**
     * Pay the DeliveryOrder upon entering the confirmation number of that order
     * @param confirmationNumber confirmation number of the DeliveryOrder that is to be paid
     * @return a DeliveryOrder object that is paid
     */
    @Transactional
    public DeliveryCommission pay(Integer confirmationNumber){
        DeliveryCommission d = getDeliveryOrder(confirmationNumber);
        d.pay();
        return d;
    }
    /**
     * Deliver the DeliveryOrder upon entering the confirmation number of that order
     * @param confirmationNumber confirmation number of the DeliveryOrder that is to be delivered
     * @return a DeliveryOrder object that is delivered
     */
    @Transactional
    public DeliveryCommission deliver(Integer confirmationNumber){
        DeliveryCommission d = getDeliveryOrder(confirmationNumber);
        d.deliver();
        return d;
    }
    /**
     * Set the shipping status of a DeliveryOrder upon entering the confirmation number of that order
     * @param confirmationNumber confirmation number of the DeliveryOrder we want to set a status to
     * @param shippingStatus status we want to set the DeliveryOrder to
     * @return a DeliveryOrder associated to the entered confirmation number with the shippingStatus set to it
     */
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
            case "Delivered" -> newDeliveryOrder.setShippingStatus(DeliveryCommission.ShippingStatus.Delivered);
            default -> throw new IllegalArgumentException("Invalid shipping status");
        }
        if (newDeliveryOrder.getShippingStatus().name().equals("Ordered")) {
            for (PurchasedItem purchasedItem : newDeliveryOrder.getPurchasedItem()) {
                String itemName = purchasedItem.getItem().getName();
                itemService.updateItemTotalPurchased(itemName,purchasedItem.getItemQuantity());
            }
            storeService.incrementActiveDelivery();
        }
        if (newDeliveryOrder.getShippingStatus().name().equals("Delivered")) storeService.decrementActiveDelivery();
        return newDeliveryOrder;
    }
    /**
     * Set the shipping address of a DeliveryOrder upon entering the confirmation number of that order
     * @param current confirmation number of the DeliverOrder which we want to set the address
     * @param address address which we want to set to the DeliveryOrder
     * @return a DeliveryOrder associated to the entered confirmation number with the address set to it
     */
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

    /**
     * Update the TotalCost of a DeliveryOrder upon entering the confirmation number of that order
     * @param OrderId confirmation number of the DeliveryOrder which we want to update the TotalCost
     * @return a DeliveryOrder associated to the entered confirmation number with the updated TotalCost
     */
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

    /**
     * Creates an object DeliveryOrder with all the credentials that has been provided
     * @param username  the username of the customer/employee to whom the order is associated with
     * @param shippingAddress the address of the customer/employee to whom the order is associated with
     * @param accountType accountType of the user which desired to create a DeliveryOrder (employee/customer)
     * @param isOutOfTown determines whether a customer/employee is out of town in order to charge an additional fee
     * @return the object of that deliveryOrder that was created with all the credentials
     */
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
        deliveryOrderRepository.save(newDeliveryOrder);
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
