package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
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
public class TestPickupOrderService {
    private int CONFIRMATION_NUMBER_KEY = 123;
    private String ITEM_NAME_KEY = "Cheesy Balls";
    private PickupOrder.PaymentMethod PAYMENT_METHOD = PickupOrder.PaymentMethod.Cash;
    private PickupOrder.PickupStatus PICKUP_STATUS = PickupOrder.PickupStatus.PickedUp;
    private int TOTAL_COST = 68;

    @Mock
    private PickupOrderRepository pickupOrderRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private PurchasedItemRepository purchasedItemRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private PickupOrderService pickupOrderService;
    @InjectMocks
    private StoreService storeService;
    @InjectMocks
    private PurchasedItemService purchasedItemService;
    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(pickupOrderRepository.findByConfirmationNumber(any(int.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER_KEY)) {
                ArrayList<PickupOrder> pickupOrders = new ArrayList<PickupOrder>();
                PickupOrder pickupOrder = new PickupOrder();
                pickupOrder.setConfirmationNumber(CONFIRMATION_NUMBER_KEY);
                pickupOrder.setPickupStatus(PICKUP_STATUS);
                pickupOrder.setPaymentMethod(PAYMENT_METHOD);
                pickupOrder.setTotalCost(TOTAL_COST);
                Store store = storeService.createStore(15, "MTL", 9, 8);
                // when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
                pickupOrder.setStore(store);

                return pickupOrder;
            } else {
                return null;
            }
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
        lenient().when(pickupOrderRepository.save(any(PickupOrder.class))).thenAnswer(returnParameterAsAnswer);
    }

    //CREATE
    @Test
    public void testCreatePickupOrder() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        int confirmationNumber = 70;
        PickupOrder pickupOrder = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), confirmationNumber, TOTAL_COST);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }


    //CONFIRMATION NUMBER
    @Test
    public void testCreatePickupOrderNullConfirmationNumber() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        Integer confirmationNumber = null;
        PickupOrder pickupOrder = null;
        String error = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), confirmationNumber, TOTAL_COST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertEquals("Confirmation number can't be empty.", error);
    }
    @Test
    public void testUpdatePickupOrderNullConfirmationNumber() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        Integer confirmationNumber = null;
        String error = null;

        try {
            pickupOrderService.setConfirmationNumber(CONFIRMATION_NUMBER_KEY, confirmationNumber);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(pickupOrder.getConfirmationNumber());
        assertEquals("Confirmation number can't be empty.", error);
    }

    @Test
    public void testCreatePickupOrderConfirmationNumberZero() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        int confirmationNumber = 0;
        String error = null;
        PickupOrder pickupOrder = null;
        try {
            pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), confirmationNumber, TOTAL_COST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        // check error
        assertEquals("Confirmation number must be greater than 0.", error);

    }
    @Test
    public void testUpdatePickupOrderConfirmationNumberZero() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        Integer confirmationNumber = 0;
        String error = null;

        try {
            pickupOrderService.setConfirmationNumber(CONFIRMATION_NUMBER_KEY, confirmationNumber);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(pickupOrder.getConfirmationNumber());
        assertEquals("Confirmation number must be greater than 0.", error);
    }

    @Test
    public void testCreatePickupOrderConfirmationNumberNegative() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        int confirmationNumber = -5;
        String error = null;
        PickupOrder pickupOrder = null;
        try {
            pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), confirmationNumber, TOTAL_COST);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        // check error
        assertEquals("Confirmation number must be greater than 0.", error);
    }


    @Test
    public void testUpdatePickupOrderConfirmationNumberNegative() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        Integer confirmationNumber = -10;
        String error = null;

        try {
            pickupOrderService.setConfirmationNumber(CONFIRMATION_NUMBER_KEY, confirmationNumber);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(pickupOrder.getConfirmationNumber());
        assertEquals("Confirmation number must be greater than 0.", error);
    }
    @Test
    public void testUpdatePickupOrderConfirmationNumberWRONGID() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        Integer confirmationNumber = 999;
        String error = null;
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        try {
            pickupOrderService.setConfirmationNumber(confirmationNumber, 123);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(pickupOrder.getConfirmationNumber(), CONFIRMATION_NUMBER_KEY);
        // check error
        assertEquals("Pickup order doesn't exist.", error);
    }

    //TOTAL COST
    @Test
    public void testCreatePickupOrderNullTotalCost() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        Integer totalCost = null;
        PickupOrder pickupOrder = null;
        String error = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, totalCost);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertEquals("Total cost can't be empty.", error);
    }
    @Test
    public void testUpdatePickupOrderNullTotalCost() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        Integer totalCost = null;
        String error = null;
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        try {
            pickupOrderService.setTotalCost(TOTAL_COST, totalCost);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(pickupOrder.getTotalCost());
        // check error
        assertEquals("Total cost can't be empty.", error);
    }

    @Test
    public void testCreatePickupOrderTotalCostZero() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        Integer totalCost = 0;
        PickupOrder pickupOrder = null;
        String error = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(PAYMENT_METHOD.name(), PICKUP_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertEquals("Total cost must be greater than 0.", error);
    }
    @Test
    public void testUpdatePickupOrderTotalCostNegative() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        Integer totalCost = -10;
        String error = null;
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        try {
            pickupOrderService.setTotalCost(CONFIRMATION_NUMBER_KEY, totalCost);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(pickupOrder.getTotalCost(), TOTAL_COST);
        // check error
        assertEquals("Total cost can't be negative.", error);
    }
    @Test
    public void testUpdatePickupOrderTotalCostWRONGID() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        Integer confirmationNumber = 999;
        String error = null;
        PickupOrder pickupOrder = null;
        pickupOrder = pickupOrderService.createPickupOrder(PICKUP_STATUS.name(), PAYMENT_METHOD.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
        try {
            pickupOrderService.setTotalCost(confirmationNumber, 123);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(pickupOrder.getTotalCost(), TOTAL_COST);
        // check error
        assertEquals("Pickup order doesn't exist.", error);
    }

    @Test
    public void testCreatePickupOrderTotalCostNegative() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());

        Integer totalCost = -5;
        PickupOrder pickupOrder = null;
        String error = null;

        try {
            pickupOrder = pickupOrderService.createPickupOrder(PAYMENT_METHOD.name(), PICKUP_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(pickupOrder);
        assertEquals("Total cost must be greater than 0.", error);
    }

    //Duplicate pickup order created
    @Test
    public void testCreateDuplicatePickupOrderID() {
        assertEquals(0, pickupOrderService.getAllPickupOrders().size());
        int confirmationNumber = 42;
        PickupOrder pickupOrder1 = null;
        PickupOrder pickupOrder2 = null;
        String error = null;
        ArrayList<PickupOrder> POs = new ArrayList<>();
        when(pickupOrderRepository.findAll()).thenReturn(POs);

        try {
            pickupOrder1 = pickupOrderService.createPickupOrder(PAYMENT_METHOD.name(), PICKUP_STATUS.name(), confirmationNumber, TOTAL_COST);
            POs.add(pickupOrder1);
            pickupOrder2 = pickupOrderService.createPickupOrder(PAYMENT_METHOD.name(), PICKUP_STATUS.name(), confirmationNumber, TOTAL_COST);

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
        List<PickupOrder> pickupOrders = new ArrayList<>();
        PickupOrder pickupOrder = null;
        int confirmationNumber = 100;
        when(pickupOrderRepository.findAll()).thenReturn(pickupOrders);

        try {
            pickupOrder = pickupOrderService.createPickupOrder(PAYMENT_METHOD.name(), PICKUP_STATUS.name(), confirmationNumber, TOTAL_COST);
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
        pickupOrderService.createPickupOrder(PAYMENT_METHOD.name(), PICKUP_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST);
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
        PickupOrder pickupOrder = null;
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
//    @Test
//    public void testPickupOrderStockCheck(){
//        PickupOrder pickupOrder = pickupOrderService.getPickupOrder(CONFIRMATION_NUMBER_KEY);
//        String error = null;
//        Item item = new Item();
//        when(itemRepository.findByName(anyString())).thenReturn(item);
//        Item temp = itemService.createItem(ITEM_NAME_KEY, true, 10, "Cheeziest balls in the game", 10);
//        PurchasedItem temp2 = purchasedItemService.createPurchasedItem(item, 0);
//        PurchasedItem purchasedItem = new PurchasedItem();
//        when(purchasedItemRepository.findByPurchasedItemID(anyInt())).thenReturn(purchasedItem);
//
//        PurchasedItem purchasedItem2Check = purchasedItemService.purchasedItemRepository.findByPurchasedItemID(temp2.getPurchasedItemID());
//        Item item2Check = itemService.itemRepository.findByName(ITEM_NAME_KEY);
//        //Check that item and purchased item are successfully added to "repository"
//        assertNotNull(item2Check);
//        assertNotNull(purchasedItem2Check);
//        assertNotNull(pickupOrder);
//
//        List<PurchasedItem> purchasedItemList = new ArrayList<>();
//        purchasedItemList.add(purchasedItem2Check);
//        pickupOrder.setPurchasedItem(purchasedItemList);
//
//        assertNotNull(pickupOrder.getPurchasedItem());
//
//        item2Check.setStock(2);
//
//        try{
//            purchasedItemService.purchasedItemRepository.findByPurchasedItemID(pickupOrder.getPurchasedItem().get(0).getPurchasedItemID()).setItemQuantity(10);
//        }catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNotNull(error);
//        assertEquals(error, "itemQuantity cannot be greater than the stock.");
//
//    }


}




