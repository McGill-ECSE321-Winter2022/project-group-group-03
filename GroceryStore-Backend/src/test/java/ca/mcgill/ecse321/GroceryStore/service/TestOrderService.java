/*
package ca.mcgill.ecse321.GroceryStore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
public class TestOrderService {
    private final int CONFIRMATION_NUMBER_KEY = 123;
    private final String SHIPPING_ADDRESS = "3064 rue edmond rostand";
    private final DeliveryOrder.ShippingStatus SHIPPING_STATUS = DeliveryOrder.ShippingStatus.InCart;
    private final int TOTAL_COST = 68;
    private static final PickupOrder.PaymentMethod PAYMENT_METHOD = PickupOrder.PaymentMethod.Cash;
    private static final PickupOrder.PickupStatus PICKUP_STATUS = PickupOrder.PickupStatus.Ordered;
    private static final int PURCHASED_ITEM_ID = 3;
    private static final int ITEM_QUANTITY = 2;

    @Mock
    private DeliveryOrderRepository deliveryOrderRepository;
    @Mock
    private PickupOrderRepository pickupOrderRepository;
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

        lenient().when(pickupOrderRepository.findByConfirmationNumber(any(int.class))).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER_KEY)) {
                ArrayList<PickupOrder> pickupOrders = new ArrayList<>();
                PickupOrder pickupOrder = null;
                Store store = storeService.createStore(15, "MTL", 9, 8);
                storeRepository.save(store);
                when(storeRepository.findAll()).thenReturn(Arrays.asList(store));

                PurchasedItem purchasedItem = new PurchasedItem();
                Item item = new Item();

                purchasedItem.setPurchasedItemID(PURCHASED_ITEM_ID);
                purchasedItem.setItemQuantity(ITEM_QUANTITY);
                purchasedItem.setItem(item);

                ArrayList<PurchasedItem> purchasedItemList = new ArrayList<>();
                purchasedItemList.add(purchasedItem);


                pickupOrder.setConfirmationNumber(CONFIRMATION_NUMBER_KEY);
                pickupOrder.setPurchasedItem(purchasedItemList);
                pickupOrder.setTotalCost(TOTAL_COST);
                pickupOrder.setStore(store);
                pickupOrder.setPaymentMethod(PAYMENT_METHOD);
                pickupOrder.setPickupStatus(PICKUP_STATUS);

                pickupOrders.add(pickupOrder);
                return pickupOrders;
            } else {
                return new ArrayList<DeliveryOrder>();
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(deliveryOrderRepository.save(any(DeliveryOrder.class))).thenAnswer(returnParameterAsAnswer);

        lenient().when(pickupOrderRepository.save(any(PickupOrder.class))).thenAnswer(returnParameterAsAnswer);
    }


    @Test
    public void testGetAllOrder() {
        assertEquals(0, deliveryOrderService.getAllDeliveryOrders().size());

        String error = null;
        List<Order> Orders = new ArrayList<>();
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        PickupOrder pickupOrder = new PickupOrder();

        deliveryOrder.setConfirmationNumber(30);
        pickupOrder.setConfirmationNumber(40);

        Orders.add(deliveryOrder);
        Orders.add(pickupOrder);

        try {
            Orders.add(deliveryOrder);
            Orders.add(pickupOrder);

        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNotNull(Orders);
        assertNull(error);

    }

}




*/


/*
package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.HolidayRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
public class TestOrderService {

    private static final int TOTAL_COST = 50;
    private static final int CONFIRMATION_NUMBER = 20;
    private static final PickupOrder.PickupStatus PICKUP_STATUS = PickupOrder.PickupStatus.Ordered;
    private static final PickupOrder.PaymentMethod PAYMENT_METHOD = PickupOrder.PaymentMethod.Cash;
    private static final DeliveryOrder.ShippingStatus SHIPPING_STATUS = DeliveryOrder.ShippingStatus.InCart;
    private static final String SHIPPING_ADDRESS = "Sherbrooke EST";
    private static final int PURCHASED_ITEM_ID = 3;
    private static final int ITEM_QUANTITY = 2;

    @Mock
    private PickupOrderRepository pickupOrderRepository;

    @Mock
    private PickupOrderRepository deliveryOrderRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private OrderService orderService;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(pickupOrderRepository.findByConfirmationNumber(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER)) {
                PickupOrder pickupOrder = null;
                Store store = storeService.createStore(15, "MTL", 9, 8);
                storeRepository.save(store);
                when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
                PurchasedItem purchasedItem = new PurchasedItem();
                Item item = new Item();

                purchasedItem.setPurchasedItemID(PURCHASED_ITEM_ID);
                purchasedItem.setItemQuantity(ITEM_QUANTITY);
                purchasedItem.setItem(item);

                ArrayList<PurchasedItem> purchasedItemList = new ArrayList<>();
                purchasedItemList.add(purchasedItem);


                pickupOrder.setConfirmationNumber(CONFIRMATION_NUMBER);
                pickupOrder.setStore(store);
                pickupOrder.setPurchasedItem(purchasedItemList);
                pickupOrder.setTotalCost(TOTAL_COST);
                pickupOrder.setPaymentMethod(PAYMENT_METHOD);
                pickupOrder.setPickupStatus(PICKUP_STATUS);

             */
/*   order.setTotalCost(TOTAL_COST);
                order.setStore(store);
                order.setConfirmationNumber(CONFIRMATION_NUMBER);
                order.setPurchasedItem(purchasedItemList);
*//*

                return pickupOrder;
            } else {
                return null;
            }
            });


        lenient().when(deliveryOrderRepository.findByConfirmationNumber(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER)) {
                Order order = null;
                DeliveryOrder deliveryOrder = null;
                Store store = storeService.createStore(15, "MTL", 9, 8);
                storeRepository.save(store);
                when(storeRepository.findAll()).thenReturn(Arrays.asList(store));
                PurchasedItem purchasedItem = new PurchasedItem();
                ArrayList<PurchasedItem> purchasedItemList = new ArrayList<>();
                purchasedItemList.add(purchasedItem);
                Item item = new Item();

                purchasedItem.setPurchasedItemID(PURCHASED_ITEM_ID);
                purchasedItem.setItemQuantity(ITEM_QUANTITY);
                purchasedItem.setItem(item);

                assert false;
                deliveryOrder.setConfirmationNumber(CONFIRMATION_NUMBER);
                deliveryOrder.setStore(store);
                deliveryOrder.setPurchasedItem(purchasedItemList);
                deliveryOrder.setTotalCost(TOTAL_COST);
                deliveryOrder.setShippingAddress(SHIPPING_ADDRESS);
                deliveryOrder.setShippingStatus(SHIPPING_STATUS);

                */
/*
                order.setTotalCost(TOTAL_COST);
                order.setStore(store);
                order.setConfirmationNumber(CONFIRMATION_NUMBER);
                order.setPurchasedItem(purchasedItemList);*//*


                return deliveryOrder;
            } else {
                return null;
            }
        });

        lenient().when(pickupOrderRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(CONFIRMATION_NUMBER)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(pickupOrderRepository.save(any(Order.class))).thenAnswer(returnParameterAsAnswer);
}
*/
