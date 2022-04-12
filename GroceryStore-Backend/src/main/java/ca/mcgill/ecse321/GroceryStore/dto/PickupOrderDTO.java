package ca.mcgill.ecse321.GroceryStore.dto;



public class PickupOrderDTO {

    private String paymentMethod;
    private String pickupStatus;
    private int confirmationNumber;
    private int totalCost;
    private String username;



    public PickupOrderDTO(String paymentMethod, String pickupStatus, int confirmationNumber, int totalCost, String username){
        this.paymentMethod = paymentMethod;
        this.pickupStatus = pickupStatus;
        this.confirmationNumber = confirmationNumber;
        this.totalCost = totalCost;
        this.username = username;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
    public String getPickupStatus(){
        return pickupStatus;
    }
    public int getConfirmationNumber() {return confirmationNumber;}
    public int getTotalCost(){return totalCost;}
    public String getUsername() {return username;}

}
