package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.OwnerDTO;
import ca.mcgill.ecse321.GroceryStore.dto.StoreDTO;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import ca.mcgill.ecse321.GroceryStore.service.OwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class OwnerRestController {

    @Autowired
    private OwnerService service;

    @PostMapping(value = { "/owner", "/owner/" })
    public OwnerDTO createOwner(@RequestParam String username, @RequestParam String email,
                                @RequestParam String password)
            throws IllegalArgumentException {
        Owner owner = service.createOwner(username, email, password);
        return convertToDto(owner);
    }

    @GetMapping(value = { "/owner", "/owner/" })
    public OwnerDTO getOwner(String username) {
        return convertToDto(service.getOwner(username));
    }

    @GetMapping(value = { "/store/owner", "/store/owner/" })
    public StoreDTO getStoreOfOwner(@RequestParam String username) {
        return createStoreDtoOfOwner(convertToDomainObject(getOwner(username)));
    }

    @PutMapping(value = {"/update_owner", "/update_owner/"})
    public OwnerDTO updateOwner(@RequestParam String username, @RequestParam String password){
        return convertToDto(service.updateOwner(username, password));
    }

    @DeleteMapping(value = {"/owner", "/owner/"})
    public void deleteOwner(String username) {service.deleteOwner(username);}

    private OwnerDTO convertToDto(Owner o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Owner!");
        }
        OwnerDTO oDTO = new OwnerDTO(o.getUsername(), o.getPassword(), o.getEmail());
        oDTO.setStore(convertToDto(o.getStore()));
        return oDTO;
    }

    private StoreDTO convertToDto(Store store) {
        return new StoreDTO(store.getStoreID(), store.getAddress(), store.getCurrentActivePickup(), store.getCurrentActiveDelivery());
    }

    private Owner convertToDomainObject(OwnerDTO oDto) {
        return service.getOwner(oDto.getUsername());
    }

    private StoreDTO createStoreDtoOfOwner(Owner owner){
        return convertToDto(owner.getStore());
    }
}
