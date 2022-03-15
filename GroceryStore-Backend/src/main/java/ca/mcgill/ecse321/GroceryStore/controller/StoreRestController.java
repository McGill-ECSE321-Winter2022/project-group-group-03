package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import ca.mcgill.ecse321.GroceryStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class StoreRestController {
    @Autowired
    private StoreService service;

    @GetMapping(value = {"/store", "/store/"})
    public List<StoreDTO> getAllStores(){
        return service.getAllStores().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @PostMapping(value = { "/store/{storeID}", "/store/{storeID}/" })
    public StoreDTO createStore(@PathVariable("storeID") int storeID, @RequestParam String aAddress, @RequestParam int aCurrentActiveDelivery, @RequestParam int aCurrentActivePickup) throws IllegalArgumentException {
        Store store = service.createStore(storeID, aAddress, aCurrentActiveDelivery, aCurrentActivePickup);
        return convertToDto(store);
    }
    @GetMapping(value = { "/store/{storeID}", "/store/{storeID}/" })
    public StoreDTO getStoreByID(@PathVariable("storeID") int storeID) throws IllegalArgumentException {
        return convertToDto(service.getStore(storeID));
    }

    @GetMapping(value = { "/employee/store/{storeID}", "/employee/store/{storeID}/" })
    public List<EmployeeDTO> getEmployeesOfStore(@PathVariable("storeID") StoreDTO sDTO) {
        Store s = convertToDomainObject(sDTO);
        return createEmployeeDtosForStore(s);
    }
    @GetMapping(value = { "/item/store/{storeID}", "/item/store/{storeID}/" })
    public List<ItemDTO> getItemsOfStore(@PathVariable("storeID") StoreDTO sDTO) {
        Store s = convertToDomainObject(sDTO);
        return createItemDtosForStore(s);
    }
    @GetMapping(value = { "/holiday/store/{storeID}", "/holiday/store/{storeID}/" })
    public List<HolidayDTO> getHolidaysOfStore(@PathVariable("storeID") StoreDTO sDTO) {
        Store s = convertToDomainObject(sDTO);
        return createHolidayDtosForStore(s);
    }
    @GetMapping(value = { "/business_hour/store/{storeID}", "/business_hour/store/{storeID}/" })
    public List<BusinessHourDTO> getBusinessHoursOfStore(@PathVariable("storeID") StoreDTO sDTO) {
        Store s = convertToDomainObject(sDTO);
        return createBusinessHourDtosForStore(s);
    }
    @PutMapping(value = {"/editStoreActiveDelivery/{storeID}"})
    public StoreDTO updateStoreActiveDelivery(@PathVariable("storeID") int storeID, @RequestParam int newCurrentActiveDelivery) throws IllegalArgumentException{
        return convertToDto(service.setActiveDelivery(storeID, newCurrentActiveDelivery));
    }
    @PutMapping(value = {"/editStoreActivePickup/{storeID"})
    public StoreDTO updateStoreActivePickup(@PathVariable("storeID") int storeID, @RequestParam int newCurrentActivePickup) throws  IllegalArgumentException{
        return convertToDto(service.setActivePickup(storeID,newCurrentActivePickup));
    }
    @PutMapping(value = {"/editStoreAddress/{storeID}"})
    public StoreDTO updateStoreAddress(@PathVariable("storeID") int storeID, @RequestParam String address) throws IllegalArgumentException{
        return convertToDto(service.setAddress(storeID, address));
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
        return new EmployeeDTO(e.getUsername(),e.getPassword(),e.getEmail(),e.getAddress());
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


    private Store convertToDomainObject(StoreDTO eDto) {
        List<Store> allStores = service.getAllStores();
        for (Store store : allStores) {
            if (store.getStoreID() == (eDto.getStoreID())) {
                return store;
            }
        }
        return null;
    }

    private List<EmployeeDTO> createEmployeeDtosForStore(Store s) {
        List<Employee> employeesForStore = service.getEmployees(s.getStoreID());
        List<EmployeeDTO> employees = new ArrayList<>();
        for (Employee employee : employeesForStore) {
            employees.add(convertToDto(employee));
        }
        return employees;
    }

    private List<ItemDTO> createItemDtosForStore(Store s) {
        List<Item> itemsForStore = service.getItems(s.getStoreID());
        List<ItemDTO> items = new ArrayList<>();
        for (Item item : itemsForStore) {
            items.add(convertToDto(item));
        }
        return items;
    }

    private List<HolidayDTO> createHolidayDtosForStore(Store s) {
        List<Holiday> holidaysForStore = service.getStoreHolidays(s.getStoreID());
        List<HolidayDTO> holidays = new ArrayList<>();
        for (Holiday holiday : holidaysForStore) {
            holidays.add(convertToDto(holiday));
        }
        return holidays;
    }
    private List<BusinessHourDTO> createBusinessHourDtosForStore(Store s) {
        List<BusinessHour> businessHoursForStore = service.getBusinessHours(s.getStoreID());
        List<BusinessHourDTO> businessHours = new ArrayList<>();
        for (BusinessHour businessHour : businessHoursForStore) {
            businessHours.add(convertToDto(businessHour));
        }
        return businessHours;
    }

}
