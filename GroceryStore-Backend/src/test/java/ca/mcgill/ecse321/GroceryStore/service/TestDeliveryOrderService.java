package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class TestDeliveryOrderService {
    private int CONFIRMATION_NUMBER_KEY = 123;
    private String SHIPPING_ADDRESS = "3064 rue edmond rostand";
    private DeliveryOrder.ShippingStatus SHIPPING_STATUS = DeliveryOrder.ShippingStatus.InCart;
    private int TOTAL_COST = 68;

    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private PurchasedItemRepository purchasedItemRepository;

    @InjectMocks
    private DeliveryOrderService deliveryOrderService;
    @InjectMocks
    private StoreService storeService;
    @InjectMocks
    private PurchasedItemService purchasedItemService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(any(int.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER_KEY)) {
                ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<DeliveryOrder>();
                DeliveryOrder deliveryOrder = new DeliveryOrder();
                deliveryOrder.setConfirmationNumber(CONFIRMATION_NUMBER_KEY);
                deliveryOrder.setShippingStatus(SHIPPING_STATUS);
                deliveryOrder.setShippingAddress(SHIPPING_ADDRESS);
                deliveryOrder.setTotalCost(TOTAL_COST);

                deliveryOrders.add(deliveryOrder);
                return deliveryOrders;
            } else {
                return new ArrayList<DeliveryOrder>();
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(deliveryOrderRepository.save(any(DeliveryOrder.class))).thenAnswer(returnParameterAsAnswer);
    }
    @Test
    public void testCreateDeliveryOrder() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        int confirmationNumber = 70;
        DeliveryOrder deliveryOrder = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber,TOTAL_COST);
        }catch(IllegalArgumentException e){
            fail();
        }
    }

    //CONFIRMATION NUMBER
    @Test
    public void testCreateDeliveryOrderNullConfirmationNumber() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer confirmationNumber = null;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Confirmation number can't be empty.",error);
    }

    @Test
    public void testCreateDeliveryOrderConfirmationNumberZero() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        int confirmationNumber = 0;
        String error = null;
        DeliveryOrder deliveryOrder = null;
        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        // check error
        assertEquals("Confirmation number must be greater than 0.", error);
    }

    @Test
    public void testCreateDeliveryOrderConfirmationNumberNegative() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        int confirmationNumber = -5;
        String error = null;
        DeliveryOrder deliveryOrder = null;
        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        // check error
        assertEquals("Confirmation number must be greater than 0.", error);
    }

    //SHIPPING ADDRESS
    @Test
    public void testCreateDeliveryOrderNullShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingAddress = null;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(shippingAddress,SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping address can't be empty.",error);
    }

    @Test
    public void testCreateDeliveryOrderEmptyShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingAddress = "";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(shippingAddress,SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping address can't be empty.",error);
    }

    @Test
    public void testCreateDeliveryOrderSingleSpaceShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingAddress = " ";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(shippingAddress,SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping address can't be empty.",error);
    }

    //SHIPPING STATUS
    @Test
    public void testCreateDeliveryOrderNullShippingStatus() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingStatus = null;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,shippingStatus, CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping status can't be empty.",error);
    }

    @Test
    public void testCreateDeliveryOrderEmptyShippingStatus() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingStatus = "";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,shippingStatus, CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping status can't be empty.",error);
    }

    @Test
    public void testCreateDeliveryOrderSingleSpaceShippingStatus() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingStatus = " ";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,shippingStatus, CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping status can't be empty.",error);
    }

    //TOTAL COST
    @Test
    public void testCreateDeliveryOrderNullTotalCost() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer totalCost = null;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Total cost can't be empty.",error);
    }

    @Test
    public void testCreateDeliveryOrderTotalCostZero() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer totalCost = 0;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Total cost must be greater than 0.",error);
    }

    @Test
    public void testCreateDeliveryOrderTotalCostNegative() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer totalCost = -5;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try{
            deliveryOrder= deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS,SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Total cost must be greater than 0.",error);
    }









}
