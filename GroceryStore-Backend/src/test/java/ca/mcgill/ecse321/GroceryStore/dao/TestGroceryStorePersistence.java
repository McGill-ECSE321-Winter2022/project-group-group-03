package ca.mcgill.ecse321.GroceryStore.dao;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import org.assertj.core.api.Assertions;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.GroceryStore.model.*;

import javax.transaction.Transactional;

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

    //Order -> A customer can have many orders therefore we use a list
    PickupOrder defaultOrder = new PickupOrder();
    List<PickupOrder> orderList = new ArrayList<PickupOrder>();

    //Item -> A customer can have many items therefore we use a list
    Item defaultItem = new Item();
    List<Item> itemList = new ArrayList<Item>();

    //Workshift -> An employee can have many workshifts therefore we use a list
    WorkShift defaultWorkShift = new WorkShift();
    List<WorkShift> workShiftList = new ArrayList<WorkShift>();

    //Purchased Item -> An order can have many purchased items therefore we use a list
    PurchasedItem defaultPurchasedItem = new PurchasedItem();
    List<PurchasedItem> purchasedItemList = new ArrayList<PurchasedItem>();

    //These methods will initialize and create the references that will test the following associations

    //Store
    public void initializeDefaultStore(){
        this.defaultStore.setStoreID(123);
    }

    public void initializeDefaultOrder(){
        this.defaultOrder.setConfirmationNumber(123);
        this.defaultOrder.setPaymentMethod(PickupOrder.PaymentMethod.Cash);
        this.defaultOrder.setPickupStatus(PickupOrder.PickupStatus.Ordered);
    }

    public void initializeDefaultItem(){
        this.defaultItem.setName("Heinz");
    }

    public void initializeDefaultWorkShift(){
       this.defaultWorkShift.setShiftID(123);
       this.defaultWorkShift.setDay(WorkShift.DayOfWeek.Monday);
    }

    public void initializeDefaultPurchasedItem(){
        this.defaultPurchasedItem.setPurchasedItemID(123);
    }


    //These methods will delete the references
    public void deleteDefaultStore(){
        storeRepository.deleteById(defaultStore.getStoreID());
    }

    public void delete1Order(PickupOrder order2Delete){
        orderList.remove(order2Delete);
        pickupOrderRepository.deleteById(order2Delete.getConfirmationNumber());
    }

    public void delete1Item(Item item2Delete){
        itemList.remove(item2Delete);
        itemRepository.deleteById(item2Delete.getName());
    }

    public void delete1Workshift(WorkShift workShift2Delete){
        workShiftList.remove(workShift2Delete);
        workShiftRepository.deleteById(workShift2Delete.getShiftID());
    }

    public void delete1PurchasedItem(PurchasedItem purchasedItem2Delete){
        purchasedItemList.remove(purchasedItem2Delete);
        purchasedItemRepository.deleteById(purchasedItem2Delete.getPurchasedItemID());
    }

    @AfterEach
	public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies
        storeRepository.deleteAll();
        ownerRepository.deleteAll();
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
        businessHourRepository.deleteAll();
        workShiftRepository.deleteAll();
        holidayRepository.deleteAll();
        itemRepository.deleteAll();
        pickupOrderRepository.deleteAll();
        deliveryOrderRepository.deleteAll();
        purchasedItemRepository.deleteAll();

    }

    @Test
    @Transactional
	public void testPersistAndLoadStore() {
		int storeID = 1;
        String address = "215 avenue Kenaston";
        int currentActivePickup = 5;
        int currentActiveDelivery = 6;
		// First example for object save/load
		Store store2Eval = new Store();
        store2Eval.setStoreID(storeID);
        store2Eval.setAddress(address);
        store2Eval.setCurrentActiveDelivery(currentActiveDelivery);
        store2Eval.setCurrentActivePickup(currentActivePickup);

        initializeDefaultItem();
        this.itemList.add(defaultItem);

        itemRepository.saveAll(itemList);
        store2Eval.setItem(itemList);

        itemRepository.saveAll(itemList);
        storeRepository.save(store2Eval);
        store2Eval = null;
        store2Eval = storeRepository.findById(storeID);

        assertNotNull(store2Eval);
		assertEquals(storeID, store2Eval.getStoreID());

        delete1Item(defaultItem);
        assertNull(itemRepository.findByName(defaultItem.getName()));
        assertFalse(storeRepository.findById(storeID).getItem().contains(defaultItem));

	}


    @Test
    @Transactional
    public void testPersistAndLoadEmployee() {
        String username = "Seb";
        int id = 2;
        // First example for object save/load
        Employee employee = new Employee();
        employee.setWorkingStatus(Employee.WorkingStatus.Hired);
        // First example for attribute save/load
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

    @Test
    @Transactional
    public void testPersistAndLoadCustomer(){
        String username = "Arsters";
        String password = "Testies";
        String email = "arsters.testies@gmail.com";
        String address = "3064 rue edmond rostand";

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setAddress(address);
        initializeDefaultOrder();
        this.orderList.add(defaultOrder);
        //customer.setOrder(orderList);
        pickupOrderRepository.saveAll(orderList);

        customerRepository.save(customer);
        customer = null;
        customer = customerRepository.findByUsername(username);
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());

        delete1Order(defaultOrder);
        assertNull(pickupOrderRepository.findByConfirmationNumber(defaultOrder.getConfirmationNumber()));
        //There is only one order in the list so by removing that order we can verify if the list is null
        assertNull(customerRepository.findByUsername(username).getOrder());
    }

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
    @Test
    @Transactional
    public void testPersistAndLoadDeliveryOrder(){
        int confirmationNumber = 123;
        int totalCost = 10;
        String shippingAddress = "3064 rue edmond rostand";
        DeliveryOrder.ShippingStatus status = DeliveryOrder.ShippingStatus.Ordered;

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setConfirmationNumber(confirmationNumber);
        deliveryOrder.setTotalCost(totalCost);
        deliveryOrder.setShippingAddress(shippingAddress);
        deliveryOrder.setShippingStatus(status);

        deliveryOrderRepository.save(deliveryOrder);
        deliveryOrder = null;

        deliveryOrder = deliveryOrderRepository.findByConfirmationNumber(confirmationNumber);
        assertNotNull(deliveryOrder);
        assertEquals(confirmationNumber, deliveryOrder.getConfirmationNumber());
    }
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
    //TODO
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


    @Test
    @Transactional
    public void testPersistAndLoadPickupOrder(){
        int confirmationNumber = 321;
        int totalCost = 5;
        PickupOrder.PaymentMethod paymentMethod = PickupOrder.PaymentMethod.Cash;
        PickupOrder.PickupStatus status = PickupOrder.PickupStatus.InCart;

        PickupOrder pickupOrder = new PickupOrder();
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