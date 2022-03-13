package ca.mcgill.ecse321.GroceryStore.dto;



public class DeliveryOrderDTO {

    private String shippingAddress;
    private String shippingStatus;
    private int confirmationNumber;
    private int totalCost;


    public DeliveryOrderDTO(String shippingAddress, String shippingStatus, int confirmationNumber, int totalCost){
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
        this.confirmationNumber = confirmationNumber;
        this.totalCost = totalCost;
    }
    public String getShippingAddress(){
        return shippingAddress;
    }
    public String getShippingStatus(){
        return shippingStatus;
    }

    public int getConfirmationNumber() {return confirmationNumber;}
    public int getTotalCost() {return totalCost;}
}
