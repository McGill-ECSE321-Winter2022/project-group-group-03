package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.ItemDTO;
import ca.mcgill.ecse321.GroceryStore.model.Item;
import ca.mcgill.ecse321.GroceryStore.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

    @Autowired
    private ItemService service;

    @PostMapping(value = { "/item", "/item/" })
    public ItemDTO createItem(@RequestParam String itemName ,@RequestParam boolean purchasable,
                                      @RequestParam int price, @RequestParam String description,
                                      @RequestParam int stock) throws IllegalArgumentException {
        return convertToDto(service.createItem(itemName,purchasable,price,description,stock));
    }

    @GetMapping(value = {"/item","/item/"})
    public List<ItemDTO> getItems() throws IllegalArgumentException {
        return service.getAllItems().stream().map(this::convertToDto).collect(Collectors.toList());

    }

    @PutMapping(value = {"/editItemPurchasable/{itemName}"})
    public ItemDTO updateItemPurchasable(@PathVariable("itemName") String itemName,
                                         @RequestParam boolean newPurchasable) throws IllegalArgumentException {
        return convertToDto(service.updateItemPurchasable(itemName,newPurchasable));
    }

    @PutMapping(value = {"/editItemDescription/{itemName}"})
    public ItemDTO updateItemDescription(@PathVariable("itemName") String itemName,
                                         @RequestParam String newDescription) throws IllegalArgumentException {
        return convertToDto(service.updateItemDescription(itemName,newDescription));
    }

    @PutMapping(value = {"/editItemTotalPurchased/{itemName}"})
    public ItemDTO updateItemTotalPurchased(@PathVariable("itemName") String itemName,
                                            @RequestParam int newTotalPurchased) throws IllegalArgumentException {
        return convertToDto(service.updateItemTotalPurchased(itemName,newTotalPurchased));
    }

    @PutMapping(value = {"/editItemStock/{itemName}"})
    public ItemDTO updateItemStock(@PathVariable("itemName") String itemName,
                                   @RequestParam int newStock) throws IllegalArgumentException {
        return convertToDto(service.updateItemStock(itemName,newStock));
    }

    @PutMapping(value = {"/addItemStock/{itemName}"})
    public ItemDTO addItemStock(@PathVariable("itemName") String itemName,
                                   @RequestParam int addedStock) throws IllegalArgumentException {
        return convertToDto(service.addItemStock(itemName,addedStock));
    }

    @PutMapping(value = {"/editItemPrice/{itemName}"})
    public ItemDTO updateItemPrice(@PathVariable("itemName") String itemName,
                                @RequestParam int newPrice) throws IllegalArgumentException {
        return convertToDto(service.updateItemPrice(itemName,newPrice));
    }

    @GetMapping(value = {"/item/{itemName}", "/item/{itemName}/"})
    public ItemDTO getItem(@PathVariable("itemName") String itemName) throws IllegalArgumentException {
        return convertToDto(service.getItem(itemName));
    }

    @DeleteMapping(value = {"/item/{itemName}", "/item/{itemName}/"})
    public void deleteItem(@PathVariable("itemName") String itemName) throws IllegalArgumentException {
        service.deleteItem(itemName);
    }

    private ItemDTO convertToDto(Item item) {
        if (item == null) throw new IllegalArgumentException("There is no such Item!");
        return new ItemDTO(item.getName(), item.getPurchasable(), item.getPrice(),item.getDescription(), item.getStock(), item.getTotalPurchased());
    }

}