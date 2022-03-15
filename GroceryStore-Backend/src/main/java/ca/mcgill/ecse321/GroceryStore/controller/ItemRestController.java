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
    public ItemDTO createBusinessHour(@RequestParam String itemName ,@RequestParam boolean purchasable,
                                      @RequestParam int price, @RequestParam String description,
                                      @RequestParam int stock) throws IllegalArgumentException {
        return convertToDto(service.createItem(itemName,purchasable,price,description,stock));
    }

    @GetMapping(value = {"/item","/item/"})
    public List<ItemDTO> getBusinessHours() throws IllegalArgumentException {
        return service.getAllItems().stream().map(this::convertToDto).collect(Collectors.toList());

    }


// Deleting item breaks purchase item and is not necessary
    /*@DeleteMapping(value = {"/item/{itemName}", "/item/{itemName}/"})
    public void deleteItem(@PathVariable("itemName") String itemName) throws IllegalArgumentException {
        service.deleteItem(itemName);
    }*/

    @GetMapping(value = {"/item/{itemName}", "/item/{itemName}/"})
    public ItemDTO getItem(@PathVariable("itemName") String itemName) throws IllegalArgumentException {
        return convertToDto(service.getItem(itemName));
    }

    private ItemDTO convertToDto(Item item) {
        if (item == null) throw new IllegalArgumentException("There is no such Item!");
        return new ItemDTO(item.getName(), item.getPurchasable(), item.getPrice(),item.getDescription(), item.getStock(), item.getTotalPurchased());
    }

}