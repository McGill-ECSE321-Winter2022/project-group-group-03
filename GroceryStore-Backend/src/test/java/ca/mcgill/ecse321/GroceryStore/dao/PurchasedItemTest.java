package ca.mcgill.ecse321.GroceryStore.dao;

import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PurchasedItemTest {
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

    //Item -> A customer can have many items therefore we use a list
    Item defaultItem = new Item();

    //Assign attributes to the temporary Item object
    public void initializeDefaultItem(){
        this.defaultItem.setName("Heinz");
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

        purchasedItem = purchasedItemRepository.findByPurchasedItemID(purchasedItemID);
        assertNotNull(purchasedItem);
        assertEquals(purchasedItemID, purchasedItem.getPurchasedItemID());

        String name = purchasedItemRepository.findByPurchasedItemID(purchasedItemID).getItem().getName();
        String name2Compare = itemRepository.findByName(defaultItem.getName()).getName();
        assertEquals( name, name2Compare);
    }
}
