package ca.mcgill.ecse321.GroceryStore.dto;

public class DeliveryOrderDTO {

    private String shippingAddress;
    private String shippingStatus;


    public DeliveryOrderDTO(){}

    public DeliveryOrderDTO(String shippingAddress, String shippingStatus){
        this.shippingAddress = shippingAddress;
        this.shippingStatus = shippingStatus;
    }
    public String getShippingAddress(){
        return shippingAddress;
    }
    public String getShippingStatus(){
        return shippingStatus;
    }


}
