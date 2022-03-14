package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(MockitoExtension.class)
public class TestPurchasedItemService {
    private static final int PURCHASED_ITEM_ID = 3;
    private static final int ITEM_QUANTITY = 2;


    @Mock
    private PurchasedItemRepository purchasedItemRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private PurchasedItemService purchasedItemService;

    private static final String VALID_NAME = "HELLO";

    private static final Item ITEM = Mockito.spy(new Item());

    @BeforeEach
    public void setMockOutput() {
        Mockito.when(ITEM.getName()).thenReturn(VALID_NAME);

        lenient().when(purchasedItemRepository.findByPurchasedItemID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PURCHASED_ITEM_ID)) {

                Item item = new Item();
                PurchasedItem purchasedItem = new PurchasedItem();

                purchasedItem.setPurchasedItemID(PURCHASED_ITEM_ID);
                purchasedItem.setItemQuantity(ITEM_QUANTITY);
                purchasedItem.setItem(item);

                return purchasedItem;
            } else {
                return null;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(purchasedItemRepository.save(any(PurchasedItem.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);

        lenient().when(itemRepository.findByName(any(String.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(VALID_NAME)) {
                        return ITEM;
                    } else {
                        return null;
                    }
                });
    }

    @Test
    public void testCreatePurchasedItemZeroQuantity() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        Item item = new Item();
        PurchasedItem purchasedItem = null;
        String error = null;

        try{
            purchasedItem = purchasedItemService.createPurchasedItem(item,0);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("item quantity cannot be zero.", error);
    }

    @Test
    public void testCreatePurchasedItemNegativeQuantity() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        Item item = new Item();
        PurchasedItem purchasedItem = null;
        String error = null;

        try{
            purchasedItem = purchasedItemService.createPurchasedItem(item,-1);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("item quantity cannot be negative.", error);
    }

    @Test
    public void testCreatePurchasedItemQuantityGreaterThanStock() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());
        Item item = new Item();
        item.setStock(9000);
        String error = null;

        try{
            PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 9001);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals("itemQuantity cannot be greater than the stock.", error);
    }

    @Test
    public void testCreatePurchasedItemNullItem() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());
        Item item = new Item();
        String error = null;

        try{
            PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(null, ITEM_QUANTITY);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals("item cannot be null.", error);
    }

    //TODO
    @Test
    public void testCreatePurchasedItem() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        Item aItem = new Item();
        PurchasedItem purchasedItem = new PurchasedItem();

        try {
            purchasedItem = purchasedItemService.createPurchasedItem(aItem, ITEM_QUANTITY);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(purchasedItem);

        assertEquals(purchasedItem.getItem(), aItem);
        assertEquals(purchasedItem.getItemQuantity(), ITEM_QUANTITY);
    }

    @Test
    public void testDeletePurchasedItem() {
        PurchasedItem purchasedItem = new PurchasedItem();
        String error = null;
        try {
            purchasedItemService.deletePurchasedItem(PURCHASED_ITEM_ID);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(error);
    }

    @Test
    public void testGetAllPurchasedItems() {
        String error = null;
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        PurchasedItem purchasedItem = null;

        Item aItem = new Item();

        when(purchasedItemRepository.findAll()).thenReturn(purchasedItems);

        try {
            aItem.setStock(1000);
            purchasedItem = purchasedItemService.createPurchasedItem(aItem, ITEM_QUANTITY);
            purchasedItems.add(purchasedItem);
            System.out.println(purchasedItems.toString());
            purchasedItems = purchasedItemService.getAllPurchasedItem();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(purchasedItems);
        assertNull(error);
    }

    @Test
    public void testGetPurchasedItemByID() {
        PurchasedItem purchasedItem = null;
        String error = null;
        try {
            purchasedItem = purchasedItemService.getPurchasedItem(PURCHASED_ITEM_ID);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(error);
    }

    @Test
    public void testGetPurchasedItemDoesntExist() {
        PurchasedItem purchasedItem = null;
        String error = null;

        try {
            purchasedItem = purchasedItemService.getPurchasedItem(420);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("PurchasedItem doesn't exist.", error);
    }

    @Test
    public void testGetPurchasedItemWithInvalidID() {
        PurchasedItem purchasedItem = null;
        String error = null;

        try {
            purchasedItem = purchasedItemService.getPurchasedItem(-1);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("ID is invalid.", error);
    }




}
