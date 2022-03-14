package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.ItemRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PurchasedItemRepository;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

public class TestPurchasedItemService {
    private static final int sample_purchaseditemID = 3;
    private static final int sample_purchaseditemQuantity = 99;


    @Mock
    private PurchasedItemRepository purchasedItemRepository;
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private PurchasedItemService purchasedItemService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(purchasedItemRepository.findByPurchasedItemID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(sample_purchaseditemID)) {

                Item item = new Item();
                PurchasedItem purchasedItem = new PurchasedItem();

                purchasedItem.setPurchasedItemID(sample_purchaseditemID);
                purchasedItem.setItemQuantity(sample_purchaseditemQuantity);
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
    }

    @Test
    public void testCreatePurchasedItem() {
        assertEquals(0, purchasedItemService.getAllPurchasedItem().size());

        Item item = new Item();

        try{
            PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item, sample_purchaseditemQuantity);
        }catch(IllegalArgumentException e){
            fail();
        }
    }

}
