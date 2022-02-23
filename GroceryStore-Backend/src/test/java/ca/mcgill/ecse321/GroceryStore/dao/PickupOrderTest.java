package ca.mcgill.ecse321.GroceryStore.dao;

import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PickupOrderTest {
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

    List<PickupOrder> orderList = new ArrayList<PickupOrder>();


    //Purchased Item -> An order can have many purchased items therefore we use a list
    PurchasedItem defaultPurchasedItem = new PurchasedItem();
    List<PurchasedItem> purchasedItemList = new ArrayList<PurchasedItem>();

    //Assign attributes to the temporary PurchasedItem object
    public void initializeDefaultPurchasedItem(){
        this.defaultPurchasedItem.setPurchasedItemID(123);
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

        pickupOrder = pickupOrderRepository.findByConfirmationNumber(confirmationNumber);
        assertNotNull(pickupOrder);
        assertEquals(confirmationNumber, pickupOrder.getConfirmationNumber());

        delete1PurchasedItem(defaultPurchasedItem);
        assertNull(purchasedItemRepository.findByPurchasedItemID(defaultPurchasedItem.getPurchasedItemID()));
        assertFalse(pickupOrderRepository.findByConfirmationNumber(confirmationNumber).getPurchasedItem().contains(defaultPurchasedItem));

    }
}
