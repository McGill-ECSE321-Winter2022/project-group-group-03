package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public abstract class Order{
   private ArrayList<PurchasedItem> purchasedItem;
   private static int nextConfimationNumber = 1;
   private int totalCost;
   @Id
   private int confimationNumber;
   private Store store;
   private GroceryStoreSystem groceryStoreSystem;
   
   @ManyToMany
   public ArrayList<PurchasedItem> getPurchasedItem() {
      return this.purchasedItem;
   }
   
   public void setPurchasedItem(ArrayList<PurchasedItem> purchasedItems) {
      this.purchasedItem = purchasedItems;
   }
   
   private NamedUser namedUser;
   
   @ManyToOne(optional = false)
   public NamedUser getNamedUser() {
      return this.namedUser;
   }
   
   public void setNamedUser(NamedUser namedUsers) {
      this.namedUser = namedUsers;
   }

   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }
   
   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }

   public Order() {}

   public Order(int aTotalCost, GroceryStoreSystem aGroceryStoreSystem)
   {
      totalCost = aTotalCost;
      confimationNumber = nextConfimationNumber++;
      store = new Store();
      groceryStoreSystem = aGroceryStoreSystem;
      purchasedItem = new ArrayList<PurchasedItem>();
   }

   //------------------------
   // INTERFACE
   //------------------------

   public void setTotalCost(int aTotalCost)
   {
      totalCost = aTotalCost;
   }

   public int getTotalCost()
   {
      return totalCost;
   }

   public int getConfimationNumber()
   {
      return confimationNumber;
   }


   @ManyToOne()
   public Store getStore()
   {
      return store;
   }

   public void setStore(Store aStore) {
      store = aStore;
   }

   public void addPurchasedItem(PurchasedItem aPurchasedItem)
   {
      purchasedItem.add(aPurchasedItem);
      }
}
