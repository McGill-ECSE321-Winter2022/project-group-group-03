package ca.mcgill.ecse321.GroceryStore.dto;

public class ItemDTO {

    private String name;
    private boolean purchasable;
    private int price;
    private String description;
    private int stock;
    private int totalPurchased;
    private String image;



    public ItemDTO(String name, boolean purchasable, int price, String description, int stock, int totalPurchased, String image) {
        this.name = name;
        this.purchasable = purchasable;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.totalPurchased = totalPurchased;
        this.image = image;
    }

    public String getName() {return name;}

    public boolean getPurchasable() {return purchasable;}

    public int getPrice() {return price;}

    public String getDescription() {return description;}

    public int getStock() {return stock;}

    public int getTotalPurchased() {return totalPurchased;}

    public String getImage() {return image;}
}