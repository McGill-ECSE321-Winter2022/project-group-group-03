package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
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
    @Mock
    private ItemService itemService;

    private static final String VALID_NAME = "HELLO";

    private static final Item ITEM = new Item();

    @BeforeEach
    public void setMockOutput() {

        lenient().when(itemService.getItem(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(VALID_NAME)) {
                        Item item = new Item();
                        item.setName(VALID_NAME);
                        item.setPurchasable(true);
                        item.setPrice(5);
                        item.setDescription("good");
                        item.setStock(9000);
                        item.setTotalPurchased(0);
                        return item;
                    } else {
                        return null;
                    }
                });

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

        lenient().when(purchasedItemRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(PURCHASED_ITEM_ID)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });


        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(purchasedItemRepository.save(any(PurchasedItem.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);


    }

    @Test
    public void testCreatePurchasedItemZeroQuantity() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        PurchasedItem purchasedItem = null;
        String error = null;

        try{
            purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME,0);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("item quantity cannot be zero.", error);
    }

    @Test
    public void testCreatePurchasedItemNegativeQuantity() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        PurchasedItem purchasedItem = null;
        String error = null;

        try{
            purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME,-1);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("item quantity cannot be negative.", error);
    }

    @Test
    public void testCreatePurchasedItemQuantityGreaterThanStock() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());


        String error = null;

        try{
            ITEM.setPurchasable(true);
            ITEM.setStock(9000);
            PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, 9001);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals("itemQuantity cannot be greater than the stock.", error);
    }

    @Test
    public void testCreatePurchasedItemNotPurchaseable() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());
        String error = null;
        Item item  = new Item();
        item.setPurchasable(false);

        try{
            when(itemService.getItem(VALID_NAME)).thenReturn(item);
            PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, ITEM_QUANTITY);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertEquals("item is not purchasable.", error);
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

        PurchasedItem purchasedItem = new PurchasedItem();
        String error = null;
        Item item = null;

        try {
            purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, ITEM_QUANTITY);
            item = itemService.getItem(VALID_NAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(error);
        assertNotNull(purchasedItem);
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
        aItem.setPurchasable(true);

        when(purchasedItemRepository.findAll()).thenReturn(purchasedItems);

        try {
            aItem.setStock(1000);
            purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, ITEM_QUANTITY);
            purchasedItems.add(purchasedItem);
            purchasedItems = purchasedItemService.getAllPurchasedItem();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(purchasedItems);
        assertNull(error);
    }

    @Test
    public void testGetPurchasedItemByID() {
        PurchasedItem purchasedItem = new PurchasedItem();
        String error = null;

        Item item = new Item();
        purchasedItem.setItem(item);
        purchasedItem.setPurchasedItemID(3);
        purchasedItem.setItemQuantity(4);
        PurchasedItem purchasedItem2 = new PurchasedItem();

        try {
            purchasedItem2 = purchasedItemService.getPurchasedItem(3);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(purchasedItem2);
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

    @Test
    public void testUpdatePurchasedItem() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        PurchasedItem purchasedItem = null;
        String error = null;

        try{
            purchasedItem = purchasedItemService.updatePurchasedItemQuantity(-1, PURCHASED_ITEM_ID);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(purchasedItem);
        assertEquals("item quantity cannot be negative.", error);
    }

    @Test
    public void testGetPurchasedItemItem() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        Item item = new Item();

        try {
            item = purchasedItemRepository.findByPurchasedItemID(PURCHASED_ITEM_ID).getItem();

        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(item);
    }
    @Test
    public void testUpdatePurchasedItemQuantity(){
        String error = null;
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, 2);

        try{
            when(purchasedItemRepository.existsById(anyInt())).thenReturn(true);
            when(purchasedItemRepository.findByPurchasedItemID(anyInt())).thenReturn(purchasedItem);
            purchasedItemService.updatePurchasedItemQuantity(90001, purchasedItem.getPurchasedItemID());
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals(error, "itemQuantity cannot be greater than the stock.");
    }

    @Test
    public void testUpdatePurchasedItemQuantityNegative(){
        String error = null;
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, 2);

        try{
            when(purchasedItemRepository.existsById(anyInt())).thenReturn(true);
            when(purchasedItemRepository.findByPurchasedItemID(anyInt())).thenReturn(purchasedItem);
            purchasedItemService.updatePurchasedItemQuantity(-1, purchasedItem.getPurchasedItemID());
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals(error, "item quantity cannot be negative.");
    }
    @Test
    public void testUpdatePurchasedItemQuantityWrongId(){

        String error = null;
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(VALID_NAME, 2);
        try{
            purchasedItem = purchasedItemService.updatePurchasedItemQuantity(1, 1234);
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals(error, "PurchasedItem doesn't exist.");
    }

}