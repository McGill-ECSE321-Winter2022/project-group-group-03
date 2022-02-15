package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.util.*;
import javax.persistence.ManyToMany;

@Entity
public class NamedUser extends User{

   private String name;
   private String address;
   private ArrayList<Order> orders;

   public NamedUser(String aUsername, String aPassword, String aEmail, GroceryStoreSystem aGroceryStoreSystem, String aName, String aAddress)
   {
      super(aUsername, aPassword, aEmail, aGroceryStoreSystem);
      name = aName;
      address = aAddress;
      orders = new ArrayList<>();
   }

   public NamedUser() {
   }

   public String getName()
   {
      return name;
   }
   public void setName(String aName) { name = aName; }


   public String getAddress() { return address; }
   public void setAddress(String aAddress) { address = aAddress; }


   @ManyToMany
   public ArrayList<Order> getOrders() {
      return this.orders;
   }
   
   public void setOrders(ArrayList<Order> orders) {
      this.orders = orders;
   }
   
   }
