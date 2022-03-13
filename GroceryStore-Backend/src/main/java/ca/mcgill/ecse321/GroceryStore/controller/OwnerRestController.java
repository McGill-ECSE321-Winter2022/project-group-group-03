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

    @PostMapping(value = { "/owner/{username}", "/owner/{username}/" })
    public OwnerDTO createEvent(@PathVariable("username") String username, @RequestParam String email,
                                @RequestParam String password)
            throws IllegalArgumentException {
        Owner owner = service.createOwner(username, email, password);
        return convertToDto(owner);
    }

    @GetMapping(value = { "/owner", "/owner/" })
    public OwnerDTO getOwner(String username) {
        return convertToDto(service.getOwner(username));
    }

    @GetMapping(value = { "/registrations/person/{name}", "/registrations/person/{name}/" })
    public StoreDTO getStoreOfOwner(@PathVariable("username") OwnerDTO oDto) {
        Owner o = convertToDomainObject(oDto);
        return createStoreDtoOfOwner(o);
    }

    private OwnerDTO convertToDto(Owner o) {
        if (o == null) {
            throw new IllegalArgumentException("There is no such Owner!");
        }
        OwnerDTO ownerDTO = new OwnerDTO(o.getUsername(), o.getEmail(), o.getPassword());
        return ownerDTO;
    }

    private StoreDTO convertToDto(Store store) {
        return new StoreDTO(store.getStoreID(), store.getAddress(), store.getCurrentActivePickup(), store.getCurrentActiveDelivery());
    }

    private Owner convertToDomainObject(OwnerDTO oDto) {
        Owner owner = service.getOwner(oDto.getUsername());
        return owner;
    }

    private StoreDTO createStoreDtoOfOwner(Owner owner){
        return convertToDto(owner.getStore());
    }
}
