package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
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
        lenient().when(itemRepository.existsById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ITEM_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });
        Answer<?> returnParameterAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
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
    public void testCreateDuplicateItem() {
        assertEquals(0,itemService.getAllItems().size());
        String errorMessage = null;
        Item item = null;
        
        try {
            item = itemService.createItem(ITEM_KEY,ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,VALID_STOCK);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }

        assertNull(item);
        assertEquals("An identical Item already exists.",errorMessage);
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
        Item item = null;
        when(itemRepository.findAll()).thenReturn(items);

        try {
            item = itemService.createItem("OREO",ITEM_PURCHASABLE,VALID_PRICE,ITEM_DESCRIPTION,VALID_STOCK);
            items.add(item);
            items = itemService.getAllItems();
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(items);
    }

    @Test
    public void testDeleteItem() {
        try {
            itemService.deleteItem(ITEM_KEY);
        }
        catch(IllegalArgumentException error) {
            fail();
        }
    }

    @Test
    public void testDeleteItemNullName() {
        String errorMessage = null;
        try {
            itemService.deleteItem(null);
        }
        catch(IllegalArgumentException error) {
           errorMessage = error.getMessage();
        }
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testDeleteItemEmptyName() {
        String errorMessage = null;
        try {
            itemService.deleteItem("");
        }
        catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertEquals("A name parameter is needed.",errorMessage);
    }

    @Test
    public void testDeletenNonExistentItem() {
        String errorMessage = null;
        String nonExistentName = "OREO";
        try {
            itemService.deleteItem(nonExistentName);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertEquals("The item with name " + nonExistentName + " does not exist.",errorMessage);
    }
}

