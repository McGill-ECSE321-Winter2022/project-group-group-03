package ca.mcgill.ecse321.GroceryStore.dto;



import java.util.List;

public class StoreDTO {
    private int storeID;
    private String address;
    private int currentActivePickup;
    private int currentActiveDelivery;

    private List<EmployeeDTO> employees;
    private List<ItemDTO> items;
    private List<HolidayDTO> holidays;
    private List<BusinessHourDTO> businesshours;





    public StoreDTO(int storeID, String address, int currentActiveDelivery, int currentActivePickup){
        this.storeID = storeID;
        this.address = address;
        this.currentActiveDelivery = currentActiveDelivery;
        this.currentActivePickup = currentActivePickup;
    }

    public int getStoreID(){
        return storeID;
    }
    public String getAddress(){
        return address;
    }
    public int getCurrentActivePickup(){
        return currentActivePickup;
    }
    public int getCurrentActiveDelivery(){
        return currentActiveDelivery;
    }
    public List<EmployeeDTO> getEmployees(){
        return employees;
    }
    public List<ItemDTO> getItems(){
        return items;
    }
    public List<HolidayDTO> getHolidays(){
        return holidays;
    }
    public List<BusinessHourDTO> getBusinesshours(){
        return businesshours;
    }
    public void setEmployees(List<EmployeeDTO> employees){
        this.employees = employees;
    }
    public void setItems(List<ItemDTO> items){
        this.items = items;
    }
    public void setHolidays(List<HolidayDTO> holidays){
        this.holidays = holidays;
    }
    public void setBusinesshours(List<BusinessHourDTO> businesshours){
        this.businesshours = businesshours;
    }
}


