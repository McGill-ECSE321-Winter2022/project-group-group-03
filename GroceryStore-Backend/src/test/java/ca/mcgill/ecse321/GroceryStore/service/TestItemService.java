package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestItemService {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private StoreService storeService;

    private static final String ITEM_KEY = "Coke Zero";
    private static final Boolean ITEM_PURCHASABLE = true;
    private static final int VALID_PRICE = 2;
    private static final String ITEM_DESCRIPTION = "Tastes just like the real thing";
    private static final int VALID_STOCK = 10;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(itemRepository.findByName(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ITEM_KEY)) {
                Item item = new Item();
                item.setName(ITEM_KEY);
                item.setPurchasable(ITEM_PURCHASABLE);
                item.setPrice(VALID_PRICE);
                item.setDescription(ITEM_DESCRIPTION);
                item.setStock(VALID_STOCK);
                item.setTotalPurchased(0);
                return item;
            }
            else {
                return null;
            }
        });
        lenient().when(storeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Store s = storeService.createStore("ADDRESS", 5,5);
            return new ArrayList<Store>(Arrays.asList(s));
        });
        lenient().when(itemRepository.existsById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ITEM_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });
        Answer<?> returnParameterAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAnswer);
    }

    @Test
    public void testCreateItem() {
        assertEquals(0,itemService.getAllItems().size());
        Item item = null;
        String itemName = "OREO";
        String itemDescription = "GOES BETTER WITH MILK";
        try {
            item = itemService.createItem(itemName,ITEM_PURCHASABLE,VALID_PRICE,itemDescription,VALID_STOCK);
        } catch(IllegalArgumentException error) {
            fail();
        }

        assertNotNull(item);
        assertEquals(item.getName(),itemName);
        assertEquals(item.getPurchasable(),ITEM_PURCHASABLE);
        assertEquals(item.getPrice(),VALID_PRICE);
        assertEquals(item.getDescription(),itemDescription);
        assertEquals(item.getStock(),VALID_STOCK);
        assertEquals(item.getTotalPurchased(),0);
    }
    

    @Test
    public void testCreateItemNullName() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem(null,ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,VALID_STOCK);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Name can't be empty.",errorMessage);

    }

    @Test
    public void testCreateItemEmptyName() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem("",ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,VALID_STOCK);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Name can't be empty.",errorMessage);
    }

    @Test
    public void testCreateItemNullDescription() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;


        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,VALID_PRICE,null,VALID_STOCK);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Description can't be empty.",errorMessage);
    }

    @Test
    public void testCreateItemEmptyDescription() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,VALID_PRICE,"",VALID_STOCK);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Description can't be empty.",errorMessage);
    }

    @Test
    public void testCreateItemZeroPrice() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,0,ITEM_DESCRIPTION,VALID_STOCK);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Item's price can't be 0.",errorMessage);
    }

    @Test
    public void testCreateItemZeroStock() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,0);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Item's stock can't be 0.",errorMessage);

    }

    @Test
    public void testCreateItemNegativePrice() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,-1,ITEM_DESCRIPTION,VALID_STOCK);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Item's price can't be negative.",errorMessage);
    }

    @Test
    public void testCreateItemNegativeStock() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;

        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,-1);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Item's stock can't be negative.",errorMessage);
    }

    @Test
    public void testGetItem() {
        Item item = null;
        try {
            item = itemService.getItem(ITEM_KEY);
        }
        catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getName(),ITEM_KEY);
    }

    @Test
    public void testGetItemNullName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.getItem(null);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testGetItemEmptyName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.getItem("");
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testGetItemNonExistingName() {
        Item item = null;
        String errorMessage = null;
        String name = "OREO";
        try {
            item = itemService.getItem(name);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + name + " was not found in the database.",errorMessage);
    }

    @Test
    public void testGetAllItems() {
        List<Item> items = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(items);

        try {
            Item item = itemService.createItem("OREO",ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,VALID_STOCK);
            items.add(item);
            items = itemService.getAllItems();
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(items);
    }

    @Test
    public void testUpdateItemPurchasable() {
        Item item = null;
        try{
            item = itemService.updateItemPurchasable(ITEM_KEY, !ITEM_PURCHASABLE);
        }catch (Exception e){
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getPurchasable(), !ITEM_PURCHASABLE);
    }

    @Test
    public void testUpdateItemPurchasableNullName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemPurchasable(null,!ITEM_PURCHASABLE);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemPurchasableEmptyName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemPurchasable("",!ITEM_PURCHASABLE);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemPurchasableNonExistentName() {
        Item item = null;
        String INVALID_NAME = "OREO";
        String errorMessage = null;
        try {
            item = itemService.updateItemPurchasable(INVALID_NAME,!ITEM_PURCHASABLE);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + INVALID_NAME + " was not found in the database.",errorMessage);
    }

    @Test
    public void testUpdateItemPurchasableAlreadyTrue() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemPurchasable(ITEM_KEY,ITEM_PURCHASABLE);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + ITEM_KEY + " is already purchasable.",errorMessage);
    }

    @Test
    public void testUpdateItemPurchasableAlreadyFalse() {
        Item item2 = null;
        String errorMessage = null;
        String newItem = "OREO";
        Item item = itemService.createItem(newItem,!ITEM_PURCHASABLE,30,"Goes better with milk.",30);
        when(itemRepository.findByName(anyString())).thenReturn(item);
        try {
            item2 = itemService.updateItemPurchasable(newItem,!ITEM_PURCHASABLE);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item2);
        assertEquals("The Item with name: " + newItem + " is already not purchasable.",errorMessage);
    }

    @Test
    public void testUpdateItemDescription() {
        Item item = null;
        String newDescription = "Now in multiple flavours";
        try{
            item = itemService.updateItemDescription(ITEM_KEY, newDescription);
        }catch (Exception e){
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getDescription(), newDescription);
    }

    @Test
    public void testUpdateItemDescriptionNullName() {
        Item item = null;
        String errorMessage = null;
        String newDescription = "Now in multiple flavours";
        try {
            item = itemService.updateItemDescription(null,newDescription);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemDescriptionEmptyName() {
        Item item = null;
        String errorMessage = null;
        String newDescription = "Now in multiple flavours";
        try {
            item = itemService.updateItemDescription("",newDescription);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemDescriptionNonExistentName() {
        Item item = null;
        String INVALID_NAME = "OREO";
        String newDescription = "Now in multiple flavours";
        String errorMessage = null;
        try {
            item = itemService.updateItemDescription(INVALID_NAME,newDescription);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + INVALID_NAME + " was not found in the database.",errorMessage);
    }

    @Test
    public void testUpdateItemDescriptionNullDescription() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemDescription(ITEM_KEY,null);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A Description parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemDescriptionEmptyDescription() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemDescription(ITEM_KEY,"");
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A Description parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemTotalPurchased() {
        Item item = null;
        int newTotalPurchased = 5;
        try{
            item = itemService.updateItemTotalPurchased(ITEM_KEY, newTotalPurchased);
        }catch (Exception e){
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getTotalPurchased(), newTotalPurchased);
        assertEquals(item.getStock(),VALID_STOCK-newTotalPurchased);
    }

    @Test
    public void testUpdateItemTotalPurchasedNullName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemTotalPurchased(null,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemTotalPurchasedEmptyName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemTotalPurchased("",5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemTotalPurchasedNonExistentName() {
        Item item = null;
        String INVALID_NAME = "OREO";
        String errorMessage = null;
        try {
            item = itemService.updateItemTotalPurchased(INVALID_NAME,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + INVALID_NAME + " was not found in the database.",errorMessage);
    }

    @Test
    public void testUpdateTotalPurchasedZero() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.updateItemTotalPurchased(ITEM_KEY,0);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Total Purchased can't be 0.",errorMessage);
    }



    @Test
    public void testUpdateTotalPurchasedNegative() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.updateItemTotalPurchased(ITEM_KEY,-1);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Total Purchased can't be negative.",errorMessage);
    }

    @Test
    public void testUpdateTotalPurchasedWhenStockZero() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item;
        item = itemService.updateItemStock(ITEM_KEY,0);
        try {
            when(itemRepository.findByName(anyString())).thenReturn(item);
            item = itemService.updateItemTotalPurchased(ITEM_KEY,2);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNotNull(item);
        assertEquals(0,item.getStock());
        assertEquals("There are no " + ITEM_KEY + "s available currently.",errorMessage);
    }

    @Test
    public void testUpdateTotalPurchasedWhenStockNotEnough() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = itemService.getItem(ITEM_KEY);
        Item nullItem = null;
        try {
            nullItem = itemService.updateItemTotalPurchased(ITEM_KEY,14);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(nullItem);
        assertEquals("There are only " + item.getStock() + " " + ITEM_KEY + "s available currently.",errorMessage);

    }

    @Test
    public void testUpdateItem() {
        Item item = itemService.updateItem(ITEM_KEY, "https://pbs.twimg.com/profile_images/577610307700326400/oz5_yYBl_400x400.jpeg",23,21,"You'll love it",ITEM_PURCHASABLE);

        assertEquals(item.getPrice(),23);
        assertEquals(item.getStock(),21);
        assertEquals(item.getDescription(),"You'll love it");
        assertEquals(item.getPurchasable(),ITEM_PURCHASABLE);
    }

    @Test
    public void testUpdateItemStock() {
        Item item = null;
        int newStock = 5;
        try{
            item = itemService.updateItemStock(ITEM_KEY, newStock);
        }catch (Exception e){
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getStock(), newStock);
    }

    @Test
    public void testUpdateItemStockNullName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemStock(null,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemStockEmptyName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemStock("",5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemStockNonExistentName() {
        Item item = null;
        String INVALID_NAME = "OREO";
        String errorMessage = null;
        try {
            item = itemService.updateItemStock(INVALID_NAME,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + INVALID_NAME + " was not found in the database.",errorMessage);
    }

    @Test
    public void testUpdateStockNegative() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.updateItemStock(ITEM_KEY,-1);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Stock can't be negative.",errorMessage);
    }

    @Test
    public void testAddItemStock() {
        Item item = null;
        int newStock = 5;
        try{
            item = itemService.addItemStock(ITEM_KEY, newStock);
        }catch (Exception e){
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getStock(), VALID_STOCK+newStock);
    }

    @Test
    public void testAddItemStockNullName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.addItemStock(null,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testAddItemStockEmptyName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.addItemStock("",5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testAddItemStockNonExistentName() {
        Item item = null;
        String INVALID_NAME = "OREO";
        String errorMessage = null;
        try {
            item = itemService.addItemStock(INVALID_NAME,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + INVALID_NAME + " was not found in the database.",errorMessage);
    }

    @Test
    public void testAddStockZero() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.addItemStock(ITEM_KEY,0);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Added Stock can't be 0.",errorMessage);
    }

    @Test
    public void testAddStockNegative() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.addItemStock(ITEM_KEY,-1);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Added Stock can't be negative.",errorMessage);
    }

    @Test
    public void testUpdateItemPrice() {
        Item item = null;
        int newPrice = 5;
        try{
            item = itemService.updateItemPrice(ITEM_KEY, newPrice);
        }catch (Exception e){
            fail();
        }
        assertNotNull(item);
        assertEquals(item.getPrice(), newPrice);
    }

    @Test
    public void testUpdateItemPriceNullName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemPrice(null,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemPriceEmptyName() {
        Item item = null;
        String errorMessage = null;
        try {
            item = itemService.updateItemPrice("",5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateItemPriceNonExistentName() {
        Item item = null;
        String INVALID_NAME = "OREO";
        String errorMessage = null;
        try {
            item = itemService.updateItemPrice(INVALID_NAME,5);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(item);
        assertEquals("The Item with name: " + INVALID_NAME + " was not found in the database.",errorMessage);
    }

    @Test
    public void testUpdateItemPriceZero() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.updateItemPrice(ITEM_KEY,0);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Price can't be 0.",errorMessage);
    }

    @Test
    public void testUpdateItemPriceNegative() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        try {
            item = itemService.updateItemPrice(ITEM_KEY,-1);
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("Price can't be negative.",errorMessage);
    }

}

