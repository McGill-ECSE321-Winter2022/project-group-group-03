package ca.mcgill.ecse321.GroceryStore.dto;


import java.util.List;

public class EmployeeDTO {
    private String username;
    private String password;
    private String email;
    private String address;

    private List<WorkShiftDTO> workShifts;
    private List<OrderDTO> orders;

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

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setWorkShifts(List<WorkShiftDTO> workShifts) {
        this.workShifts = workShifts;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

}