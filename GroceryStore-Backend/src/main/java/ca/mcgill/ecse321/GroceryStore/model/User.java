package ca.mcgill.ecse321.GroceryStore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User{

   private static Map<String, User> usersByUsername = new HashMap<>();
   private static Map<String, User> usersByEmail = new HashMap<>();

   @Id
   private String username;
   private String password;
   private String email;

   public User(String aUsername, String aPassword, String aEmail, GroceryStoreSystem aGroceryStoreSystem)
   {
      this.username=aUsername;
      this.email=aEmail;
      this.password = aPassword;
      setGroceryStoreSystem(aGroceryStoreSystem);
   }

   public User() {
   }

   public void setUsername(String aUsername){
      String anOldUsername = getUsername();
      if (anOldUsername != null && anOldUsername.equals(aUsername)) {
       return;
      }
      username = aUsername;
      if (anOldUsername != null) {
         usersByUsername.remove(anOldUsername);
      }
      usersByUsername.put(aUsername, this);
   }

   public String getUsername()
   {
      return username;
   }
   public static User getWithUsername(String aUsername)
   {
      return usersByUsername.get(aUsername);
   }
   public static boolean hasWithUsername(String aUsername)
   {
      return getWithUsername(aUsername) != null;
   }

   public void setEmail(String aEmail)
   {
     
      String anOldEmail = getEmail();
      if (anOldEmail != null && anOldEmail.equals(aEmail)) {
         return;
      }
      email = aEmail;
      if (anOldEmail != null) {
         usersByEmail.remove(anOldEmail);
      }
      usersByEmail.put(aEmail, this);
   }

   public String getEmail()
   {
      return email;
   }
   /* Code from template attribute_GetUnique */
   public static User getWithEmail(String aEmail)
   {
      return usersByEmail.get(aEmail);
   }
   /* Code from template attribute_HasUnique */
   public static boolean hasWithEmail(String aEmail)
   {
      return getWithEmail(aEmail) != null;
   }


   private GroceryStoreSystem groceryStoreSystem;
   
   @ManyToOne(optional=false)
   public GroceryStoreSystem getGroceryStoreSystem() {
      return this.groceryStoreSystem;
   }
   
   public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
      this.groceryStoreSystem = groceryStoreSystem;
   }
   
   }
