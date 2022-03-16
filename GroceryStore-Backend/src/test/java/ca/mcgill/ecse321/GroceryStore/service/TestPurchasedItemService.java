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
import java.util.Optional;

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
    @InjectMocks
    private ItemService itemService;

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
        item.setPurchasable(true);
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
    public void testCreatePurchasedItemNotPurchaseable() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());
        String error = null;
        Item item = new Item();
        item.setPurchasable(false);
        try{
            PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, ITEM_QUANTITY);
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

        Item aItem = new Item();
        aItem.setPurchasable(true);
        PurchasedItem purchasedItem = new PurchasedItem();
        String error = null;

        try {
            aItem.setStock(10000);
            purchasedItem = purchasedItemService.createPurchasedItem(aItem, ITEM_QUANTITY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(error);
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
        aItem.setPurchasable(true);

        when(purchasedItemRepository.findAll()).thenReturn(purchasedItems);

        try {
            aItem.setStock(1000);
            purchasedItem = purchasedItemService.createPurchasedItem(aItem, ITEM_QUANTITY);
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
        Item item = new Item();
        String error = null;
        item = itemService.createItem("Cheeze", true, 10, "Cheezy", 10);
        item.setStock(10);

        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 2);

        try{
            when(purchasedItemRepository.existsById(anyInt())).thenReturn(true);
            when(purchasedItemRepository.findByPurchasedItemID(anyInt())).thenReturn(purchasedItem);
            purchasedItemService.updatePurchasedItemQuantity(20, purchasedItem.getPurchasedItemID());
        }
        catch (Exception e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals(error, "itemQuantity cannot be greater than the stock.");
    }

    @Test
    public void testUpdatePurchasedItemQuantityNegative(){
        Item item = new Item();
        String error = null;
        item = itemService.createItem("Cheeze", true, 10, "Cheezy", 10);
        item.setStock(10);

        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 2);

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

        Item item = new Item();
        String error = null;
        item = itemService.createItem("Cheeze", true, 10, "Cheezy", 10);
        item.setStock(10);
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, 2);
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