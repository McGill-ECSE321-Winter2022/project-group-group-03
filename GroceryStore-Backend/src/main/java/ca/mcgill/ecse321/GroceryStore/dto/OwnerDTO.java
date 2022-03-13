package ca.mcgill.ecse321.GroceryStore.dto;

public class OwnerDTO {
    private String username;
    private String password;
    private String email;
    private StoreDTO store;


    public OwnerDTO(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }
}
