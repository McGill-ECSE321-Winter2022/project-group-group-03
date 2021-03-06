package ca.mcgill.ecse321.GroceryStore.controller;


import ca.mcgill.ecse321.GroceryStore.dto.PurchasedItemDTO;
import ca.mcgill.ecse321.GroceryStore.model.PurchasedItem;
import ca.mcgill.ecse321.GroceryStore.service.PurchasedItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class PurchasedItemRestController {

    @Autowired
    private PurchasedItemService purchasedItemService;


    @PostMapping(value = {"/purchased_item", "/purchased_item"}) //we added the extra path variable to test something out
    public ResponseEntity<?> createPurchasedItem(@RequestParam String item,
                                              @RequestParam("aItemQuantity") int aItemQuantity,
                                              @RequestParam int confirmationNumber,
                                              @RequestParam String orderType) throws IllegalArgumentException {
        try {
            return ResponseEntity.ok(convertToDto(purchasedItemService.createPurchasedItem(item, aItemQuantity, confirmationNumber, orderType)));
        }
        catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = { "/edit_purchasedItem/{id}","/edit_purchasedItem/{id}/"})
    public PurchasedItemDTO updatePurchasedItem(@PathVariable("id") int purchasedItemID, @RequestParam int itemQuantity)
            throws IllegalArgumentException {
        PurchasedItem purchasedItem = purchasedItemService.updatePurchasedItemQuantity(itemQuantity, purchasedItemID);
        return convertToDto(purchasedItem);
    }

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
