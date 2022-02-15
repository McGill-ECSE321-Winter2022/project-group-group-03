package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import javax.persistence.ManyToMany;

@Entity
public class Owner extends User{
   private ArrayList<Store> store;


   //------------------------
   // CONSTRUCTOR
   //------------------------

   public  Owner() {}

   public Owner(String aUsername, String aPassword, String aEmail, GroceryStoreSystem aGroceryStoreSystem, Store aStore)
   {
      super(aUsername, aPassword, aEmail, aGroceryStoreSystem);
      addStore(aStore);
   }

   @ManyToMany(mappedBy="owner" )
   public ArrayList<Store> getStore() {
      return this.store;
   }
   
   public void setStore(ArrayList<Store> stores) {
      this.store = stores;
   }

   public void addStore(Store aStore) {
      this.store.add(aStore);
      setStore(this.store);
   }
   
}
