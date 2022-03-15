package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.GroceryStore.dao.*;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.hamcrest.core.Is;
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
    private String ITEM_NAME_KEY = "Cheesy Balls";
    private String SHIPPING_ADDRESS = "3064 rue edmond rostand";
    private DeliveryOrder.ShippingStatus SHIPPING_STATUS = DeliveryOrder.ShippingStatus.InCart;
    private int TOTAL_COST = 68;
    private boolean IS_IN_TOWN = true;

    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private PurchasedItemRepository purchasedItemRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private DeliveryOrderService deliveryOrderService;
    @InjectMocks
    private StoreService storeService;
    @InjectMocks
    private PurchasedItemService purchasedItemService;
    @InjectMocks
    private ItemService itemService;

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
                deliveryOrder.setIsOutOfTown(IS_IN_TOWN);
                Store store = storeService.createStore(15, "MTL", 9, 8);
               // when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
                deliveryOrder.setStore(store);

                return deliveryOrder;
            } else {
                return null;
            }
        });
        lenient().when(deliveryOrderRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(deliveryOrderRepository.save(any(DeliveryOrder.class))).thenAnswer(returnParameterAsAnswer);
    }

    //CREATE
    @Test
    public void testCreateDeliveryOrder() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        int confirmationNumber = 70;
        DeliveryOrder deliveryOrder = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
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

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Confirmation number can't be empty.", error);
    }


    @Test
    public void testCreateDeliveryOrderConfirmationNumberZero() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        int confirmationNumber = 0;
        String error = null;
        DeliveryOrder deliveryOrder = null;
        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);
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
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);
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

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(shippingAddress, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping address can't be empty.", error);
    }
    @Test
    public void testUpdateDeliveryOrderNullShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        DeliveryOrder deliveryOrder = null;
        deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        String address = null;
        String error = null;

        try {
            deliveryOrderService.setShippingAddress(CONFIRMATION_NUMBER_KEY, null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(deliveryOrder.getShippingAddress(), SHIPPING_ADDRESS);
        assertEquals("Address can't be empty.", error);
    }

    @Test
    public void testCreateDeliveryOrderEmptyShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingAddress = "";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(shippingAddress, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping address can't be empty.", error);
    }
    @Test
    public void testUpdateDeliveryOrderEmptyShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        DeliveryOrder deliveryOrder = null;
        deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        String address = "";
        String error = null;

        try {
            deliveryOrderService.setShippingAddress(CONFIRMATION_NUMBER_KEY, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(deliveryOrder.getShippingAddress(), SHIPPING_ADDRESS);
        assertEquals("Address can't be empty.", error);
    }

    @Test
    public void testCreateDeliveryOrderSingleSpaceShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingAddress = " ";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(shippingAddress, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping address can't be empty.", error);
    }
    @Test
    public void testUpdateDeliveryOrderSingleSpaceShippingAddress() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        DeliveryOrder deliveryOrder = null;
        deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        String address = " ";
        String error = null;

        try {
            deliveryOrderService.setShippingAddress(CONFIRMATION_NUMBER_KEY, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(deliveryOrder.getShippingAddress(), SHIPPING_ADDRESS);
        assertEquals("Address can't be empty.", error);
    }

    //SHIPPING STATUS
    @Test
    public void testCreateDeliveryOrderNullShippingStatus() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingStatus = null;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, shippingStatus, CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping status can't be empty.", error);
    }

    @Test
    public void testCreateDeliveryOrderEmptyShippingStatus() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingStatus = "";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, shippingStatus, CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping status can't be empty.", error);
    }

    @Test
    public void testCreateDeliveryOrderSingleSpaceShippingStatus() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String shippingStatus = " ";
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, shippingStatus, CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Shipping status can't be empty.", error);
    }
    @Test
    public void testUpdateDeliveryOrderAddressWRONGID() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        String address = "lol";
        String error = null;
        DeliveryOrder deliveryOrder = null;
        deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        try {
            deliveryOrderService.setShippingAddress(10000, address);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(deliveryOrder.getShippingAddress());
        // check error
        assertEquals("Delivery order doesn't exist.", error);
    }

    //TOTAL COST
    @Test
    public void testCreateDeliveryOrderNullTotalCost() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer totalCost = null;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Total cost can't be empty.", error);
    }


    @Test
    public void testCreateDeliveryOrderTotalCostZero() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer totalCost = 0;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Total cost must be greater than 0.", error);
    }

    @Test
    public void testCreateDeliveryOrderTotalCostNegative() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        Integer totalCost = -5;
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, totalCost, IS_IN_TOWN);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(deliveryOrder);
        assertEquals("Total cost must be greater than 0.", error);
    }

    //Duplicate delivery order created
    @Test
    public void testCreateDuplicateDeliveryOrderID() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());
        int confirmationNumber = 42;
        DeliveryOrder deliveryOrder1 = null;
        DeliveryOrder deliveryOrder2 = null;
        String error = null;
        ArrayList<DeliveryOrder> DOs = new ArrayList<>();
        when(deliveryOrderRepository.findAll()).thenReturn(DOs);

        try {
            deliveryOrder1 = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);
            DOs.add(deliveryOrder1);
            deliveryOrder2 = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);

    } catch(
    IllegalArgumentException e){
        error = e.getMessage();
    }
        assertNotNull(deliveryOrder1);
        assertNull(deliveryOrder2);
        assertEquals("An identical delivery order with the same confirmation number already exists.", error);
    }
    @Test
    public void testGetAllDeliveryOrder(){
        String error = null;
        List<DeliveryOrder> deliveryOrders = new ArrayList<>();
        DeliveryOrder deliveryOrder = null;
        int confirmationNumber = 100;
        when(deliveryOrderRepository.findAll()).thenReturn(deliveryOrders);

        try {
            deliveryOrder = deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), confirmationNumber, TOTAL_COST, IS_IN_TOWN);
            deliveryOrders.add(deliveryOrder);
            deliveryOrders = deliveryOrderService.getAllDeliveryOrders();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(deliveryOrder);
    }

    //DELETE

    @Test
    public void testDeleteDeliveryOrder() {
        deliveryOrderService.createDeliveryOrder(SHIPPING_ADDRESS, SHIPPING_STATUS.name(), CONFIRMATION_NUMBER_KEY, TOTAL_COST, IS_IN_TOWN);
        String error = null;

        try {
            deliveryOrderService.deleteDeliveryOrder(CONFIRMATION_NUMBER_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        System.out.println(error);
        assertNull(error);
    }

    @Test
    public void testDeleteDeliveryOrderInvalidID() {
        DeliveryOrder deliveryOrder = null;
        String error = null;
        try {
            deliveryOrderService.deleteDeliveryOrder(4933);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Delivery order doesn't exist.",error);
    }

    @Test
    public void testDeleteDeliveryOrderIDZero() {
        DeliveryOrder deliveryOrder = null;
        String error = null;
        try {
            deliveryOrderService.deleteDeliveryOrder(0);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Confirmation number must be greater than 0.",error);
    }

    @Test
    public void testDeleteDeliveryOrderIDNegative() {
        DeliveryOrder deliveryOrder = null;
        String error = null;
        try {
            deliveryOrderService.deleteDeliveryOrder(-10);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Confirmation number must be greater than 0.",error);
    }
    @Test
    public void testGetDeliveryOrderByConfirmationNumber() {
        DeliveryOrder deliveryOrder = null;
        String error = null;

        try {
            deliveryOrder = deliveryOrderService.deliveryOrderRepository.findDeliveryOrderByConfirmationNumber(CONFIRMATION_NUMBER_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(deliveryOrder);
        assertNull(error);

    }

    //GET
    @Test
    public void testGetDeliveryOrderDoesNotExist() {
        DeliveryOrder deliveryOrder2 = null;
        String error = null;

        try{
            deliveryOrder2 = deliveryOrderService.getDeliveryOrder(789);
        }catch(IllegalArgumentException e){
            error= e.getMessage();
        }
        assertNull(deliveryOrder2);
        assertEquals(error, "Delivery order doesn't exist.");
    }
    @Test
    public void testGetDeliveryOrderNullInteger() {
        DeliveryOrder deliveryOrder2 = null;
        String error = null;

        try{
            deliveryOrder2 = deliveryOrderService.getDeliveryOrder(null);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder2);
        assertNotNull(error);
        assertEquals(error, "Confirmation number can't be empty.");
    }

    @Test
    public void testGetDeliveryOrderZEROInteger() {
        DeliveryOrder deliveryOrder2 = null;
        String error = null;

        try{
            deliveryOrder2 = deliveryOrderService.getDeliveryOrder(0);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder2);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }

    @Test
    public void testGetDeliveryOrderNegativeInteger() {
        DeliveryOrder deliveryOrder2 = null;
        String error = null;

        try{
            deliveryOrder2 = deliveryOrderService.getDeliveryOrder(-10);
        }catch (Exception e){
            error = e.getMessage();
        }
        assertNull(deliveryOrder2);
        assertNotNull(error);
        assertEquals(error, "Confirmation number must be greater than 0.");
    }
//    @Test
//    public void testDeliveryOrderStockCheck(){
//        DeliveryOrder deliveryOrder = deliveryOrderService.getDeliveryOrder(CONFIRMATION_NUMBER_KEY);
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
//        assertNotNull(deliveryOrder);
//
//        List<PurchasedItem> purchasedItemList = new ArrayList<>();
//        purchasedItemList.add(purchasedItem2Check);
//        deliveryOrder.setPurchasedItem(purchasedItemList);
//
//        assertNotNull(deliveryOrder.getPurchasedItem());
//
//        item2Check.setStock(2);
//
//        try{
//           purchasedItemService.purchasedItemRepository.findByPurchasedItemID(deliveryOrder.getPurchasedItem().get(0).getPurchasedItemID()).setItemQuantity(10);
//        }catch (Exception e){
//            error = e.getMessage();
//        }
//        assertNotNull(error);
//        assertEquals(error, "itemQuantity cannot be greater than the stock.");
//
//    }
//

}




