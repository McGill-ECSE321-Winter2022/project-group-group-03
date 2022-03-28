package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import ca.mcgill.ecse321.GroceryStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class StoreRestController {
    @Autowired
    private StoreService service;

    @PostMapping(value = { "/store", "/store/" })
    public StoreDTO createStore(@RequestParam String aAddress, @RequestParam int aCurrentActiveDelivery, @RequestParam int aCurrentActivePickup) throws IllegalArgumentException {
        Store store = service.createStore(aAddress, aCurrentActiveDelivery, aCurrentActivePickup);
        return convertToDto(store);
    }
    @GetMapping(value = { "/store", "/store" })
    public StoreDTO getStoreByID() throws IllegalArgumentException {
        return convertToDto(service.getStore());
    }

    @GetMapping(value = { "/employee/store", "/employee/store/" })
    public List<EmployeeDTO> getEmployeesOfStore() {
        return createEmployeeDtosForStore();
    }
    @GetMapping(value = { "/item/store", "/item/store/" })
    public List<ItemDTO> getItemsOfStore() {
        return createItemDtosForStore();
    }
    @GetMapping(value = { "/holiday/store", "/holiday/store/" })
    public List<HolidayDTO> getHolidaysOfStore() {
        return createHolidayDtosForStore();
    }
    @GetMapping(value = { "/business_hour/store", "/business_hour/store/" })
    public List<BusinessHourDTO> getBusinessHoursOfStore() {
        return createBusinessHourDtosForStore();
    }
    @PutMapping(value = {"/editStoreActiveDelivery/"})
    public StoreDTO updateStoreActiveDelivery(@RequestParam int newCurrentActiveDelivery) throws IllegalArgumentException{
        return convertToDto(service.setActiveDelivery(newCurrentActiveDelivery));
    }
    @PutMapping(value = {"/editStoreActivePickup"})
    public StoreDTO updateStoreActivePickup(@RequestParam int newCurrentActivePickup) throws  IllegalArgumentException{
        return convertToDto(service.setActivePickup(newCurrentActivePickup));
    }
    @PutMapping(value = {"/editStoreAddress"})
    public StoreDTO updateStoreAddress(@RequestParam String address) throws IllegalArgumentException{
        return convertToDto(service.setAddress(address));
    }

    private StoreDTO convertToDto(Store c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Store!");
        }
        return new StoreDTO(c.getStoreID(),c.getAddress(),c.getCurrentActiveDelivery(),c.getCurrentActivePickup());
    }
    private EmployeeDTO convertToDto(Employee e) {
        if (e == null) {
            throw new IllegalArgumentException("There is no such Employee!");
        }
        return new EmployeeDTO(e.getUsername(),e.getPassword(),e.getEmail(),e.getAddress(), e.getWorkingStatusFullName());
    }

    private ItemDTO convertToDto(Item i) {
        if (i == null) {
            throw new IllegalArgumentException("There is no such Item!");
        }
        return new ItemDTO(i.getName(),i.getPurchasable(),i.getPrice(),i.getDescription(),i.getStock(),i.getTotalPurchased());
    }
    private HolidayDTO convertToDto(Holiday h) {
        if (h == null) {
            throw new IllegalArgumentException("There is no such Holiday!");
        }
        return new HolidayDTO(h.getName(), h.getStartDate(), h.getEndDate());
    }
    private BusinessHourDTO convertToDto(BusinessHour b) {
        if (b == null) {
            throw new IllegalArgumentException("There is no such BusinessHour!");
        }
        return new BusinessHourDTO(b.getHoursID(), b.getStartTime(), b.getEndTime(), b.getDay().name());
    }


    private List<EmployeeDTO> createEmployeeDtosForStore() {
        List<Employee> employeesForStore = service.getEmployees();
        List<EmployeeDTO> employees = new ArrayList<>();
        for (Employee employee : employeesForStore) {
            employees.add(convertToDto(employee));
        }
        return employees;
    }

    private List<ItemDTO> createItemDtosForStore() {
        List<Item> itemsForStore = service.getItems();
        List<ItemDTO> items = new ArrayList<>();
        for (Item item : itemsForStore) {
            items.add(convertToDto(item));
        }
        return items;
    }

    private List<HolidayDTO> createHolidayDtosForStore() {
        List<Holiday> holidaysForStore = service.getStoreHolidays();
        List<HolidayDTO> holidays = new ArrayList<>();
        for (Holiday holiday : holidaysForStore) {
            holidays.add(convertToDto(holiday));
        }
        return holidays;
    }
    private List<BusinessHourDTO> createBusinessHourDtosForStore() {
        List<BusinessHour> businessHoursForStore = service.getBusinessHours();
        List<BusinessHourDTO> businessHours = new ArrayList<>();
        for (BusinessHour businessHour : businessHoursForStore) {
            businessHours.add(convertToDto(businessHour));
        }
        return businessHours;
    }

}
