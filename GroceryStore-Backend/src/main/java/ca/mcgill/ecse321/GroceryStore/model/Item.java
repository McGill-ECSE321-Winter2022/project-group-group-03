package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Item{

   private static int nextItemID = 1;

   private String name;
   private boolean purchasable;
   private int price;
   private String description;
   private int stock;
   private int totalPurchased;

   @Id
   private int itemID;

   private ArrayList<PurchasedItem> purchasedItem;
   private ArrayList<Store> store;
   private GroceryStoreSystem groceryStoreSystem;

   public Item(){}

   public Item(String aName, boolean aPurchasable, int aPrice, String aDescription, int aStock, int aTotalPurchased, GroceryStoreSystem aGroceryStoreSystem, Store aStore)
   {
      name = aName;
      purchasable = aPurchasable;
      price = aPrice;
      description = aDescription;
      stock = aStock;
      totalPurchased = aTotalPurchased;
      itemID = nextItemID++;
      setGroceryStoreSystem(aGroceryStoreSystem);

      setStore(new ArrayList<>());
      addStore(aStore);

      purchasedItem = new ArrayList<>();
   }

   public void setName(String aName) { name = aName; }
   public String getName()
   {
      return name;
   }

   public void setPurchasable(boolean aPurchasable) { purchasable = aPurchasable; }
   public boolean getPurchasable()
   {
      return purchasable;
   }

   public void setPrice(int aPrice) { price = aPrice; }
   public int getPrice()
   {
      return price;
   }

   public void setDescription(String aDescription)  { description = aDescription; }
   public String getDescription()
   {
      return description;
   }

   public void setStock(int aStock) { stock = aStock; }
   public int getStock()
   {
      return stock;
   }

   public void setTotalPurchased(int aTotalPurchased) {
      totalPurchased = aTotalPurchased;
   }
   public int getTotalPurchased()
   {
      return totalPurchased;
   }

   public int getItemID()
   {
      return itemID;
   }


   @ManyToMany
   public ArrayList<PurchasedItem> getPurchasedItem() {
      return this.purchasedItem;
   }
   public void addPurchasedItem(int aItemQuantity, Order aOrder)
   {
      this.getPurchasedItem().add(new PurchasedItem(aItemQuantity, this, aOrder));
      setPurchasedItem(this.getPurchasedItem());
   }
   public void addPurchasedItem(PurchasedItem aPurchasedItem){
      if (purchasedItem.contains(aPurchasedItem)) return;
      this.getPurchasedItem().add(aPurchasedItem);
      setPurchasedItem(this.purchasedItem);
   }
   public void setPurchasedItem(ArrayList<PurchasedItem> purchasedItems) {
      this.purchasedItem = purchasedItems;
   }
   public void removePurchasedItem(PurchasedItem aPurchasedItem)
   {
      //Unable to remove aPurchasedItem, as it must always have a item
      if (!this.equals(aPurchasedItem.getItem()))
      {
         purchasedItem.remove(aPurchasedItem);
      }
   }
   public int numberOfPurchasedItems()
   {
      return purchasedItem.size();
   }

   public boolean hasPurchasedItems()
   {
      return purchasedItem.size() > 0;
   }

   public int indexOfPurchasedItem(PurchasedItem aPurchasedItem)
   {
      return purchasedItem.indexOf(aPurchasedItem);
   }
   

   @ManyToMany(mappedBy="item" )
   public ArrayList<Store> getStore() {
      return this.store;
   }
   public void addStore(Store aStore) {
      this.store.add(aStore);
      setStore(this.store);
   }
   public void setStore(ArrayList<Store> stores) {
      this.store = stores;
   }
   

   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }
   
   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }

   public String toString()
   {
      return super.toString() + "["+
              "itemID" + ":" + getItemID()+ "," +
              "name" + ":" + getName()+ "," +
              "purchasable" + ":" + getPurchasable()+ "," +
              "price" + ":" + getPrice()+ "," +
              "description" + ":" + getDescription()+ "," +
              "stock" + ":" + getStock()+ "," +
              "totalPurchased" + ":" + getTotalPurchased()+ "]" + System.getProperties().getProperty("line.separator") +
              "  " + "groceryStoreSystem = "+(getGroceryStoreSystem()!=null?Integer.toHexString(System.identityHashCode(getGroceryStoreSystem())):"null") + System.getProperties().getProperty("line.separator") +
              "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
   }

   }
