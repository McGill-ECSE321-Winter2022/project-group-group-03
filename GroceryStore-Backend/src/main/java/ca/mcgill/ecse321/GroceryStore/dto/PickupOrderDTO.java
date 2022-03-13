package ca.mcgill.ecse321.GroceryStore.dto;



public class PickupOrderDTO {

    private String paymentMethod;
    private String pickupStatus;
    private int confirmationNumber;
    private int totalCost;



    public PickupOrderDTO(int confirmationNumber, int totalCost, String paymentMethod, String pickupStatus){
        this.paymentMethod = paymentMethod;
        this.pickupStatus = pickupStatus;
        this.confirmationNumber = confirmationNumber;
        this.totalCost = totalCost;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public String getPickupStatus(){
        return pickupStatus;
    }
    public int getConfirmationNumber() {return confirmationNumber;}
    public int getTotalCost(){return totalCost;}

}
