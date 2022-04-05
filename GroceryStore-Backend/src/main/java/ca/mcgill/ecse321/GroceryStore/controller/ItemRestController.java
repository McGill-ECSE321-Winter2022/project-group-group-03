package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.ItemDTO;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

    @Autowired
    private ItemService service;

    @PostMapping(value = { "/item", "/item/" })
    public ResponseEntity<?> createItem(@RequestParam String itemName , @RequestParam boolean purchasable,
                                     @RequestParam int price, @RequestParam String description,
                                     @RequestParam int stock) throws IllegalArgumentException {
        try {
            return ResponseEntity.ok(convertToDto(service.createItem(itemName, purchasable, price, description, stock)));
        } catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = {"/item","/item/"})
    public List<ItemDTO> getItems() throws IllegalArgumentException {
        return service.getAllItems().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PutMapping(value = {"/editItem/{itemName}"})

    public ResponseEntity<?> updateItemAll(@PathVariable("itemName") String itemName, @RequestParam String newImage,

                                         @RequestParam int newPrice, @RequestParam int newStock,
                                         @RequestParam String newDescription, @RequestParam boolean newPurchasable) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItem(itemName, newImage, newPrice, newStock, newDescription, newPurchasable)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editItemPurchasable/{itemName}"})
    public ResponseEntity<?> updateItemPurchasable(@PathVariable("itemName") String itemName,
                                         @RequestParam boolean newPurchasable) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItemPurchasable(itemName, newPurchasable)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editItemDescription/{itemName}"})
    public ResponseEntity<?> updateItemDescription(@PathVariable("itemName") String itemName,
                                         @RequestParam String newDescription) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItemDescription(itemName, newDescription)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editItemTotalPurchased/{itemName}"})
    public ResponseEntity<?> updateItemTotalPurchased(@PathVariable("itemName") String itemName,
                                            @RequestParam int newTotalPurchased) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItemTotalPurchased(itemName, newTotalPurchased)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editItemStock/{itemName}"})
    public ResponseEntity<?> updateItemStock(@PathVariable("itemName") String itemName,
                                   @RequestParam int newStock) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItemStock(itemName, newStock)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/addItemStock/{itemName}"})
    public ResponseEntity<?> addItemStock(@PathVariable("itemName") String itemName,
                                @RequestParam int addedStock) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.addItemStock(itemName, addedStock)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editItemPrice/{itemName}"})
    public ResponseEntity<?> updateItemPrice(@PathVariable("itemName") String itemName,
                                   @RequestParam int newPrice) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItemPrice(itemName, newPrice)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PutMapping(value = {"/editItemImage/{itemName}"})
    public ResponseEntity<?> updateItemImage(@PathVariable("itemName") String itemName,
                                   @RequestParam String image) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.updateItemImage(itemName, image)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = {"/item/{itemName}", "/item/{itemName}/"})
    public ResponseEntity<?> getItem(@PathVariable("itemName") String itemName) throws IllegalArgumentException {
        try{
            return ResponseEntity.ok(convertToDto(service.getItem(itemName)));
        }catch (IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    private ItemDTO convertToDto(Item item) {
        if (item == null) throw new IllegalArgumentException("There is no such Item!");
        return new ItemDTO(item.getName(), item.getPurchasable(), item.getPrice(),item.getDescription(), item.getStock(), item.getTotalPurchased(), item.getImage());
    }

}