package ca.mcgill.ecse321.GroceryStore.dto;

import java.util.List;

public class CustomerDTO {

    private String username;
    private String password;
    private String email;
    private String address;

    private List<DeliveryOrderDTO> deliveryOrders;
    private List<PickupOrderDTO> pickupOrders;


    public CustomerDTO(String username, String password, String email, String address){
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public List<DeliveryOrderDTO> getDeliveryOrders() {
        return deliveryOrders;
    }
    public List<PickupOrderDTO> getPickupOrders() {
        return pickupOrders;
    }
    public void setDeliveryOrders(List<DeliveryOrderDTO> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }
    public void setPickupOrders(List<PickupOrderDTO> pickupOrders) {
        this.pickupOrders = pickupOrders;
    }
}
