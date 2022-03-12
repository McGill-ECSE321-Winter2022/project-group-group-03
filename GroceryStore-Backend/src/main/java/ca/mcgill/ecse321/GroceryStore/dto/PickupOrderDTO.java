package ca.mcgill.ecse321.GroceryStore.dto;

import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;

public class PickupOrderDTO {

    private String paymentMethod;
    private String pickupStatus;

    public PickupOrderDTO(){}

    public PickupOrderDTO(String paymentMethod, String pickupStatus){
        this.paymentMethod = paymentMethod;
        this.pickupStatus = pickupStatus;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public String getPickupStatus(){
        return pickupStatus;
    }

}
