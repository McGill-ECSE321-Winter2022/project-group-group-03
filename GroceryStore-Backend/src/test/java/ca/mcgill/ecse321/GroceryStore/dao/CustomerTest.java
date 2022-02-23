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

public class CustomerTest {
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

    //Order -> A customer can have many orders therefore we use a list
    PickupOrder defaultOrder = new PickupOrder();
    List<PickupOrder> orderList = new ArrayList<PickupOrder>();


    //Assign attributes to the temporary PickupOrder object
    public void initializeDefaultOrder(){
        this.defaultOrder.setConfirmationNumber(123);
        this.defaultOrder.setPaymentMethod(PickupOrder.PaymentMethod.Cash);
        this.defaultOrder.setPickupStatus(PickupOrder.PickupStatus.Ordered);
    }


    public void delete1Order(PickupOrder order2Delete){
        orderList.remove(order2Delete);
        pickupOrderRepository.deleteById(order2Delete.getConfirmationNumber());
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
        customer = customerRepository.findByUsername(username);
        assertNotNull(customer);
        assertEquals(username, customer.getUsername());

        delete1Order(defaultOrder);
        assertNull(pickupOrderRepository.findByConfirmationNumber(defaultOrder.getConfirmationNumber()));
        //There is only one order in the list so by removing that order we can verify if the list is null
        assertNull(customerRepository.findByUsername(username).getOrder());
    }


}
