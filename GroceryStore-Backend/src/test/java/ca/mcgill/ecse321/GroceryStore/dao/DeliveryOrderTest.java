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
public class DeliveryOrderTest {

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;

    @AfterEach
    public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies

        deliveryOrderRepository.deleteAll();

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
        DeliveryOrder.ShippingStatus status = DeliveryOrder.ShippingStatus.Ordered;

        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setConfirmationNumber(confirmationNumber);
        deliveryOrder.setTotalCost(totalCost);
        deliveryOrder.setShippingAddress(shippingAddress);
        deliveryOrder.setShippingStatus(status);

        deliveryOrderRepository.save(deliveryOrder);

        deliveryOrder = deliveryOrderRepository.findByConfirmationNumber(confirmationNumber);
        assertNotNull(deliveryOrder);
        assertEquals(confirmationNumber, deliveryOrder.getConfirmationNumber());
    }

}
