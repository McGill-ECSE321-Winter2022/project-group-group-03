package ca.mcgill.ecse321.GroceryStore.dto;


public class EmployeeDTO {
    private String username;
    private String password;
    private String email;
    private String address;

    private List<WorkShiftDTO> workShifts;
    private List<DeliveryOrderDTO> deliveryOrders;

    public EmployeeDTO(){
    }

    @SuppressWarnings("unchecked")
    public EmployeeDTO(String username, String password, String email, String address){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public List<WorkShiftDTO> getWorkShifts() {
        return workShifts;
    }

    public List<DeliveryOrderDTO> getDeliveryOrders() {
        return deliveryOrders;
    }

    public void setWorkShifts(List<WorkShiftDTO> workShifts) {
        this.workShifts = workShifts;
    }

    public void setDeliveryOrders(List<DeliveryOrderDTO> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }
}