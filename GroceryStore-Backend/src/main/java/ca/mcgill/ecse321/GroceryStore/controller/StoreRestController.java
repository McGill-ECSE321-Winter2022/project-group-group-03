package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.StoreDTO;
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
        return service.getAllStores().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
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


    private StoreDTO convertToDto(Store c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Store!");
        }
        StoreDTO storeDTO = new StoreDTO(c.getStoreID(),c.getAddress(),c.getCurrentActiveDelivery(),c.getCurrentActivePickup());
        return storeDTO;
    }
    private EmployeeDTO convertToDto(Employee e) {
        if (e == null) {
            throw new IllegalArgumentException("There is no such Employee!");
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(e.getUsername(),e.getPassword(),e.getEmail(),e.getAddress());
        return employeeDTO;
    }

    private ItemDTO convertToDto(Item i) {
        if (i == null) {
            throw new IllegalArgumentException("There is no such Item!");
        }
        ItemDTO itemDTO = new ItemDTO(i.getName(), i.getPurchasable(), i.getDescription(),i.getStock(), i.getTotalPurchased());
        return itemDTO;
    }
    private HolidayDTO convertToDto(Holiday h) {
        if (h == null) {
            throw new IllegalArgumentException("There is no such Holiday!");
        }
        HolidayDTO holidayDTO = new HolidayDTO(h.getName(), h.getStartDate(), h.getEndDate());
        return holidayDTO;
    }
    private BusinessHourDTO convertToDto(BusinessHour b) {
        if (b == null) {
            throw new IllegalArgumentException("There is no such BusinessHour!");
        }
        BusinessHourDTO businessHourDTO = new BusinessHourDTO(b.getHoursID(), b.getStartTime(), b.getEndTime(), b.getDay().name());
        return businessHourDTO;
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
        List<EmployeeDTO> employees = new ArrayList();
        for (Employee employee : employeesForStore) {
            employees.add(convertToDto(employee));
        }
        return employees;
    }

    private List<ItemDTO> createItemDtosForStore(Store s) {
        List<Item> itemsForStore = service.getItems(s.getStoreID());
        List<ItemDTO> items = new ArrayList();
        for (Item item : itemsForStore) {
            items.add(convertToDto(item));
        }
        return items;
    }

    private List<HolidayDTO> createHolidayDtosForStore(Store s) {
        List<Holiday> holidaysForStore = service.getStoreHolidays(s.getStoreID());
        List<HolidayDTO> holidays = new ArrayList();
        for (Holiday holiday : holidaysForStore) {
            holidays.add(convertToDto(holiday));
        }
        return holidays;
    }
    private List<BusinessHourDTO> createBusinessHourDtosForStore(Store s) {
        List<BusinessHour> businessHoursForStore = service.getBusinessHours(s.getStoreID());
        List<BusinessHourDTO> businessHours = new ArrayList();
        for (BusinessHour businessHour : businessHoursForStore) {
            businessHours.add(convertToDto(businessHour));
        }
        return businessHours;
    }

}
