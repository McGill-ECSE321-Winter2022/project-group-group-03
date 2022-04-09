package ca.mcgill.ecse321.GroceryStore.dto;


import java.util.List;

public class EmployeeDTO {
    private String username;
    private String password;
    private String email;
    private String address;
    private String workingStatus;

    private List<WorkShiftDTO> workShifts;
    private List<OrderDTO> commissions;



    public EmployeeDTO(String username, String password, String email, String address, String workingStatus){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.workingStatus = workingStatus;
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

    public String getWorkingStatus() {
        return workingStatus;
    }

    public List<WorkShiftDTO> getWorkShifts() {
        return workShifts;
    }

    public List<OrderDTO> getOrders() {
        return commissions;
    }

    public void setWorkShifts(List<WorkShiftDTO> workShifts) {
        this.workShifts = workShifts;
    }

    public void setOrders(List<OrderDTO> commissions) {
        this.commissions = commissions;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }
}