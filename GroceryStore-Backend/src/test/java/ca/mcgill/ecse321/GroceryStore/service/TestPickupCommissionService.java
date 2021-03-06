package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class TestPickupCommissionService {
    private int CONFIRMATION_NUMBER_KEY = 123;
    private String ITEM_NAME_KEY = "Cheesy Balls";
    private PickupCommission.PaymentMethod PAYMENT_METHOD = PickupCommission.PaymentMethod.Cash;
    private PickupCommission.PickupStatus PICKUP_STATUS = PickupCommission.PickupStatus.PickedUp;
    private String USERNAME = "Seb";
    private int TOTAL_COST = 68;

    @Mock
    private PickupOrderRepository pickupOrderRepository;
    @Mock
    private PurchasedItemRepository purchasedItemRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private StoreService storeService;


    @InjectMocks
    private PickupOrderService pickupOrderService;
    @InjectMocks
    private PurchasedItemService purchasedItemService;
    @InjectMocks
    private ItemService itemService;


    @BeforeEach
    public void setMockOutput() {

        lenient().when(pickupOrderRepository.findByConfirmationNumber(any(int.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER_KEY)) {
                ArrayList<PickupCommission> pickupOrders = new ArrayList<PickupCommission>();
                PickupCommission pickupOrder = new PickupCommission();
                pickupOrder.setConfirmationNumber(CONFIRMATION_NUMBER_KEY);
                pickupOrder.setPickupStatus(PICKUP_STATUS);
                pickupOrder.setPaymentMethod(PAYMENT_METHOD);
                pickupOrder.setTotalCost(TOTAL_COST);
                Store store = storeService.createStore( "MTL", 9, 8);
                // when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
                pickupOrder.setStore(store);

                return pickupOrder;
            } else {
                return null;
            }
        });
        lenient().when(storeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Store s = storeService.createStore("ADDRESS", 5,5);
            return new ArrayList<Store>(Arrays.asList(s));
        });
        lenient().when(pickupOrderRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(pickupOrderRepository.save(any(PickupCommission.class))).thenAnswer(returnParameterAsAnswer);
    }

    //CREATE
    @Test
    public void testCreatePickupOrder() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        int confirmationNumber = 70;
        PickupCommission pickupOrder = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),CONFIRMATION_NUMBER_KEY);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }


    //CONFIRMATION NUMBER
    @Test
    public void testCreatePickupOrderNullConfirmationNumber() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        Integer confirmationNumber = null;
        PickupCommission pickupOrder = null;
        String error = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),confirmationNumber);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertEquals("Confirmation number can't be empty.", error);
    }

    @Test
    public void testCreatePickupOrderConfirmationNumberZero() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        int confirmationNumber = 0;
        String error = null;
        PickupCommission pickupOrder = null;
        try {
            pickupOrder = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),confirmationNumber);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        // check error
        assertEquals("Confirmation number must be greater than 0.", error);

    }

    @Test
    public void testCreatePickupOrderConfirmationNumberNegative() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        int confirmationNumber = -5;
        String error = null;
        PickupCommission pickupOrder = null;
        try {
            pickupOrder = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),confirmationNumber);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        // check error
        assertEquals("Confirmation number must be greater than 0.", error);
    }

    //Duplicate pickup order created
    @Test
    public void testCreateDuplicatePickupOrderID() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        int confirmationNumber = 42;
        PickupCommission pickupOrder1 = null;
        PickupCommission pickupOrder2 = null;
        String error = null;
        ArrayList<PickupCommission> POs = new ArrayList<>();
        when(pickupOrderRepository.findAll()).thenReturn(POs);

        try {
            pickupOrder1 = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),CONFIRMATION_NUMBER_KEY);
            POs.add(pickupOrder1);
            pickupOrder2 = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),CONFIRMATION_NUMBER_KEY);

        } catch(
                IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(pickupOrder1);
        assertNull(pickupOrder2);
        assertEquals("An identical pickup order with the same confirmation number already exists.", error);
    }
    @Test
    public void testGetAllPickupOrder(){
        String error = null;
        List<PickupCommission> pickupOrders = new ArrayList<>();
        PickupCommission pickupOrder = null;
        int confirmationNumber = 100;
        when(pickupOrderRepository.findAll()).thenReturn(pickupOrders);

        try {
            pickupOrder = pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),CONFIRMATION_NUMBER_KEY);
            pickupOrders.add(pickupOrder);
            pickupOrders = pickupOrderService.getAllPickupOrders();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(pickupOrder);
    }

    //DELETE

    @Test
    public void testDeletePickupOrder() {
        pickupOrderService.createPickupOrder(USERNAME,PAYMENT_METHOD.name(),CONFIRMATION_NUMBER_KEY);
        String error = null;

        try {
            pickupOrderService.deletePickupOrder(CONFIRMATION_NUMBER_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(error);
    }

    @Test
    public void testDeletePickupOrderInvalidID() {
        PickupCommission pickupOrder = null;
        String error = null;
        try {
            pickupOrderService.deletePickupOrder(4933);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Pickup order doesn't exist.",error);
    }

    @Test
    public void testDeletePickupOrderIDZero() {
        PickupCommission pickupOrder = null;
        String error = null;
        try {
            pickupOrderService.deletePickupOrder(0);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Confirmation number must be greater than 0.",error);
    }

    @Test
    public void testDeletePickupOrderIDNegative() {
        PickupCommission pickupOrder = null;
        String error = null;
        try {
            pickupOrderService.deletePickupOrder(-10);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Confirmation number must be greater than 0.",error);
    }
    @Test
    public void testGetPickupOrderByConfirmationNumber() {
        PickupCommission pickupOrder = null;
        String error = null;

        try {
            pickupOrder = pickupOrderService.pickupOrderRepository.findByConfirmationNumber(CONFIRMATION_NUMBER_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(pickupOrder);
        assertNull(error);

    }

    //GET
    @Test
    public void testGetPickupOrderDoesNotExist() {
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.getPickupOrder(789);
        }catch(IllegalArgumentException e){
            error= e.getMessage();
        }
        assertNull(pickupOrder);
        assertEquals(error, "Pickup order doesn't exist.");
    }
    @Test
    public void testGetPickupOrderNullInteger() {
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.getPickupOrder(null);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number can't be empty.");
    }

    @Test
    public void testGetPickupOrderZEROInteger() {
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.getPickupOrder(0);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }

    @Test
    public void testGetPickupOrderNegativeInteger() {
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.getPickupOrder(-10);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }

    @Test
    public void updatePickupStatusNullConfirmationNumber(){
        PickupCommission pickupOrder = null;
        String error = null;
        Integer confirmationNumber = null;

        try{
            pickupOrder = pickupOrderService.updatePickupStatus(confirmationNumber, PICKUP_STATUS.name());
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number can't be empty.");
    }
    @Test
    public void updatePickupStatusConfirmationNumberZero(){
        PickupCommission pickupOrder = null;
        String error = null;


        try{
            pickupOrder = pickupOrderService.updatePickupStatus(0, PICKUP_STATUS.name());
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }
    @Test
    public void updatePickupStatusConfirmationNumberNegative(){
        PickupCommission pickupOrder = null;
        String error = null;


        try{
            pickupOrder = pickupOrderService.updatePickupStatus(-12, PICKUP_STATUS.name());
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }
    @Test
    public void updatePickupStatusInvalidStatus(){
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.updatePickupStatus(CONFIRMATION_NUMBER_KEY, "invalid");
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Not a valid pickup status");
    }
    @Test
    public void updatePickupPaymentNullConfirmationNumber(){
        PickupCommission pickupOrder = null;
        String error = null;
        Integer confirmationNumber = null;

        try{
            pickupOrder = pickupOrderService.updatePaymentMethod(confirmationNumber, PICKUP_STATUS.name());
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number can't be empty.");
    }
    @Test
    public void updatePickupPaymentConfirmationNumberZero(){
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.updatePaymentMethod(0, PAYMENT_METHOD.name());
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }
    @Test
    public void updatePickupPaymentConfirmationNumberNegative(){
        PickupCommission pickupOrder = null;
        String error = null;


        try{
            pickupOrder = pickupOrderService.updatePaymentMethod(-12, PAYMENT_METHOD.name());
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }
    @Test
    public void updatePickupPaymentInvalidPaymentMethod(){
        PickupCommission pickupOrder = null;
        String error = null;

        try{
            pickupOrder = pickupOrderService.updatePaymentMethod(CONFIRMATION_NUMBER_KEY, "invalid");
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertNotNull(error);
        assertEquals(error, "Invalid payment method");
    }

}




