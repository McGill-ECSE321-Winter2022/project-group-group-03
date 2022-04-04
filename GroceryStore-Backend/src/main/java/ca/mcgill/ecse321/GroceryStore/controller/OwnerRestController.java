package ca.mcgill.ecse321.GroceryStore.controller;

import ca.mcgill.ecse321.GroceryStore.dto.OwnerDTO;
import ca.mcgill.ecse321.GroceryStore.dto.StoreDTO;
import ca.mcgill.ecse321.GroceryStore.model.Owner;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import ca.mcgill.ecse321.GroceryStore.service.OwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OwnerRestController {

    @Autowired
    private OwnerService service;

    @PostMapping(value = { "/owner", "/owner/" })
    public ResponseEntity<?> createOwner(@RequestParam String username, @RequestParam String email,
                                @RequestParam String password)
            throws IllegalArgumentException {
        try {
            return ResponseEntity.ok(convertToDto(service.createOwner(username, email,password)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = {"/owner_login", "/owner_login/"})
    public ResponseEntity<?> loginOwner(@RequestParam String username, @RequestParam String password) throws IllegalArgumentException{
        try {
            return ResponseEntity.ok(convertToDto(service.loginOwner(username, password)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @GetMapping(value = { "/owner", "/owner/" })
    public OwnerDTO getOwner(@RequestParam String username) {
        return convertToDto(service.getOwner(username));
    }

    @GetMapping(value = { "/owners", "/owners/" })
    public List<OwnerDTO> getOwners() {
        List<OwnerDTO> ownerDTOS = new ArrayList<>();
        for (Owner o : service.getOwners()){
            ownerDTOS.add(convertToDto(o));
        }
        return ownerDTOS;
    }

    @GetMapping(value = { "/store/owner", "/store/owner/" })
    public StoreDTO getStoreOfOwner(@RequestParam String username) {
        return createStoreDtoOfOwner(convertToDomainObject(getOwner(username)));
    }

    @PutMapping(value = {"/update_owner", "/update_owner/"})
    public ResponseEntity<?> updateOwner(@RequestParam String username, @RequestParam String password){
        try {
            return ResponseEntity.ok(convertToDto(service.updateOwner(username, password)));
        } catch(IllegalArgumentException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
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
