package ca.mcgill.ecse321.GroceryStore.dao;


import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStorePersistence {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BusinessHourRepository businessHourRepository;
    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PickupOrderRepository pickupOrderRepository;
    @Autowired
    private PurchasedItemRepository purchasedItemRepository;
    @Autowired
    private WorkShiftRepository workShiftRepository;

    //Store
    Store defaultStore = new Store();

    //Order -> A customer can have many commissions therefore we use a list
    PickupCommission defaultOrder = new PickupCommission();
    List<PickupCommission> orderList = new ArrayList<>();

    //Item -> A customer can have many items therefore we use a list
    Item defaultItem = new Item();
    List<Item> itemList = new ArrayList<>();

    //Workshift -> An employee can have many workshifts therefore we use a list
    WorkShift defaultWorkShift = new WorkShift();
    List<WorkShift> workShiftList = new ArrayList<>();

    //Purchased Item -> An order can have many purchased items therefore we use a list
    PurchasedItem defaultPurchasedItem = new PurchasedItem();
    List<PurchasedItem> purchasedItemList = new ArrayList<>();

    //Holiday -> Many holidays therefore we use a list
    Holiday defaultHoliday = new Holiday();
    List<Holiday> holidayList = new ArrayList<>();


    //These methods will initialize and create the references that will test the following associations

    //Store
    public void initializeDefaultStore(){
        this.defaultStore.setStoreID(123);
    }
    //Assign attributes to the temporary PickupOrder object
    public void initializeDefaultOrder(){
        this.defaultOrder.setConfirmationNumber(123);
        this.defaultOrder.setPaymentMethod(PickupCommission.PaymentMethod.Cash);
        this.defaultOrder.setPickupStatus(PickupCommission.PickupStatus.Ordered);
    }
    //Assign attributes to the temporary Item object
    public void initializeDefaultItem(){
        this.defaultItem.setName("Heinz");
    }

    public void initializeDefaultWorkShift(){
       this.defaultWorkShift.setShiftID(123);
       this.defaultWorkShift.setDay(WorkShift.DayOfWeek.Monday);
    }
    //Assign attributes to the temporary PurchasedItem object
    public void initializeDefaultPurchasedItem(){
        this.defaultPurchasedItem.setPurchasedItemID(123);
    }
    //Assign attributes to the temporary Holiday object
    public void initializeDefaultHoliday(){
        this.defaultHoliday.setName("Day of the Ari");
        this.defaultHoliday.setStartDate(java.sql.Date.valueOf(LocalDate.of(2022, Month.MAY, 31)));
        this.defaultHoliday.setEndDate(java.sql.Date.valueOf(LocalDate.of(2022, Month.JULY, 10)));
    }
    
    public void delete1Order(PickupCommission order2Delete){
        orderList.remove(order2Delete);
        pickupOrderRepository.deleteById(order2Delete.getConfirmationNumber());
    }

    public void delete1Workshift(WorkShift workShift2Delete){
        workShiftList.remove(workShift2Delete);
        workShiftRepository.deleteById(workShift2Delete.getShiftID());
    }

    public void delete1PurchasedItem(PurchasedItem purchasedItem2Delete){
        purchasedItemList.remove(purchasedItem2Delete);
        purchasedItemRepository.deleteById(purchasedItem2Delete.getPurchasedItemID());
    }

    public void delete1Holiday(Holiday holiday2Delete){
        holidayList.remove(holiday2Delete);
        holidayRepository.deleteById(holiday2Delete.getName());
    }

    @AfterEach
	public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        holidayRepository.deleteAll();
        purchasedItemRepository.deleteAll();
        businessHourRepository.deleteAll();
        workShiftRepository.deleteAll();
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
        pickupOrderRepository.deleteAll();
        deliveryOrderRepository.deleteAll();
        itemRepository.deleteAll();
        ownerRepository.deleteAll();
        storeRepository.deleteAll();

    }
    /**
     * Tests read and write capabilities with Store objects and attributes.
     * Association is tested between Store and Holiday by creating a temporary Holiday object initialized with some attributes, 
     * then deleting it, and checking to see from the Store class that the holiday instance is deleted.
     */
    @Test
    @Transactional
	public void testPersistAndLoadStore() {
		int storeID = 1;
        String address = "215 avenue Kenaston";
        int currentActivePickup = 5;
        int currentActiveDelivery = 6;
		// First example for object save/load
		Store store2Eval = new Store();
        store2Eval.setStoreID(storeID);                             //set attributes
        store2Eval.setAddress(address);
        store2Eval.setCurrentActiveDelivery(currentActiveDelivery);
        store2Eval.setCurrentActivePickup(currentActivePickup);

        initializeDefaultHoliday();                                 
        holidayList.add(defaultHoliday);
        store2Eval.setHoliday(holidayList);

        holidayRepository.saveAll(holidayList);
        storeRepository.save(store2Eval);
        store2Eval = null;
        store2Eval = storeRepository.findById(storeID);

        assertNotNull(store2Eval);
		assertEquals(storeID, store2Eval.getStoreID());

        delete1Holiday(defaultHoliday);
        assertNull(holidayRepository.findByName(defaultHoliday.getName()));
        assertFalse(storeRepository.findById(storeID).getHoliday().contains(defaultHoliday));

	}

    /**
     * Tests read and write capabilities with Employee objects and attributes.
     * Association is tested between Employee and Workshift by creating a temporary object initialized with some attributes, 
     * then deleting it, and checking to see from the Employee class that the Workshift instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadEmployee() {
        String username = "Seb";
        Employee employee = new Employee();
        employee.setWorkingStatus(Employee.WorkingStatus.Hired);
        employee.setUsername(username);
        employee.setAddress("address");
        employee.setEmail("email");
        employee.setPassword("12345");


        initializeDefaultWorkShift();
        this.workShiftList.add(defaultWorkShift);
        employee.setWorkShift(workShiftList);
        workShiftRepository.saveAll(workShiftList);
        employeeRepository.save(employee);

        employee = null;

        employee = employeeRepository.findByUsername(username);
        assertNotNull(employee);
        assertEquals(username, employee.getUsername());

        delete1Workshift(defaultWorkShift);
        assertNull(workShiftRepository.findByShiftID(defaultWorkShift.getShiftID()));
        assertFalse(employeeRepository.findByUsername(username).getWorkShift().contains(defaultWorkShift));
    }
    /**
     * Tests read and write capabilities with Customer objects and attributes.
     * Association is tested between Customer and PickupOrder by creating a temporary PickupOrder object initialized with some attributes, 
     * then deleting it, and checking to see from the Customer class that the PickupOrder instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadCustomer(){
        String username = "Arsters";
        String password = "Testies";
        String email = "arsters.testies@gmail.com";
        String address = "215 asdjkahd";

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setAddress(address);
        initializeDefaultOrder();
        this.orderList.add(defaultOrder);
        pickupOrderRepository.saveAll(orderList);

        customerRepository.save(customer);
        customer = null;
        customer = customerRepository.findCustomerByUsername(username);
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());

        delete1Order(defaultOrder);
        assertNull(pickupOrderRepository.findByConfirmationNumber(defaultOrder.getConfirmationNumber()));
        //There is only one order in the list so by removing that order we can verify if the list is null
        assertNull(customerRepository.findCustomerByUsername(username).getOrder());
    }
    /**
     * Tests read and write capabilities with Item objects and attributes.
     * There is no association to test here since we only have one unidirectional association coming from store.
     */
    @Test
    @Transactional
    public void testPersistAndLoadItem(){
        String name = "Cheese balls";
        boolean purchasable = true;
        int price = 10;
        String description = "Cheesiest balls in the game";
        int stock = 15;
        int totalPurchased = 2;

        Item item = new Item();
        item.setName(name);
        item.setPurchasable(purchasable);
        item.setPrice(price);
        item.setDescription(description);
        item.setStock(stock);
        item.setTotalPurchased(totalPurchased);
        itemList.add(item);
        itemRepository.saveAll(itemList);
        item = null;

        item = itemRepository.findByName(name);
        assertNotNull(item);
        assertEquals(name, item.getName());

    }
    /**
     * Tests read and write capabilities with BusinessHour objects and attributes.
     * There is no association to test since we only have one unidirectional association coming from store.
     */
    @Test
    @Transactional
    public void testPersistAndLoadBusinessHour(){
        int hoursID = 123;
        Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(18, 55));
        BusinessHour.DayOfWeek day = BusinessHour.DayOfWeek.Monday;

        BusinessHour businessHour = new BusinessHour();
        businessHour.setHoursID(hoursID);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setDay(day);

        businessHourRepository.save(businessHour);

        businessHour = businessHourRepository.findByHoursID(hoursID);
        assertNotNull(businessHour);
        assertEquals(hoursID, businessHour.getHoursID());

    }
    /**
     * Tests read and write capabilities with DeliveryOrder objects and attributes.
     */
    @Test
    @Transactional
    public void testPersistAndLoadDeliveryOrder(){
        int confirmationNumber = 123;
        int totalCost = 10;
        String shippingAddress = "3064 rue edmond rostand";
        DeliveryCommission.ShippingStatus status = DeliveryCommission.ShippingStatus.Ordered;

        DeliveryCommission deliveryOrder = new DeliveryCommission();
        deliveryOrder.setConfirmationNumber(confirmationNumber);
        deliveryOrder.setTotalCost(totalCost);
        deliveryOrder.setShippingAddress(shippingAddress);
        deliveryOrder.setShippingStatus(status);
        deliveryOrder.setIsOutOfTown(true);
        deliveryOrderRepository.save(deliveryOrder);
        deliveryOrder = null;

        deliveryOrder = deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(confirmationNumber);
        assertNotNull(deliveryOrder);
        assertEquals(confirmationNumber, deliveryOrder.getConfirmationNumber());
    }
    /**
     * Tests read and write capabilities with Holiday objects and attributes.
     * There is no association to test since we only have one unidirectional association coming from store.
     */
    @Test
    @Transactional
    public void testPersistAndLoadHoliday(){
        String name = "Spring grace week";
        Date startDate = java.sql.Date.valueOf(LocalDate.of(2022, Month.MARCH, 31));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2022, Month.APRIL, 10));

        Holiday holiday = new Holiday();
        holiday.setName(name);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);

        holidayRepository.save(holiday);
        holiday = null;

        holiday = holidayRepository.findByName(name);
        assertNotNull(holiday);
        assertEquals(name, holiday.getName());

    }
    /**
     * Tests read and write capabilities with Owner objects and attributes.
     * Association is tested between Owner and Store by creating a temporary Store object initialized with some attributes, 
     * then deleting it, and checking to see from the Owner class that the Owner instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadOwner(){
        String username = "Thad Castle";
        String password = "BlueMountainState";
        String email = "ThadCastle@gmail.com";

        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setEmail(email);
        owner.setPassword(password);
        initializeDefaultStore();
        owner.setStore(defaultStore);
        storeRepository.save(defaultStore);
        ownerRepository.save(owner);
        owner = null;

        owner = ownerRepository.findByUsername(username);
        assertNotNull(owner);
        assertEquals(username, owner.getUsername());

        //Check association between owner and store
        assertEquals(storeRepository.findById(defaultStore.getStoreID()), ownerRepository.findByUsername(username).getStore());

    }

    /**
     * Tests read and write capabilities with PickupOrder objects and attributes.
     * Association is tested between PickupOrder and PurchasedItem by creating a temporary PurchasedItem object initialized with some attributes, 
     * then deleting it, and checking to see from the PickupOrder class that the PurchasedItem instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadPickupOrder(){
        int confirmationNumber = 321;
        int totalCost = 5;
        PickupCommission.PaymentMethod paymentMethod = PickupCommission.PaymentMethod.Cash;
        PickupCommission.PickupStatus status = PickupCommission.PickupStatus.InCart;

        PickupCommission pickupOrder = new PickupCommission();
        pickupOrder.setConfirmationNumber(confirmationNumber);
        pickupOrder.setTotalCost(totalCost);
        pickupOrder.setPaymentMethod(paymentMethod);
        pickupOrder.setPickupStatus(status);

        orderList.add(pickupOrder);
        initializeDefaultPurchasedItem();
        this.purchasedItemList.add(defaultPurchasedItem);
        pickupOrder.setPurchasedItem(purchasedItemList);

        purchasedItemRepository.saveAll(purchasedItemList);
        pickupOrderRepository.save(pickupOrder);
        pickupOrder = null;

        pickupOrder = pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
        assertNotNull(pickupOrder);
        assertEquals(confirmationNumber, pickupOrder.getConfirmationNumber());

        delete1PurchasedItem(defaultPurchasedItem);
        assertNull(purchasedItemRepository.findByPurchasedItemID(defaultPurchasedItem.getPurchasedItemID()));
        assertFalse(pickupOrderRepository.findByConfirmationNumber(confirmationNumber).getPurchasedItem().contains(defaultPurchasedItem));

    }
    /**
     * Tests read and write capabilities with PurchasedItem objects and attributes.
     * Association is tested between PurchasedItem and Item by creating a temporary Item object initialized with some attributes, 
     * then deleting it, and checking to see from the PurchasedItem class that the Item instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadPurchasedItem(){
        int purchasedItemID = 789;
        int quantity = 6;

        PurchasedItem purchasedItem = new PurchasedItem();
        purchasedItem.setPurchasedItemID(purchasedItemID);
        purchasedItem.setItemQuantity(quantity);
        initializeDefaultItem();

        purchasedItem.setItem(defaultItem);
        itemRepository.save(defaultItem);
        purchasedItemRepository.save(purchasedItem);

        purchasedItem = null;

        purchasedItem = purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        assertNotNull(purchasedItem);
        assertEquals(purchasedItemID, purchasedItem.getPurchasedItemID());

        String name = purchasedItemRepository.findByPurchasedItemID(purchasedItemID).getItem().getName();
        String name2Compare = itemRepository.findByName(defaultItem.getName()).getName();
        assertEquals( name, name2Compare);
    }
    /**
     * Tests read and write capabilities with WorkShift objects and attributes.
     * There is no association to test since we only have one unidirectional association coming from Employee.
     */
    @Test
    @Transactional
    public void testPersistAndLoadWorkShift(){
        int shiftID = 379;
        Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 35));
        Time endTime = java.sql.Time.valueOf(LocalTime.of(10, 55));
        WorkShift.DayOfWeek day = WorkShift.DayOfWeek.Monday;

        WorkShift workShift = new WorkShift();
        workShift.setShiftID(shiftID);
        workShift.setStartTime(startTime);
        workShift.setEndTime(endTime);
        workShift.setDay(day);

        workShiftRepository.save(workShift);
        workShift = null;

        workShift = workShiftRepository.findByShiftID(shiftID);
        assertNotNull(workShift);
        assertEquals(shiftID, workShift.getShiftID());


    }
}