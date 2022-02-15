package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PurchasedItem{

   private static int nextPurchasedItemID = 1;

   private int itemQuantity;

   @Id
   private int purchasedItemID;


   private Item item;
   private GroceryStoreSystem groceryStoreSystem;
   private Order order;




   public PurchasedItem(int aItemQuantity, Item aItem, Order aOrder)
   {
      itemQuantity = aItemQuantity;
      purchasedItemID = nextPurchasedItemID++;
      setItem(aItem);
      setOrder(aOrder);
   }

   public PurchasedItem() {

   }

   public void setItemQuantity(int aItemQuantity)
   {
      itemQuantity = aItemQuantity;
   }
   public int getItemQuantity()
   {
      return itemQuantity;
   }

   public int getPurchasedItemID()
   {
      return purchasedItemID;
   }



   @ManyToOne(optional = false)
   public Item getItem() {
      return this.item;
   }
   
   public void setItem(Item aItem) {
      this.item = aItem;
   }

   @ManyToOne(optional = false)
   public Order getOrder() {
      return this.order;
   }

   public void setOrder(Order aOrder) {
      this.order = aOrder;
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
              "purchasedItemID" + ":" + getPurchasedItemID()+ "," +
              "itemQuantity" + ":" + getItemQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
              "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null") + System.getProperties().getProperty("line.separator") +
              "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
   }
   }
