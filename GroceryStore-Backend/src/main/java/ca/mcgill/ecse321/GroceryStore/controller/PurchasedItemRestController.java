package ca.mcgill.ecse321.GroceryStore.controller;


import ca.mcgill.ecse321.GroceryStore.dto.PurchasedItemDTO;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import ca.mcgill.ecse321.GroceryStore.service.PurchasedItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class PurchasedItemRestController {

    @Autowired
    private PurchasedItemService purchasedItemService;


    @PostMapping(value = {"/purchased_item", "/purchased_item"}) //we added the extra path variable to test something out
    public PurchasedItemDTO createPurchasedItem(@RequestParam String item,
                                                @RequestParam("aItemQuantity") int aItemQuantity) throws IllegalArgumentException {

        Item item2 = purchasedItemService.getItem(item);
        PurchasedItem purchasedItem = purchasedItemService.createPurchasedItem(item2, aItemQuantity);
        return convertToDto(purchasedItem);
    }

//    @PutMapping(value = { "/edit_checkOutItems/{id}"})
//    public CheckOutItemDto updateCheckOutItem(@PathVariable("id") int , @RequestParam Item newMediaType, @RequestParam String newName,
//                                              @RequestParam boolean newIsCheckedOut, @RequestParam boolean newIsReserved, @RequestParam int newBorrowingPeriod,@RequestParam String date)
//            throws IllegalArgumentException {
//        Date newStartDate = Date.valueOf(date);
//        CheckOutItem checkOutItem = checkOutItemService.updateCheckOutItem(mediaID, newMediaType, newName, newIsCheckedOut, newIsReserved, newBorrowingPeriod, newStartDate);
//        return Conversion.convertToDto(checkOutItem);
//    }


    @GetMapping(value = {"/purchased_item", "/purchased_item"})
    public List<PurchasedItemDTO> getPurchasedItems() throws IllegalArgumentException {
        List<PurchasedItemDTO> purchasedItemDTOS = new ArrayList<>();
        for (PurchasedItem purchasedItem : purchasedItemService.getAllPurchasedItem()) purchasedItemDTOS.add(convertToDto(purchasedItem));
        return purchasedItemDTOS;
    }



    @GetMapping(value = {"/purchased_item/{purchased_itemid}", "/purchased_item/{purchased_itemid}"})
    public PurchasedItemDTO getPurchasedItem(@PathVariable("purchased_itemid")  int aPurchasedItemID) throws IllegalArgumentException {
        PurchasedItem purchasedItem = purchasedItemService.getPurchasedItem(aPurchasedItemID);
        return convertToDto(purchasedItem);
    }

    @DeleteMapping(value = { "/purchased_item/{purchased_itemid}", "/purchased_item/{purchased_itemid}"})
    public void deletePurchasedItem(@PathVariable("purchased_itemid") int purchased_itemid) throws IllegalArgumentException {
        purchasedItemService.deletePurchasedItem(purchased_itemid);
    }


    private PurchasedItemDTO convertToDto(PurchasedItem p) {
        if (p == null) {
            throw new IllegalArgumentException("There is no such Purchased Item!");
        }
        return new PurchasedItemDTO(p.getItem(), p.getItemQuantity(), p.getPurchasedItemID());
    }
}
